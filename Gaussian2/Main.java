package Gaussian2;

import java.io.IOException;
import lejos.nxt.*;
import lejos.nxt.addon.ColorHTSensor;
import lejos.robotics.*;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) { 
		try{
		ReadWriteColors rwc= new ReadWriteColors();
		ColorHTSensor cs = new ColorHTSensor(SensorPort.S1);
		rwc.openWriter();
		System.out.println("Begin write file...");
		System.out.println("Color 1");
		for(int i=0;i<4;i++){
			Button.waitForAnyPress();
			Color color = cs.getColor();
			ColorRGB c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
			rwc.writeColors(c);
			
		}
		System.out.println("Color 2");
		for(int i=0;i<4;i++){
			Button.waitForAnyPress();
			Color color = cs.getColor();
			ColorRGB c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
			rwc.writeColors(c);
		}
		System.out.println("Color 3");
		for(int i=0;i<4;i++){
			Color color = cs.getColor();
			ColorRGB c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
			rwc.writeColors(c);
			Button.waitForAnyPress();
		}
		System.out.println("Color 4");
		for(int i=0;i<4;i++){
			Color color = cs.getColor();
			ColorRGB c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
			rwc.writeColors(c);
			Button.waitForAnyPress();
		}
		System.out.println("Color 5");
		for(int i=0;i<4;i++){
			Color color = cs.getColor();
			ColorRGB c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
			rwc.writeColors(c);
			Button.waitForAnyPress();
		}
		System.out.println("Color 6");
		for(int i=0;i<4;i++){
			Color color = cs.getColor();
			ColorRGB c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
			rwc.writeColors(c);
			Button.waitForAnyPress();
		}
		rwc.closeWriter();
		System.out.println("Color to test...");
		Button.waitForAnyPress();
		
		Color color = cs.getColor();
		ColorRGB c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
		Sound.beep();
		double[] d = rwc.readColors(c,6);
		
		int s = rwc.detectColor(d);
		Button.waitForAnyPress();
		System.out.println("Color detected: "+s);
		Button.waitForAnyPress();
		//
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
