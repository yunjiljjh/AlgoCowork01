package WordSearch;

	
	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;

	public class InputReader {
		private FileReader fr;
		private BufferedReader br;
		
		public char dictionary[][] = null;
		public char words[][] = null;
		public int N;
		public int M;
		
		public InputReader(String path){
			read(path);
		}
		
		private void read(String path){
			try{
			fr=new FileReader(path);
			br=new BufferedReader(fr);
			
			String s = null;
			int rowDic = 0;
			int rowWord = 0;
			
			// reads the first line and set N, M
			s = br.readLine();
			String[] a = s.split(" ");
			N = Integer.parseInt(a[0]);
			M = Integer.parseInt(a[1]);
			
			dictionary = new char[N][N];
			words = new char[M][];
			
			/* set dictionary[][]. Remove whitespaces. Put each character of the lines to dictionary. 
			 * rowDic is pointer.
			 */
			while((s=br.readLine())!=null){
				String[] ss = s.split(" ");
				char[] c = new char[ss.length];
				for (int i=0; i<N; i++){
					c[i] = ss[i].charAt(0);
				}
				for (int i=0; i<N; i++){
					dictionary[rowDic][i] = c[i];
				}
				rowDic++;
				if (rowDic == N) break;
			}
			while((s=br.readLine())!=null){
				if (rowWord==M-1) break;
				char[] c = s.toCharArray();
				words[rowWord] = new char[c.length];
				for (int j=0; j<c.length; j++){
					words[rowWord][j] = c[j];
				}
				rowWord++;
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
