package Gaussian;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.Color;
import lejos.util.Timer;
import lejos.util.TimerListener;

public class LineFollower implements TimerListener{
	private ColorSelector colorSelector;
	private final int SPEED_MAX = 300;
	private final int SPEED_BASE = 100;
	private int speed_right;
	private int speed_left;
	private final int STEP_SPEED = 10;
	private Timer timer;
	private boolean start;
	
	public LineFollower(){
		colorSelector = new ColorSelector();
	}
	
	public void init(){
		colorSelector.chooseColorsAndSaveThem();
	}
	
	public boolean needColors(){
		return colorSelector.colorsAreSaved();
	}
	
	public void move(){
		Motor.A.setSpeed(speed_right);
		Motor.C.setSpeed(speed_left);
		Motor.A.forward();
		Motor.C.forward();
	}
	
	public void followLine(){
		timer = new Timer(1000*60, this);
		start = false;
		speed_right = SPEED_BASE;
		speed_left = SPEED_BASE;
		int counter = 0;
		int duration = 10;
		boolean turn = true;
		while(!Button.ESCAPE.isDown()){
			/* When robot saw the main color, he goes left*/
			Color color = colorSelector.getColorFromSensor();
			ColorRGB c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
			if(colorSelector.isColorFollowed(c)){
				if(start){
					timer.stop();
					start = false;
					counter = 0;
					duration = 10;
				}
				if (speed_left != speed_right) {
					speed_left = speed_right = Math.min(speed_left, speed_right);
				}
				move();
				if (speed_left < SPEED_MAX && speed_right < SPEED_MAX) {
					speed_left += STEP_SPEED;
					speed_right += STEP_SPEED;
				}
			}
			/*when robot is out of line, he goes right*/
			else{
				if(!start){
					timer.start();
					start = true;
				}
				if(counter < duration && turn){
					if (speed_left > speed_right) {
						speed_left = speed_right;
					}
					speed_left -= STEP_SPEED;
					counter++;
					if(counter == duration){
						turn = false;
						duration = duration * 2;
					}
				}
				else{
					if (speed_right > speed_left) {
						speed_right = speed_left;
					}
					speed_right += STEP_SPEED;
					counter--;
					if (counter == 0) {
						turn = false;
					}
				}
				
			}
		}
	}

	@Override
	public void timedOut() {
		Motor.A.stop();
		Motor.C.stop();
	}

}
