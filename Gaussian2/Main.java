package Gaussian2;

import java.io.IOException;
import lejos.nxt.*;
import lejos.nxt.addon.ColorHTSensor;
import lejos.robotics.*;
import lejos.robotics.navigation.DifferentialPilot;

public class Main {
	public static DifferentialPilot pilot;

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) { 
		Color color;
		ColorRGB c;
		int numColor =2;
		try{
			ReadWriteColors rwc= new ReadWriteColors();
			ColorHTSensor cs = new ColorHTSensor(SensorPort.S1);
			rwc.openWriter();
			System.out.println("Color 1...");
			for(int i=0;i<4;i++){
				Button.waitForAnyPress();
				color = cs.getColor();
				c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
				rwc.writeColors(c);
			}
			System.out.println("Color 2...");
			for(int i=0;i<4;i++){
				Button.waitForAnyPress();
				color = cs.getColor();
				c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
				rwc.writeColors(c);
			}
			System.out.println("Select ENTER for other colors!!!");
			while(!Button.ESCAPE.isDown()){
				//if(Button.ENTER.isUp()){
				Button.waitForAnyPress();
				if(Button.ESCAPE.isDown()) break;
					System.out.println("Color "+(numColor+1)+"...");
					for(int i=0;i<4;i++){
						Button.waitForAnyPress();
						color = cs.getColor();
						c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
						rwc.writeColors(c);
					}
					numColor++;
					System.out.println("Color saved");
					//
			}
					
					/*System.out.println("Color 3...");
					for(int i=0;i<4;i++){
						Button.waitForAnyPress();
						color = cs.getColor();
						c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
						rwc.writeColors(c);
					}
					//numColor++;
					//System.out.println("Color "+numColor+" saved...");
				//}
			//}*/
			rwc.closeWriter();
			

			Button.waitForAnyPress();
			System.out.println("Button pressed...");

			//pilot = new DifferentialPilot(1.25f, 4.25f, Motor.A, Motor.C);
			boolean position = false;
			int e = 0;
			while(!Button.ESCAPE.isDown()){
				/* When robot saw the main color, he goes left*/
				color = cs.getColor();
				c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
				if(rwc.detectColor(rwc.readColors(c, numColor))==1){
					Motor.A.setSpeed(300);
					Motor.C.setSpeed(100);
					Motor.A.forward();
					Motor.C.forward();
					//color = cs.getColor();
					//c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
					e++;
					if(e > 2){
						position = true;
					}
				}
				e=0;
				/*when robot is out of line, he goes right*/
				if(rwc.detectColor(rwc.readColors(c, numColor))!=1){
					//Motor.A.setSpeed(100);
					Motor.C.setSpeed(300);
					Motor.A.setSpeed(100);
					Motor.A.forward();
					Motor.C.forward();
					//color = cs.getColor();
					//c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
					e++;
					if(e > 2){
						position = false;
					}
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
