package Sensor;

import lejos.nxt.*;
import lejos.nxt.addon.ColorHTSensor;
import lejos.robotics.*;

public class ColorSensors2 {
	public static void main(String args[]){
		ColorHTSensor cs= new ColorHTSensor(SensorPort.S2);
		
		for(int i =0; i<10; i++){
			Color color = cs.getColor();
			System.out.println("RGB =("+ color.getRed()+","+color.getGreen()+","+color.getBlue()+")");
			int verif = cs.getColorID();
			switch (verif){
			case 0:
				System.out.println("Couleur detecte: RED");
				break;
			case 1:
				System.out.println("Couleur detecte: GREEN");
				break;
			case 2:
				System.out.println("Couleur detecte: BLUE");
				break;
			case 3:
				System.out.println("Couleur detecte: YELLOW");
				break;
			case 4:
				System.out.println("Couleur detecte: MAGENTA");
				break;
			case 5:
				System.out.println("Couleur detecte: ORANGE");
				break;
			case 6:
				System.out.println("Couleur detecte: WHITE");
				break;
			case 7:
				System.out.println("Couleur detecte: BLACK");
				break;
			case 8:
				System.out.println("Couleur detecte: PINK");
				break;
			case 9:
				System.out.println("Couleur detecte: GRAY");
				break;
			case 10:
				System.out.println("Couleur detecte: LIGHT GRAY");
				break;
			case 11:
				System.out.println("Couleur detecte: DARK GRAY");
				break;
			case 12:
				System.out.println("Couleur detecte: CYAN");
				break;
			default:
				System.out.println("Couleur non detecte!!!");
					
			}
			
			Button.waitForAnyPress();
		}
		
	}

}

