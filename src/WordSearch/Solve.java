package WordSearch;

public class Solve {
	private int [][] dictionary; // tile array given
	private int [][] words; // words you have to search
	private int [][] alphabet; // from a-z location of each alphabet in the dicationary[][]
	private int currentPointX; //current location of 'x' in (x,y) of dictionary[][]
	private int currentPointY; //current location of 'y' in (x,y) of dictionary[][]
	private int indicatingPointX;  //current location of 'x' in (x,y) of words[][]
	private int indicatingPointY;  //current location of 'y' in (x,y) of words[][]

	private int [][] startingPoint;
	private	int [][] endingPoint;
	
	    public Solve(){
	    	
	    }
		public Solve(int[][] dictionary, int[][] words, int[][] alphabet){
			this.dictionary = dictionary;
			this.words = words;
			this.alphabet = alphabet;
		}
		
}
