import java.util.ArrayList;

public class TestRWColors {

	public static void main(String[] args) {
		/*ReadWriteColors rwc = new ReadWriteColors();
		ArrayList<ColorRGB> colors = new ArrayList<ColorRGB>();
		ColorRGB c1  = new ColorRGB(255, 255, 255);
		ColorRGB c2  = new ColorRGB(255, 255, 33);
		ColorRGB c3  = new ColorRGB(255, 0, 0);
		ColorRGB c4  = new ColorRGB(0, 0, 0);
		colors.add(c1);
		colors.add(c2);
		colors.add(c3);
		colors.add(c4);
		rwc.saveColors(colors);
		ArrayList<ColorRGB> reads = rwc.readColors();
		System.out.print(reads);*/
		int[][] tab = new int[3][3];
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab.length; j++) {
				System.out.println(tab[i][j]);
			}
			
		}
	}

}
