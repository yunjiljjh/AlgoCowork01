package WordSearch;

	
	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;



	public class InputReader {
		private FileReader fr;
		private BufferedReader br;
		
		private int pointer;
		
		public InputReader(String path){
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
