package WordSearch;

public class Solve {
	private int N; //size of N in N*N dictionary array
	private int M; //number of words to be searched
	private char [][] dictionary; // tile array given
	private char [][] words; // words you have to search
	
//test
	private int alphabetTest = 0;
	
	private Alphabet[] alpha; // from a-z location of each alphabet in the dictionary[][]
	
	private int currentPointX; //current location of 'x' in (x,y) of dictionary[][]
	private int currentPointY; //current location of 'y' in (x,y) of dictionary[][]
	private int indicatingPointX;  //current location of 'x' in (x,y) of words[][]
	private int indicatingPointY;  //current location of 'y' in (x,y) of words[][]
	
	private int tempX;
	private int tempY;
	private int[][] canditLocs = new int[8][2]; // canditLocs[up][0]: deleted
	public int[][] wordsLoc; // wordsLoc[word][0]: canditLocs deleted
	public String result = "";

	public Solve(char[][] dictionary, char[][] words, int N, int M){
			alpha = new Alphabet[26];
			for (int i=0; i<alpha.length; i++){
				alpha[i] = new Alphabet();
			}

			this.N = N;
			this.M = M;
			this.dictionary = dictionary;
			fillAlpha(); //with dictionary[][], record where each alphabet is located
			this.words = words;
			this.wordsLoc = new int[M][4];
			// words[0][0]:  startinPointX, words[0][1]: startingPointY, words[0][2]: endingPointX, words[0][3]: endingPointY

			//System.out.println("beginning findWords");
			
			findWords(); 

			System.out.println("findWords completed");
			
			//return wordsLoc;
			//convert int[][] to string
			for (int i=0; i < M; i++)
			{
				if(wordsLoc[i][0] == -1){
					result += "0\n";
				}else{
					result = result + java.util.Arrays.toString(wordsLoc[i]) + '\n';
				}
				
				System.out.print("result is: ");
				System.out.println(result);
				
			}			
			
			System.out.println("result is generated");
			
		}
		
		private void fillAlpha(){
			
			System.out.print("fillAlpha is called. ");
			
			for (int i = 0 ; i < N ; i++){
				for (int j = 0 ; j < N ; j++){
					int alphaACII = (int)(dictionary[i][j]) - 97;
					alpha[alphaACII].setXY(i, j);	
				}
			}
			
			System.out.println("fillAlpha is completed");
			
		}

		
		
