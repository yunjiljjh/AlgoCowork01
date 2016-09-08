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
		public int N;
		public int M;
		
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
			String[] a = s.split(" ");
			N = Integer.parseInt(a[0]);
			M = Integer.parseInt(a[1]);
			
			while((s=br.readLine())!=null){
				int row = 0;
				char[] c = s.toCharArray();
				for (int i=0; i<N; i++){
					dictionary[row][i] = c[i];
				}
				row++;
			}
			while((s=br.readLine())!=null){
				int row = 0;
				char[] c = s.toCharArray();
				for (int j=0; j<M; j++){
					words[row][j] = c[j];
				}
				row++;
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
