package Gaussian;

import java.io.IOException;

import lejos.nxt.Button;
import lejos.robotics.navigation.DifferentialPilot;

public class Main {
	public static DifferentialPilot pilot;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {
		LineFollower lf = new LineFollower();
		if(lf.needColors()){
			System.out.println("Press Enter to start saving colors...");
			Button.ENTER.waitForPress();
			lf.init();
			Button.ENTER.waitForPress();
			lf.followLine();
		}
	}


}