		private void findWords()
		{ //deleted
			
			System.out.println("findWords is started");
			
			for (int k = 0 ; k < M ; k++)
			{ //traverse M number of words
				
				System.out.print("looking for word: ");
				System.out.println(this.words[this.indicatingPointX]);

			//Search for a word in a loop
				boolean isFound = false; //flag
				char firstLetterofTheWord = words[k][0];
				int howManyinDictionary = alpha[(int)firstLetterofTheWord-97].getSize();
				
				System.out.print("into if. ");
				
				if (howManyinDictionary == 0)
			 	{//no wanted alphabet in the dictionary

					System.out.println("\n no wanted alphabet in the dictionary");

			 		wordsLoc[k][0] = -1;
			 	} else {
			 		canditLocs[0][0] = -1;
			 		
					System.out.print("\ninto while(!isFound). k is: ");
					System.out.println(k);
//		
					int cnt = 0;
					
	 				while(!isFound)
	 				{
//
	 					cnt++;
	 					
	 					if(cnt <= alpha[(int)firstLetterofTheWord-97].getSize())
	 					{
	 						System.out.printf("cnt is: %d\n", cnt);
	 						
	 						//locating potential start point
	 						this.currentPointX = alpha[(int)firstLetterofTheWord-97].getX(cnt); 
	 						this.currentPointY = alpha[(int)firstLetterofTheWord-97].getY(cnt);						

	 						System.out.printf("currentPoint is: (%d, %d)\n", this.currentPointX, this.currentPointY);

	 					} else if (cnt >= 800) {
	 						//dictionary contains more than 800 same alphabet. 
	 						this.currentPointX = (this.currentPointX + 1)% this.N;
							this.currentPointY = (this.currentPointY + 1);			
							if (this.currentPointX == N && this.currentPointY == N)
							{
								wordsLoc[k][0] = -1;
								break;
							}
						} else {
							wordsLoc[k][0] = -1;
							break;
						}
	 					//checking surrounds

	 					System.out.print("into checkX things. ");

//test
/*	 					
	 					if ( checkRight(this.currentPointX, this.currentPointY, this.indicatingPointY)  )
	 					{
	 						setCanditLocs();
	 					}
	 					if ( checkLeft(this.currentPointX, this.currentPointY)  )
	 					{
	 						setCanditLocs();
	 					}
	 					if ( checkUp(this.currentPointX, this.currentPointY) )
	 					{
	 						setCanditLocs();
	 					}
	 					if ( checkDown(this.currentPointX, this.currentPointY) )
	 					{
	 						setCanditLocs();
	 					}
	 					if ( checkUpRight(this.currentPointX, this.currentPointY) )
	 					{
	 						setCanditLocs();
	 					}
	 					if ( checkDownLeft(this.currentPointX, this.currentPointY) )
	 					{
	 						setCanditLocs();
	 					}
*/
	 					if ( checkUpLeft(this.currentPointX, this.currentPointY, this.indicatingPointY+1) )
	 					{
	 						setCanditLocs();
	 					}
/*	 					if ( checkDownRight(this.currentPointX, this.currentPointY) )
	 					{
	 						setCanditLocs();
	 					}
	*/ 					
 						System.out.println("After checkX things");
 						System.out.print("into setWordsLoc. ");

	 					//determines 
	 					setWordsLoc(k);
	 				}	 //While ended						
		 			
	 				this.indicatingPointX++;
	 				if (indicatingPointX >=20)
	 				{
	 					break;
	 				}
	 				
				 }		//for ended
			}
			
			System.out.println("findWords is completed");
		}
				 		
 		//Search canditLocs[][] and set wordsLoc
 		private void setWordsLoc(int k)
		{//return true if find the word

 			System.out.print("setWordsLoc. ");
 			
 			if (canditLocs[0][0] == -1)
 			{
 				wordsLoc[k][0] = -1;
 			}else{
 				for (int i=0; i < this.M-1; i++)
 				{
 					if (canditLocs[i][0] < canditLocs[i+1][0])
 					{
 						wordsLoc[k][2] = canditLocs[i+1][0];
 						wordsLoc[k][3] = canditLocs[i+1][1];
 					} else if (canditLocs[i][0] == canditLocs[i+1][0])
 					{
 						if (canditLocs[i][1] < canditLocs[i+1][1])
 						{
 							wordsLoc[k][2] = canditLocs[i+1][0];
 							wordsLoc[k][3] = canditLocs[i+1][1];
 						}
 					} else {
 						wordsLoc[k][2] = canditLocs[i][0];
 						wordsLoc[k][3] = canditLocs[i][1];
 					}
 					wordsLoc[k][0] = this.currentPointX;
 					wordsLoc[k][1] = this.currentPointY;
 				}

 			}

 		//for test	
			System.out.print(this.words[this.indicatingPointX]);
 			System.out.printf(" is : (%d, %d), (%d, %d)\n", k, wordsLoc[k][0], wordsLoc[k][1], wordsLoc[k][2], wordsLoc[k][3]);

 		}
 		
 		private void setCanditLocs()
 		{
 			for (int i=0; i<this.M; i++)
 			{				
 				if (canditLocs[i][0] == -1)
 				{					
 					canditLocs[i][0] = tempX;
 					canditLocs[i][1] = tempY;
 					break;
 				} 
 			}
 		}
 		
