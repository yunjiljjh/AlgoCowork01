package WordSearch;

	
	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;



	public class InputReader {
		private FileReader fr;
		private BufferedReader br;
		
		private int pointer;
		
		public int n;
		public int m;
		public char dictionary[][];
		public char words[][];
		
		public InputReader(String path){
			pointer=0;
			read(path);
		
		}
		private void read(String path){
			try{
			fr=new FileReader(path);
			br=new BufferedReader(fr);
			
			String s = null;
			
			s = br.readLine();
			char[] firstLine = s.split();
			n = Integer.parseInt(firstLine[0]);
			m = Integer.parseInt(firstLine[1]);
			
			while((s=br.readLine())!=null){
				while (s!==""){
					for (i=0; i<s.length(); i++){
						dictionary[i] = s;
					}
				}
				
				
			}
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				if(br != null){
					try{
						br.close();
					}catch(IOException e){
					}
				}
				if(fr != null){
					try{
						fr.close();
					}catch(IOException e){
					}
				}
			}
		}
		
}
