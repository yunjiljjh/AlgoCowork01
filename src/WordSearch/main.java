/* (CSI 3108-01) Algorithm Analysis class HW1
 * Lee, Yun Ji	(2013198070)  	 
 * Jeong, Da Som(2012127028)	
 * Nam, Hyo Rim (2013147531)
 * 2016 Fall */
/*main*/
package WordSearch;

public class main {

	public static void main(String[] args) {
		// Read
		InputReader reader=new InputReader("C:\\hw1\\input.txt");
		
		//Solve
		Solve s = new Solve(reader.dictionary, reader.words, reader.N,reader.M);

		OutputWriter wr = new OutputWriter();
		//write
		wr.print(s.result);
	}

}
