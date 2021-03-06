import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

class ReadWriteColors{
	private File file;
	//private final String filename = "color.txt";
	
	public ReadWriteColors(){
		file = new File("color.txt");
	}
	
	public boolean exist(){
		return file.exists();
	}
	
	public void saveColors(ArrayList<ColorRGB> colors){
		try{
			FileOutputStream fo = new FileOutputStream(file);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fo));
			for (int i = 0; i < colors.size(); i++) {
				ColorRGB c = colors.get(i);
				bw.write("NAME"+" "+c.getRed()+" "+c.getGreen()+" "+c.getBlue()+" \n");
				//bw.newLine();
			}
			bw.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList<ColorRGB> readColors(){
		ArrayList<ColorRGB> colors = new ArrayList<ColorRGB>();
		try {
			FileInputStream fi = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fi));
			String line;
			while( (line = br.readLine()) != null ){
				String[] tab = split(line);
				//System.out.println(line);
				System.out.println(tab[0]+" "+tab[1]+" "+tab[2]+" "+tab[3]);
				ColorRGB c = new ColorRGB(Integer.parseInt(tab[1]), Integer.parseInt(tab[2]), Integer.parseInt(tab[3]));
				//c.setName(tab[0]);
				colors.add(c);
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return colors;
	}
	
	public String[] split(String chaine){
		String[] tab = new String[4];
		int j = 0;
		for (int i = 0; i < chaine.length(); i++) {
			int index = chaine.indexOf(' ');
			if(index != -1){
				tab[j] = chaine.substring(0,index);
				System.out.println(chaine);
				chaine = chaine.substring(index+1);
				System.out.println(chaine);
				i = 0;
				j++;
			}else {
				//tab[j] = chaine;
			}
		}
		return tab;
	}
}