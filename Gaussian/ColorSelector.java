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
	private static final int NBRECOLORS = 3;
	private static final int COLORFOLLOWED = 1;
	private static final int COLORSTOP = 3;
	//private static final int COLORLEFT = 3;
	
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
			System.out.println("Followed color...");
			for(int i=0;i<READTIMES;i++){
				Button.waitForAnyPress();
				color = cs.getColor();
				c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
				rwc.writeColors(c);
			}
			System.out.println("Edge color...");
			for(int i=0;i<READTIMES;i++){
				Button.waitForAnyPress();
				color = cs.getColor();
				c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
				rwc.writeColors(c);
			}
			System.out.println("Arret color...");
			for(int i=0;i<READTIMES;i++){
				Button.waitForAnyPress();
				color = cs.getColor();
				c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
				rwc.writeColors(c);
			}
			/*System.out.println("Select ENTER for other colors!!!");
			while(Button.ESCAPE.isDown()){
				System.out.println("Color "+"...");
				for(int i=0;i<4;i++){
					Button.waitForAnyPress();
					color = cs.getColor();
					c = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
					rwc.writeColors(c);
				}
				//numColor++;
				System.out.println("Color saved");
			}*/
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
	
	public boolean isColorStopped(ColorRGB col){
		return rwc.detectColor(rwc.readColors(col, NBRECOLORS)) == COLORSTOP;
	}
	/*public boolean isColorLeft(ColorRGB col){
		return rwc.detectColor(rwc.readColors(col, NBRECOLORS)) == COLORLEFT;
	}
	
	public boolean isColorRight(ColorRGB col){
		return rwc.detectColor(rwc.readColors(col, NBRECOLORS)) == COLORRIGHT;
	}*/
	
	public boolean colorsAreSaved(){
		return rwc.fileExist();
	}
}
