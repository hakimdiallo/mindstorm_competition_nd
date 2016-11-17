package Sensor;

import lejos.nxt.*;
import lejos.nxt.addon.ColorHTSensor;
import lejos.robotics.*;

public class ColorSensors2 {
	public static void main(String args[]){
		ColorHTSensor cs= new ColorHTSensor(SensorPort.S1);
		
		for(int i =0; i<15; i++){
			Color color = cs.getColor();
			int r = color.getRed();
			int g = color.getGreen();
			int b = color.getBlue();
			System.out.println("RGB:"+ r+","+g+","+b+")");
			if(r>200 && g>40 && g<80 && b>20 && b<60){
				System.out.println("Couleur detecte: RED");
			}
			else if(r>20 && r<130 && g>70 && g<220 && b>60 && b<120){//ok
				System.out.println("Couleur detecte: GREEN");
			}
			else if(r>20 && r<70 && g>50 && g<100 && b>95 && b<190){
				System.out.println("Couleur detecte: BLUE");
			}
			else if(r>190 && g>230 && b>250){
				System.out.println("Couleur detecte: WHITE");
			}
			else if(r<60 && g<70 && b<70){
				System.out.println("Couleur detecte: BLACK");
			}
			else if(r>190 && g>160 && b>50 && b<180){//ok
				System.out.println("Couleur detecte: YELLOW");
			}
			else if(r>170 && g>80 && g<140 && b>40 && b<70){
				System.out.println("Couleur detecte: ORANGE");
			}
			else if(r>20 && r<70 && g>90 && g<120 && b>90){
				System.out.println("Couleur detecte: CYAN");
			}
			else if(r>100 && r<200 && g>30 && g<100 && b>40 && b<120){
				System.out.println("Couleur detecte: PINK");
			}
			else if(r>60 && r<100 && g>40 && g<100 && b>80 && b<130){
				System.out.println("Couleur detecte: MAGETA");
			}
			else if(r>90 && r<170 && g>70 && g<190 && b>50 && b<180){
				System.out.println("Couleur detecte: GRAY");
			}
			else {
				System.out.println("Couleur non detecte!");
			}
			
			Button.waitForAnyPress();
		}
		
	}

}

