package Gaussian;

import java.io.IOException;

import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.ColorHTSensor;
import lejos.robotics.Color;

public class ColorSelector {
	private ReadWriteColors rwc;
	private ColorHTSensor cs;
	private Color color;
	private ColorRGB c;
	private static final int READTIMES = 4;
	private static final int NBRECOLORS = 2;
	private static final int COLORFOLLOWED = 1;

	public ColorSelector(){
		try {
			rwc = new ReadWriteColors();
		} catch (IOException e) {
			e.printStackTrace();
		}
		cs = new ColorHTSensor(SensorPort.S1);
	}

	public void chooseColorsAndSaveThem(){
		try {
			rwc.openWriter();
			System.out.println("Select the Color to follow...");
			for(int i=0;i<READTIMES;i++){
				Button.waitForAnyPress();
				color = cs.getColor();
				c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
				rwc.writeColors(c);
			}
			System.out.println("Select other colors...");
			for(int i=0;i<READTIMES;i++){
				Button.waitForAnyPress();
				color = cs.getColor();
				c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
				rwc.writeColors(c);
			}
			rwc.closeWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Color getColorFromSensor(){
		return cs.getColor();
	}

	public boolean isColorFollowed(ColorRGB col){
		return rwc.detectColor(rwc.readColors(col, NBRECOLORS)) == COLORFOLLOWED;
	}

	public boolean colorsAreSaved(){
		return rwc.fileExist();
	}
}
