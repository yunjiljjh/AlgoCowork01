package WordSearch;

public class main {

	public static void main(String[] args) {
		// Read
		InputReader reader=new InputReader("C:\\hw1\\input.txt");
		
		Solve s = new Solve(reader.dictionary, reader.words, reader.N,reader.M);
		
		//Write
		OutputWriter wr = new OutputWriter();
		wr.print(s.result);
	}

}
