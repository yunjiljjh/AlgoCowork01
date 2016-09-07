package WordSearch;

import java.io.FileWriter;
import java.io.IOException;

public class OutputWriter {
	private FileWriter fw;

	public void print(String str){
		try{
			fw=new FileWriter("C:\\hw1\\2013198070.txt",true);
			fw.write(str);
			fw.close();
		}catch(IOException e){}
		
	}
}
