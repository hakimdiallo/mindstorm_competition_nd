
import java.util.ArrayList;

public class Sample {
	private ArrayList<ColorRGB> red;
	private ArrayList<ColorRGB> green;
	private ArrayList<ColorRGB> blue;
	private ArrayList<ColorRGB> black;
	private ArrayList<ColorRGB> white;
	private ArrayList<ColorRGB> yellow;
	
	public Sample(){
		red = new ArrayList<ColorRGB>();
		green = new ArrayList<ColorRGB>();
		blue = new ArrayList<ColorRGB>();
		black = new ArrayList<ColorRGB>();
		white = new ArrayList<ColorRGB>();
		yellow = new ArrayList<ColorRGB>();
	}
	
	public void addColor(ColorRGB c){
		if(c.getName().equals("Red")){
			red.add(c);
		}
		else if(c.getName().equals("Green"))
			green.add(c);
		else if(c.getName().equals("Blue"))
			blue.add(c);
		else if(c.getName().equals("Black"))
			black.add(c);
		else if(c.getName().equals("White"))
			white.add(c);
		else if(c.getName().equals("Yellow"))
			yellow.add(c);
	}

	public void minDistance(ColorRGB c){
		//System.out.println("++++++++++++RED+++++++++++");
		GaussianParam r = new GaussianParam(red);
		double dred = r.mahalanobis(c);
		//System.out.println("+++++++++++GREEN++++++++++");
		GaussianParam g = new GaussianParam(green);
		double dgreen = g.mahalanobis(c);
		//System.out.println("+++++++++++BLUE+++++++++++");
		GaussianParam b = new GaussianParam(blue);
		double dblue = b.mahalanobis(c);
		//System.out.println("++++++++++BLACK+++++++++++");
		GaussianParam bl = new GaussianParam(black);
		double dblack = bl.mahalanobis(c);
		//System.out.println("+++++++++++WHITE+++++++++++");
		GaussianParam w = new GaussianParam(white);
		double dwhite = w.mahalanobis(c);
		//System.out.println("+++++++++YELLOW+++++++++++");
		GaussianParam y = new GaussianParam(yellow);
		double dyellow = y.mahalanobis(c);
		
		/*double dred = r.mahalanobis(c);
		double dgreen = g.mahalanobis(c);
		double dblue = b.mahalanobis(c);
		double dblack = bl.mahalanobis(c);
		double dwhite = w.mahalanobis(c);
		double dyellow = y.mahalanobis(c);*/

		if(dred<dgreen && dred<dblue && dred<dblack && dred<dwhite && dred<dyellow){
			System.out.println("Color: RED");
		}
		else if(dgreen<dred && dgreen<dblue && dgreen<dblack && dgreen<dwhite && dgreen<dyellow){
			System.out.println("Color: GREEN");
		}
		else if(dblue<dred && dblue<dgreen && dblue<dblack && dblue<dwhite && dblue<dyellow){
			System.out.println("Color: BLUE");
		}
		else if(dblack<dred && dblack<dgreen && dblack<dblue && dblack<dwhite && dblack<dyellow){
			System.out.println("Color: BLACK");
		}
		else if(dwhite<dred && dwhite<dgreen && dwhite<dblue && dwhite<dblack && dwhite<dyellow){
			System.out.println("Color: WHITE");
		}
		else {
			System.out.println("Color: YELLOW");
		}
	}

	
	public void setRed(ArrayList<ColorRGB> s){
		red = s;
	}
	public ArrayList<ColorRGB> getRed(){
		return red;
	}
	public void setGreen(ArrayList<ColorRGB> s){
		green = s;
	}
	public ArrayList<ColorRGB> getGreen(){
		return green;
	}
	public void setBlue(ArrayList<ColorRGB> s){
		blue = s;
	}
	public ArrayList<ColorRGB> getBlue(){
		return blue;
	}
	public void setBlack(ArrayList<ColorRGB> s){
		black = s;
	}
	public ArrayList<ColorRGB> getBlack(){
		return black;
	}
	public void setWhite(ArrayList<ColorRGB> s){
		white = s;
	}
	public ArrayList<ColorRGB> getWhite(){
		return white;
	}
	public void setYellow(ArrayList<ColorRGB> s){
		yellow = s;
	}
	public ArrayList<ColorRGB> getYellow(){
		return yellow;
	}

	
}	
