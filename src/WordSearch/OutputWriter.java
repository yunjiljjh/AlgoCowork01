package WordSearch;

import java.io.FileWriter;
import java.io.IOException;

public class OutputWriter {
	private String path;
	private FileWriter fw;
	private StringBuilder sb;
	public OutputWriter(String path){
		this.path=path;
		sb=new StringBuilder();
	}
	public void write(String str){
		if(!str.equals("\n"))sb.append(str+" ");
		else sb.append(str); 
	}
	
	public void writeFile(){
		
		try{
			fw=new FileWriter(path,true);
			fw.write(sb.toString());
			fw.close();
			sb=new StringBuilder();
		}catch(IOException e){
			e.printStackTrace();
		}		 
	}
}
