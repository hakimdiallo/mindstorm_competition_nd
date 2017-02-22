package Gaussian;

import java.util.TimerTask;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.Color;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Timer;
import lejos.util.TimerListener;

public class LineFollower implements TimerListener{
	private static final int MAXSPEED = 500;
	private static final int SPEED_STEP = 10;
	private static int speed_gauche, speed_droite;
	private static final int BASESPEED = 50;
	private ColorSelector colorSelector;
	private Timer timer;
	DifferentialPilot pilot ;
	
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
		//pilot.setTravelSpeed(BASESPEED);
		//pilot.forward();
			Motor.A.setSpeed(speed_gauche);
			Motor.C.setSpeed(speed_droite);
			Motor.A.forward();
			Motor.C.forward();			
	}
	
	public void followLine(){
		pilot = new DifferentialPilot(2.25f, 5.5f, Motor.A, Motor.C);
		timer = new Timer(1000, this);
		speed_gauche = BASESPEED;
		speed_droite = BASESPEED;
		int angle = 0;
		boolean turn = true;
		boolean init = false;
		while(!Button.ESCAPE.isDown()){
			/* When robot saw the main color, he goes left*/
			Color color = colorSelector.getColorFromSensor();
			ColorRGB c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
			if(colorSelector.isColorFollowed(c)){ /* When robot is in the line*/
				angle = 5;
				if (init) {
					init = false;
				}
				
				if(speed_gauche != speed_droite){
					speed_gauche = speed_droite= Math.min(speed_gauche, speed_droite);
				}
				//move();
				if(speed_gauche<MAXSPEED && speed_droite<MAXSPEED){
					speed_gauche += SPEED_STEP;
					speed_droite += SPEED_STEP;
				}
				move();
			} else { /* When robot is outlaw*/
				if (!init) {
					speed_gauche = BASESPEED;
					speed_droite = BASESPEED;
					init = true;
				}
				
				turn(angle,turn);
				turn = !turn;
				angle +=5;
			}
		}
	}
	
	public void turn(int angle, boolean left){
		pilot.setRotateSpeed(50);
		pilot.setTravelSpeed(150);
		if(left){
			pilot.rotate(angle);
			//Motor.A.rotate(angle, true);
			//Motor.C.rotate(0-angle, true);
		}else{
			//Motor.C.rotate(-2*angle, true);
			//Motor.C.rotate(angle, true);
			pilot.rotate(-angle);
		}
		
		
	}

	@Override
	public void timedOut() {
	}

}