 		//return true and set tempX, tempY as lastPoint
 		
/* 		
 		private boolean checkRight(int x, int y, int alphabetTest)
 		{ 			
 			this.tempX = x;
 			this.tempY = (y+1)%N;
 			if (words[indicatingPointX][alphabetTest] < 'a' || words[indicatingPointX][alphabetTest] > 'z') 
 			{	
 				return true;
 			}
 			if ( this.dictionary[this.tempX][this.tempY] == this.words[indicatingPointX][alphabetTest] )
 			{
 				alphabetTest++;
 				return checkRight(x, tempY);
 			} else {
 				return false;
		 	}
 		}
 		private boolean checkLeft(int x, int y)
 		{
 			this.tempX = x;
 			this.tempY = (y-1)%N;
 			if (words[indicatingPointX][alphabetTest] < 'a' || words[indicatingPointX][alphabetTest] > 'z') 
 			{	
 				return true;
 			}
 			if ( this.dictionary[this.tempX][this.tempY] == this.words[indicatingPointX][alphabetTest] )
 			{
 				alphabetTest++;
 				return checkLeft(tempX, tempY);
 			} else {
 				return false;
		 	}
 		}
 		private boolean checkUp(int x, int y)
 		{
 			this.tempX = (x-1)%N;
 			this.tempY = y;
 			if (words[indicatingPointX][alphabetTest] < 'a' || words[indicatingPointX][alphabetTest] > 'z') 
 			{	
 				return true;
 			}
 			if ( this.dictionary[this.tempX][this.tempY] == this.words[indicatingPointX][alphabetTest] )
 			{
 				alphabetTest++;
 				return checkUp(tempX, tempY);
 			} else {
 				return false;
		 	}
 		}
 		private boolean checkDown(int x, int y)
 		{
 			this.tempX = (x+1)%N;
 			this.tempY = y;
 			if (words[indicatingPointX][alphabetTest] < 'a' || words[indicatingPointX][alphabetTest] > 'z') 
 			{	
 				return true;
 			}
 			if ( this.dictionary[this.tempX][this.tempY] == this.words[indicatingPointX][alphabetTest] )
 			{
 				alphabetTest++;
 				return checkDown(tempX, tempY);
 			} else {
 				return false;
		 	}
 		}
 		private boolean checkUpRight(int x, int y)
 		{
 			this.tempX = (x-1)%N;
 			this.tempY = (y+1)%N;
 			if (words[indicatingPointX][alphabetTest] < 'a' || words[indicatingPointX][alphabetTest] > 'z') 
 			{	
 				return true;
 			}
 			if ( this.dictionary[this.tempX][this.tempY] == this.words[indicatingPointX][alphabetTest] )
 			{
 				alphabetTest++;
 				return checkUpRight(tempX, tempY);
 			} else {
 				return false;
		 	}
 		}
 		private boolean checkDownLeft(int x, int y)
 		{
 			this.tempX = (x+1)%N;
 			this.tempY = (y-1)%N;
 			if (words[indicatingPointX][alphabetTest] < 'a' || words[indicatingPointX][alphabetTest] > 'z') 
 			{	
 				return true;
 			}
 			if ( this.dictionary[this.tempX][this.tempY] == this.words[indicatingPointX][alphabetTest] )
 			{
 				alphabetTest++;
 				return checkDownLeft(tempX, tempY);
 			} else {
 				return false;
		 	}
 		}
 */
//WORKING ON
 		
