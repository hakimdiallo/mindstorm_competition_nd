import lejos.nxt.Button;
import lejos.nxt.Motor;

public class Move1 {
  public static void main (String[] args) {
    System.out.println("Moteur mouvement");
    Motor.A.forward();
	Motor.C.forward();
    Button.waitForAnyPress();
    Motor.A.backward();
    Motor.C.backward();
    Button.waitForAnyPress();
    Motor.A.stop();
    Motor.C.stop();

  }
}

