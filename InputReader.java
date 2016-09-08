package WordSearch;

	
	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;

	public class InputReader {
		private FileReader fr;
		private BufferedReader br;
		
		private int pointer;
		
		public char dictionary[][] = null;
		public char words[][] = null;
		
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
			final int N = Integer.parseInt(firstLine[0]);
			final int M = Integer.parseInt(firstLine[1]);
			
			while((s=br.readLine())!=null){
				int row = 0;
				s.toCharArray();
				for (int i=0; i<N; i++){
					dictionary[row][i] = s[i];
				}
				row++;
			}
			while((s=br.readLine())!=null){
				int row = 0;
				s.toCharArray();
				for (int j=0; j<M; j++){
					words[row][j] = s[j];
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
