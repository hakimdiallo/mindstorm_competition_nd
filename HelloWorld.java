import lejos.nxt.*;

public class HelloWorld {
  public static void main (String[] args) {
    System.out.println("Hello World");
    ColorHTSensor cs = new ColorHTSensor(SensorPort.S1);
    for (int i=0;i<2 ;i++ ) {
      Color color = cs.readColor();
      System.out.println("R: "+color.getRed()+" G: "+color.getGreen()+" B: "+color.getBlue());
      Button.waitForAnyPress();
    }
  }
}
