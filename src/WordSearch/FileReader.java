package WordSearch;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class FileReader {
	private FileReader fr;
	private BufferedReader br;
	
	private int pointer;
	
	public FileReader(String path){
		pointer=0;
		read(path);
	}
	private void read(String path){
		try{
		fr=new FileReader(path);
		br=new BufferedReader(fr);

	
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
}