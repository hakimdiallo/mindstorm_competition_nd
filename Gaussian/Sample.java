package Gaussian;
import java.util.ArrayList;


public class Sample {
	private ArrayList<ColorRGB> list;
	
	public Sample(){
		list = new ArrayList<ColorRGB>();
	}
	
	public void addColor(ColorRGB c){
		list.add(c);
	}
	
	public int size(){
		return list.size();
	}
	
	public String toString(){
		String s= "";
		int i=0;
		while(!list.isEmpty()){
			s = s+list.get(i)+"-";
			i++;
		}
		return s;
	}
	
	public ColorRGB get(int i){
		return list.get(i);
	}
}	
