package Gaussian;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import lejos.nxt.Sound;

class ReadWriteColors{
	private File file;
	private final String filename = "color.dat";
	private FileOutputStream fo;
	private DataOutputStream bw;
	
	public ReadWriteColors() throws IOException{
		file = new File(filename);
		if(!file.exists()){
			file.createNewFile();
		}
	}
	
	public void openWriter() throws IOException{
		fo = new FileOutputStream(file);
		bw= new DataOutputStream(fo);
	}
	
	public void closeWriter() throws IOException{
		bw.close();
	}
	
	public boolean fileExist(){
		return file.exists();
	}
	
	public void writeColors(ColorRGB c){
		try{
			bw.writeInt(c.getRed());
			bw.writeInt(c.getGreen());
			bw.writeInt(c.getBlue());
			Sound.beep();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	public double[] readColors(ColorRGB cc, int numColor){
		Sample s= null;
		ColorRGB c;
		double[] distances = new double[numColor];
		double d=0;
		GaussianParam g;
		int index = 0; // For array distances 
		int i=0;
		try {
			FileInputStream fi = new FileInputStream(file);
			DataInputStream br = new DataInputStream(fi);
			while(br.available()>4){
				s = new Sample();
				while(i<4){
					c = new ColorRGB(br.readInt(), br.readInt(),br.readInt());
					s.addColor(c);
					i++;
				}
			    g = new GaussianParam(s);
				d = g.mahalanobis(cc);
				i=0;
				distances[index++]=d;
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return distances;
	}
	
	public int detectColor(double[] d){
		double min = d[0];
		int index=0;
		for(int i=1;i<d.length;i++){
			if(min>d[i]){
				min = d[i];
				index = i;
			}
		}
		return index+1;
	}
}