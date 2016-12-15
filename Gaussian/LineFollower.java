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
	
	public void followLine(){
		while(!Button.ESCAPE.isDown()){
			/* When robot saw the main color, he goes left*/
			Color color = colorSelector.getColorFromSensor();
			ColorRGB c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
			if(colorSelector.isColorFollowed(c)){
				Motor.A.setSpeed(300);
				Motor.C.setSpeed(100);
				Motor.A.forward();
				Motor.C.forward();
			}
			/*when robot is out of line, he goes right*/
			else{
				Motor.C.setSpeed(300);
				Motor.A.setSpeed(100);
				Motor.A.forward();
				Motor.C.forward();
			}
		}
	}

}
