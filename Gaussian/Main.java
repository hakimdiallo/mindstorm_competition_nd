import java.util.ArrayList;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReadWriteColors rwc= new ReadWriteColors();
		Sample s = rwc.readColors();
		ColorRGB test = new ColorRGB(61,122,83);
		s.minDistance(test);
	}
	
	
}