 		private boolean checkUpLeft(int currentPointX, int currentPointY, int indicatingPointY)
 		{
 			this.alphabetTest = indicatingPointY;
 			//test
			System.out.printf("in checkUpLeft(%d, %d, %d)\n", currentPointX, currentPointY, alphabetTest);
 			
			if (currentPointX <= currentPointY)
			{	
				this.tempX = Math.floorMod((currentPointX-1),(N-(currentPointY-currentPointX)));
				this.tempY = (currentPointY-currentPointX) + Math.floorMod((currentPointX-1),(N-(currentPointY-currentPointX)));
			} else {

				this.tempX = (currentPointX-currentPointY) + Math.floorMod((currentPointY-1),(N-(currentPointX-currentPointY)));
				this.tempY = Math.floorMod((currentPointY-1),(N-(currentPointX-currentPointY))); 

				
//				this.tempX = (currentPointX-currentPointY) + (currentPointY+1)%(N-(currentPointX-currentPointY));
//				this.tempY = (currentPointY+1)%(N-(currentPointX-currentPointY));
			}
			
			System.out.printf("tempLoc is (%d, %d)\n", this.tempX, this.tempY);
			if(this.tempX < 0 || this.tempY < 0)
			{
				System.out.println("modular");
			}
			
 			if (words[indicatingPointX][alphabetTest] < 'a' || words[indicatingPointX][alphabetTest] > 'z') 
 			{	
 				
 				System.out.println("not alphabet");
 				
				System.out.println();

 				return true;
 			}
 //WorkingonHere			
 			if ( dictionary[this.tempX][this.tempY] == words[indicatingPointX][alphabetTest] )
 			{
 				
 				System.out.println("Here?");
 				
 				alphabetTest++;
 				return checkUpLeft(tempX, tempY, alphabetTest);
 			} else {
 				
 				System.out.println("NOT MATHCED");
 				System.out.printf("\n==================\ndictionary[tempX][tempY] is: %c, words[iPX][iPY] is: %c\n=======================\n", 
 						this.dictionary[this.tempX][this.tempY], this.words[indicatingPointX][alphabetTest]);
 				
 				return false;
		 	}
 		}
/* 		
 		private boolean checkDownRight(int x, int y)
 		{
			if (currentPointX < currentPointY)
			{
				this.tempX = (currentPointX+1)%(N-(currentPointY-currentPointX));
				this.tempY = (currentPointY-currentPointX) + (currentPointX+1)%(N-(currentPointY-currentPointX));
			} else {
				this.tempX = (currentPointX-currentPointY) + (currentPointY+1)%(N-(currentPointX-currentPointY));
				this.tempY = (currentPointY+1)%(N-(currentPointX-currentPointY));
			}
 			if (words[indicatingPointX][alphabetTest] < 'a' || words[indicatingPointX][alphabetTest] > 'z') 
 			{	
 				return true;
 			}
 			if ( this.dictionary[this.tempX][this.tempY] == this.words[indicatingPointX][alphabetTest] )
 			{
 				alphabetTest++;
 				return checkDownRight(tempX, tempY);
 			} else {
 				return false;
		 	}
 		}
*/
 		
/*		private void goRight(){currentPointY =  (currentPointY+1)%N;}
		private void goLeft(){currentPointY =  (currentPointY-1)%N;}
		private void goUp(){currentPointX =  (currentPointX-1)%N;}
		private void goDown(){currentPointX =  (currentPointX+1)%N;}
		private void goUpRight(){currentPointX =  (currentPointX-1)%N; currentPointY =  (currentPointY+1)%N;}
		private void goDownLeft(){currentPointX =  (currentPointX+1)%N; currentPointY =  (currentPointY-1)%N;}
		private void goDownRight (){
			if (currentPointX < currentPointY){ 
				currentPointX = (currentPointX-1)%(N-(currentPointY-currentPointX));
				currentPointY = (currentPointY-currentPointX) + (currentPointX-1)%(N-(currentPointY-currentPointX));}
			else{
				currentPointX = (currentPointX-currentPointY) + (currentPointY-1)%(N-(currentPointX-currentPointY));
				currentPointY = (currentPointY-1)%(N-(currentPointX-currentPointY));	}
			}
		private void goUpLeft(){
			if (currentPointX < currentPointY) {
				currentPointX = (currentPointX+1)%(N-(currentPointY-currentPointX));
				currentPointY = (currentPointY-currentPointX) + (currentPointX+1)%(N-(currentPointY-currentPointX)) ; }
				else{
				currentPointX = (currentPointX-currentPointY) + (currentPointY+1)%(N-(currentPointX-currentPointY));
				currentPointY = (currentPointY+1)%(N-(currentPointX-currentPointY)) ; }
		}
*/
		
}