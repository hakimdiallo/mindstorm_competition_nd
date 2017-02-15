package Gaussian;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.Color;

public class LineFollower {
	private ColorSelector colorSelector;
	
	public LineFollower(){
		colorSelector = new ColorSelector();
	}
	
	public void init(){
		colorSelector.chooseColorsAndSaveThem();
	}
	
	public boolean needColors(){
		return colorSelector.colorsAreSaved();
	}
	
	public void turnRight(int turn){
		Motor.A.setSpeed(300+turn);
		Motor.C.setSpeed(100-turn);
		Motor.A.forward();
		Motor.C.forward();
	}
	
	public void turnLeft(int turn){
		Motor.A.setSpeed(100-turn);
		Motor.C.setSpeed(300+turn);
		Motor.A.forward();
		Motor.C.forward();
	}
	
	public void stop(){
		Motor.A.stop();
		Motor.C.stop();
	}
	
	public void followLine(){
		boolean turn = true;
		int numRight=0, numLeft=0;
		while(!Button.ESCAPE.isDown()){
			/* When robot saw the main color, he goes left*/
			Color color = colorSelector.getColorFromSensor();
			ColorRGB c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
			if(colorSelector.isColorStopped(c)){
				stop();
			} else {
				while(!colorSelector.isColorFollowed(c) && turn){
					numLeft =0;
					numRight++;
					if(numRight>100) stop();
					else turnRight(numRight);
					Color color2 = colorSelector.getColorFromSensor();
					ColorRGB c2 = new ColorRGB(color2.getRed(),color2.getGreen(),color2.getBlue());
					if(colorSelector.isColorFollowed(c2)){
						turn = !turn;
					}
					
				}
				while(!colorSelector.isColorFollowed(c) && !turn){
					numRight =0;
					numLeft++;
					if(numLeft>100) stop();
					else turnLeft(numLeft);
					Color color2 = colorSelector.getColorFromSensor();
					ColorRGB c2 = new ColorRGB(color2.getRed(),color2.getGreen(),color2.getBlue());
					if(!colorSelector.isColorFollowed(c2)){
						turn = !turn;
					}
				}
			}
		}
	}

}
