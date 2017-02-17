package Gaussian;


public class ColorRGB {
	private int red,green,blue;
	
	public ColorRGB(int r,int g,int b){
		red = r;
		green= g;
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
	
	public String toString(){
		return red+";"+green+";"+blue;
	}
	
	public ColorRGB addColor(ColorRGB c){
		int r = this.red + c.getRed();
		int g = this.green+ c.getGreen();
		int b = this.blue + c.getBlue();
		
		return new ColorRGB(r,g,b);
	}
	
}
