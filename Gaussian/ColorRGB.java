
public class ColorRGB {
	private int red,green,blue;
	private String name;
	
	public ColorRGB(int r,int g,int b){
		red = r;
		green= g;
		blue = b;
	}
	
	public void setName(String n){
		name = n;
	}
	
	public String getName(){
		return name;
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
	
	public ColorRGB addColor(ColorRGB c){
		int r = this.red + c.getRed();
		int g = this.green+ c.getGreen();
		int b = this.blue + c.getBlue();
		
		return new ColorRGB(r,g,b);
	}
	
}
