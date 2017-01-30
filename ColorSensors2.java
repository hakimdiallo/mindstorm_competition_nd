import ColorRGB;
import lejos.nxt.*;
import lejos.nxt.addon.ColorHTSensor;
import lejos.robotics.*;

public class ColorSensors2 {
	
	public static void colorRecognize(ColorRGB c){
		/* Definir les couleurs pour reconnaitre*/
		ColorRGB black = new ColorRGB(0,0,0);
		/* White range*/
		ColorRGB white = new ColorRGB(255,255,255);
		/* Red range*/
		ColorRGB red = new ColorRGB(255,0,0);
		ColorRGB maroon = new ColorRGB(128,0,0);
		/* Green range */
		ColorRGB lime = new ColorRGB(0,255,0);
		ColorRGB green = new ColorRGB(0,128,0);
		/* Blue range*/
		ColorRGB blue = new ColorRGB(0,0,255);
		ColorRGB navy = new ColorRGB(0,0,128);
		/* Yellow range*/
		ColorRGB yellow = new ColorRGB(255,255,0);
		ColorRGB orange = new ColorRGB(255,165,0);

		double d[] = new double[10];
		d[0] = c.distance(black);
		
		d[1] = c.distance(white);
		
		d[2] = c.distance(red);
		d[3] = c.distance(maroon);
		
		d[4] = c.distance(lime);
		d[5] = c.distance(green);
		
		d[6] = c.distance(blue);
		d[7] = c.distance(navy);
		
		d[8] = c.distance(yellow);
		d[9] = c.distance(orange);
		
		double min = d[0];
		
		for(int i=1;i<10;i++){
			if(min>=d[i]) {
				min = d[i];
			}
		}
		
		if(d[0]==min){
			System.out.println("Color: BLACK");
		}
		else if(d[1]==min){
			System.out.println("Color: WHITE");
		}
		else if(d[2]==min || d[3]==min){
			System.out.println("Color: RED");
		}
		else if(d[4]==min || d[5]==min){
			System.out.println("Color: GREEN");
		}
		else if(d[6]==min || d[7]==min){
			System.out.println("Color: BLUE");
		}
		else if(d[8]==min || d[9]==min){
			System.out.println("Color: YELLOW");
		}
		else {
			System.out.println("Color non connu!!!");
		}
	}
	public static void main(String args[]){
		ColorHTSensor cs= new ColorHTSensor(SensorPort.S1);
		
		for(int i =0; i<15; i++){
			Color color = cs.getColor();
			ColorRGB colorRGB = new ColorRGB(color.getRed(),color.getGreen(),color.getBlue());
			System.out.println("RGB: "+color.getRed()+";"+color.getGreen()+";"+color.getBlue());
			colorRecognize(colorRGB);
			Button.waitForAnyPress();
		}
		
	}

}

