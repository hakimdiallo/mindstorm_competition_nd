public class ColorRGB {
	private int red;
	private int green;
	private int blue;
	
	public ColorRGB(int r, int g, int b){
		red = r;
		green = g;
		blue = b;
	}
	
	public int getRed(){
		return red;
	}
	
	public int getGreen(){
		return green;
	}
	
	public int getBlue(){
		return blue;
	}
	
	public double distance(ColorRGB c){
		double d = Math.sqrt((red-c.getRed())*(red-c.getRed())+(green-c.getGreen())*(green-c.getGreen())+(blue-c.getBlue())*(blue-c.getBlue()));
		return d;
	}
}

