package WordSearch;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputReader reader=new InputReader("C:\\hw1\\input.txt");
		Solve s = new Solve(reader.dictionary, reader.words, reader.N,reader.M);
	}

}
