package Gaussian;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.robotics.Color;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Timer;
import lejos.util.TimerListener;

public class LineFollower implements TimerListener{
	private static final int MAXSPEED = 300;
	private static final int SPEED_STEP = 5;
	private static int speed_gauche, speed_droite;
	private static final int BASESPEED = 100;
	private ColorSelector colorSelector;
	private Timer timer;
	DifferentialPilot pilot ;
	private NXTRegulatedMotor left, right;
	private boolean gauche;
	private boolean on;
	private int delay;
	private boolean init;
	
	public LineFollower(){
		colorSelector = new ColorSelector();
		left = Motor.C;
		right = Motor.A;
		pilot = new DifferentialPilot(2.5, 5, Motor.C, Motor.A);
	}
	
	public void init(){
		colorSelector.chooseColorsAndSaveThem();
	}
	
	public boolean needColors(){
		return colorSelector.colorsAreSaved();
	}
	
	public void move(){
		left.setSpeed(speed_gauche);
		right.setSpeed(speed_droite);
		left.forward();
		right.forward();
	}
	
	public void move2(int leftPow, int rightPow){
		MotorPort.C.controlMotor(leftPow, 1);
		MotorPort.A.controlMotor(rightPow, 1);
	}
	
	
	public void followLine(){
		//timer = new Timer(1000, this);
		speed_gauche = BASESPEED;
		speed_droite = BASESPEED;
		int angle = 0;
		boolean turn = true;
		on = false;
		while(!Button.ESCAPE.isDown()){
			/* When robot saw the main color, he goes left*/
			Color color = colorSelector.getColorFromSensor();
			ColorRGB c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
			if(colorSelector.isColorFollowed(c)){ /* When robot is in the line*/
				if (on) {
					on = false;
				}
				
				angle=0;
				
				if(speed_gauche != speed_droite){
					speed_gauche = speed_droite= Math.min(speed_gauche, speed_droite);
				}
				//move();
				if(speed_gauche < MAXSPEED && speed_droite < MAXSPEED){
					speed_gauche += SPEED_STEP;
					speed_droite += SPEED_STEP;
				}
				move2(speed_gauche,speed_droite);
			} else { /* When robot is outline*/
				if (!on) {
					speed_gauche = BASESPEED;
					speed_droite = BASESPEED;
					on = true;
				}
				
				turn(angle,turn);
				turn = !turn;
				angle +=5;
			}
		}
	}
	
	public void followLine2(){
		timer = new Timer(1000, this);
		speed_gauche = BASESPEED;
		speed_droite = BASESPEED;
		//int angle = 0;
		//boolean turn = true;
		on = false;
		gauche = true;
		delay = 2000;
		init = false;
		while(!Button.ESCAPE.isDown()){
			/* When robot saw the main color, he goes left*/
			Color color = colorSelector.getColorFromSensor();
			ColorRGB c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
			if(colorSelector.isColorFollowed(c)){ /* When robot is in the line*/
				if (on) {
					gauche=true;
					on = false;
					delay = 2000;
					timer.stop();
					init = false;
				}
				
				if(speed_gauche != speed_droite){
					speed_gauche = speed_droite= Math.min(speed_gauche, speed_droite);
				}
				//move();
				if(speed_gauche < MAXSPEED && speed_droite < MAXSPEED){
					speed_gauche += SPEED_STEP;
					speed_droite += SPEED_STEP;
				}
				//move();
			} else { /* When robot is outline*/
				if (!on) {
					on = true;
					timer.setDelay(delay);
					timer.start();
				}
				if (gauche) {
					System.out.println("A gauche!\n");
					if (!init) {
						speed_gauche = BASESPEED;
						speed_droite = BASESPEED * 3;
						init = true;
					}
					//speed_droite += SPEED_STEP;
					//speed_gauche += SPEED_STEP;
				}else{
					System.out.println("A droite!\n");
					if (!init) {
						speed_gauche = BASESPEED * 3;
						speed_droite = BASESPEED;
						init = true;
					}
					//speed_gauche += SPEED_STEP;
					//speed_droite += SPEED_STEP;
				}
			}
			move();
		}
	}
	
	public void followLine3(){
		while(!Button.ESCAPE.isDown()){
			/* When robot saw the main color, he goes left*/
			Color color = colorSelector.getColorFromSensor();
			ColorRGB c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
			if(colorSelector.isColorFollowed(c)){ /* When robot is in the line*/
				move2(80,0);
			} else { /* When robot is outline*/
				move2(0,80);
			}
		}
	}
	
	public void followLine5(){
		int countDroite = 0;
		int countGauche = 0;
		speed_droite = BASESPEED;
		speed_droite = BASESPEED;
		while(!Button.ESCAPE.isDown()){
			/* When robot saw the main color, he goes left*/
			Color color = colorSelector.getColorFromSensor();
			ColorRGB c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
			if(colorSelector.isColorFollowed(c)){ /* When robot is in the line*/
				if (countGauche != 0 && countDroite == countGauche) {
					move();
					countDroite = countGauche = 0;
				}else {
					move2(80,0);
					countGauche++;
				}
				
			} else { /* When robot is outline*/
				move2(0,80);
				countDroite++;
			}
			System.out.println("Gauche: "+countGauche+" Droite: "+countDroite);
		}
	}
	
	public void followLine6(){
		//timer = new Timer(1000, this);
		speed_gauche = BASESPEED;
		speed_droite = BASESPEED;
		int angle = 0;
		boolean turn = true;
		int lim = 50;
		on = false;
		while(!Button.ESCAPE.isDown()){
			/* When robot saw the main color, he goes left*/
			Color color = colorSelector.getColorFromSensor();
			ColorRGB c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
			if(colorSelector.isColorFollowed(c)){ /* When robot is in the line*/
				if (on) {
					on = false;
				}
				
				angle=0;
				
				if(speed_gauche != speed_droite){
					speed_gauche = speed_droite= Math.min(speed_gauche, speed_droite);
				}
				//move();
				if(speed_gauche < MAXSPEED && speed_droite < MAXSPEED){
					speed_gauche += SPEED_STEP;
					speed_droite += SPEED_STEP;
				}
				move2(speed_gauche,speed_droite);
			} else { /* When robot is outline*/
				if (!on) {
					speed_gauche = BASESPEED;
					speed_droite = BASESPEED;
					on = true;
				}
				
				turn(angle,turn);
				try {
					Thread.sleep(lim);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				turn = !turn;
				lim = lim+500;
				angle +=5;
			}
		}
	}
	
	public void followLine7(){
		int err = 0;
		int i =1;
		while(!Button.ESCAPE.isDown()){
			/* When robot saw the main color, he goes left*/
			Color color = colorSelector.getColorFromSensor();
			ColorRGB c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
			if(colorSelector.isColorFollowed(c)){ /* When robot is in the line*/
				move2(80,10);
			} else { /* When robot is outline*/
					move2(10,80);
			}
		}
	}
	
	public void turn(int angle, boolean left){
		//pilot.setRotateSpeed(50);
		//pilot.setTravelSpeed(150);
		if(left){
			move2(0,70+angle);
		}else{
			move2(70+angle,0);
		}
	}

	@Override
	public void timedOut() {
		System.out.println("Time is "+delay+"\n");
		gauche = !gauche;
		//on = false;
		delay += 2000;
		on = false;
		init = false;
	}

}
