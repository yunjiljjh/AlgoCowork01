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
	public String result = ""; //the output

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
			wordsLoc = new int[this.M][4]; // words[M] = Mth word's [startinPointX, startingPointY, endingPointX, endingPointY]
			for (int i = 0; i < 8; i++){canditLocs[i][0] = -1;} // initializing canditLocs
			
			findWords(); 

			System.out.println("findWords completed");
			
			//converts wordsLoc to string
			for (int i=0; i < this.M; i++)
			{
				if(wordsLoc[i][0] == -1){
					result += "0\n";
				}else{
					for (int j=0; j < 4; j++)
					{
						result = result + wordsLoc[i][j] + " ";
					}
					
					result = result + "\n";
					
					System.out.printf("result is: %s", result);

				}
			}			
			System.out.print("generated result is: ");
			System.out.print(result);			
		}
		
		private void fillAlpha(){			
			for (int i = 0 ; i < this.N ; i++){
				for (int j = 0 ; j < this.N ; j++){
					int alphaASCII = (int)(this.dictionary[i][j]) - 97;
					alpha[alphaASCII].setXY(i, j);	
				}
			}
		}

		
		//Find all words and store it in wordsLoc['word'] = [startingX, startingY, endingX, endingY]
		private void findWords()
		{
			for (int k = 0 ; k < M ; k++)
			{ //traverse M number of words
			//Search for a word in a loop
				boolean isFound = false; //For while loop: if the word is found
				char firstLetterofTheWord = words[k][0];
				int howManyinDictionary = alpha[(int)firstLetterofTheWord-97].getSize();
				
				if (howManyinDictionary == 0)
			 	{//no wanted alphabet in the dictionary
			 		wordsLoc[k][0] = -1;
			 	} else {
			 		int cnt = 0; //if all registered locations of the alphabet is looked up					
	 				while(!isFound) //iterates until the word is found or look through all possible locations
	 				{
	 					cnt++;	 					
	 					if(cnt <= alpha[(int)firstLetterofTheWord-97].getSize())
	 					{	//locating potential start point
	 						currentPointX = alpha[(int)firstLetterofTheWord-97].getX(cnt); 
	 						currentPointY = alpha[(int)firstLetterofTheWord-97].getY(cnt);						
	 					} else if (cnt >= 800) {
	 						//dictionary contains more than 800 same alphabet. 
	 						currentPointX = currentPointX + 1;
	 						if (currentPointX == N)
	 						{
	 							currentPointX = 0;
	 							currentPointY = (currentPointY + 1);
	 						}
							if (currentPointY == N)
							{ // dictionary is fully searched. 
								wordsLoc[k][0] = -1;
								break;
							}
						} else {
							//no more wanted alphabets in the dictionary
							wordsLoc[k][0] = -1;
							break;
						}
	 					
	 					canditLocs[0][0] = -1;
	 					//checking surroundings
	 					if ( checkRight(currentPointX, currentPointY, indicatingPointY+1)  )
	 					{
	 						setCanditLocs();
	 					}
	 					if ( checkLeft(currentPointX, currentPointY, indicatingPointY+1)  )
	 					{
	 						setCanditLocs();
	 					}
	 					if ( checkUp(currentPointX, currentPointY, indicatingPointY+1) )
	 					{
	 						setCanditLocs();
	 					}
	 					if ( checkDown(currentPointX, currentPointY, indicatingPointY+1) )
	 					{
	 						setCanditLocs();
	 					}
	 					if ( checkUpRight(currentPointX, currentPointY, indicatingPointY+1) )
	 					{
	 						setCanditLocs();
	 					}
	 					if ( checkDownLeft(currentPointX, currentPointY, indicatingPointY+1) )
	 					{
	 						setCanditLocs();
	 					}

	 					if ( checkUpLeft(currentPointX, currentPointY, indicatingPointY+1) )
	 					{
	 						setCanditLocs();
	 					}
	 					if ( checkDownRight(currentPointX, currentPointY, indicatingPointY+1) )
	 					{
	 						setCanditLocs();
	 					}
	 					
	 					if (canditLocs[0][0] != -1)
	 					{	//word is found
	 						setWordsLoc(k);
	 						break;
	 					}	 						 					
	 				}	 //While loop ends			

//test prompt	 				
	 				String aaa;
 					aaa = java.util.Arrays.toString(words[k]);
 					System.out.print(aaa);
 					System.out.printf(" is: (%d, %d), (%d, %d)\n", wordsLoc[k][0], wordsLoc[k][1], wordsLoc[k][2], wordsLoc[k][3]);
		 			
	 				indicatingPointX++;
	 				if (indicatingPointX >= M)
	 				{ // all words are searched
	 					break;
	 				}	 				
				 }	//if else (!howManyInDictionary) ends	
			}	//for loop ends
		}
				 		
		//To set the wordsLoc[k] 
		//and deal with the case where the same word found twice and they starts from the same location
 		//Search canditLocs[][] and set wordsLoc
 		private void setWordsLoc(int k)
		{//return true if find the word  to set isFound	flag		
 			if (canditLocs[1][0] == -1)
 			{//one word is found
// 				
 				System.out.println("one candidate");
 				
				wordsLoc[k][2] = canditLocs[0][0];
				wordsLoc[k][3] = canditLocs[0][1];
				
				System.out.printf("wordsLoc[%d][2] (%d) = canditLocs[0][0] (%d)\n", k, wordsLoc[k][2], canditLocs[0][0]);
				System.out.printf("wordsLoc[%d][3] (%d) = canditLocs[0][1] (%d)\n", k, wordsLoc[k][3], canditLocs[0][1]);
 			}else{//many the same words that starts from the same alphabet founds
 				
 				System.out.println("several candidates");
 				
 				for (int i=0; i < 7; i++)
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
 					canditLocs[i+1][0] = -1;
 				}
 			}
			wordsLoc[k][0] = currentPointX;
			wordsLoc[k][1] = currentPointY;

//for test	
 			System.out.print(this.words[this.indicatingPointX]);
 			System.out.printf(" is : (%d, %d), (%d, %d)\n", this.wordsLoc[k][0], wordsLoc[k][1], wordsLoc[k][2], wordsLoc[k][3]);
 		}

 		//set canditLocs[next] to possible ending [x, y]
 		private void setCanditLocs()
 		{
 			for (int i=0; i<8; i++)
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
 		
 		
 		private boolean checkRight(int x, int y, int indicatingPointY)
 		{ 		
 			alphabetTest = indicatingPointY;

 			tempX = x;
 			tempY = Math.floorMod((y+1),N);
 			
 			System.out.printf("in R(%d, %d, %d), ", x, y, alphabetTest);
			System.out.printf("tempLoc is (%d, %d)\n", tempX, tempY);

			if(tempX < 0 || tempY < 0)
			{
				System.exit(1);
			}
			if(alphabetTest >= words[indicatingPointX].length)
 			{	
 	 			System.out.println("checkR finished");

 				return true;
 			}
 			if ( dictionary[tempX][tempY] == words[indicatingPointX][alphabetTest] )
 			{
 				
 				System.out.println("right alphabet");
 				
 				alphabetTest++;
 				return checkRight(tempX, tempY, alphabetTest);
 			} else {
 				return false;
		 	}
 		}
 		private boolean checkLeft(int x, int y, int indicatingPointY)
 		{
 			alphabetTest = indicatingPointY;

 			tempX = x;
 			tempY = Math.floorMod((y-1),N);

 			System.out.printf("in L(%d, %d, %d), ", x, y, alphabetTest);
			System.out.printf("tempLoc is (%d, %d)\n", tempX, tempY);
 			
 			if(tempX < 0 || tempY < 0)
			{
				System.exit(1);
			}
			if(alphabetTest >= this.words[indicatingPointX].length)
 			{	
 	 			System.out.println("checkL finished");

 				return true;
 			}
 			if ( dictionary[tempX][tempY] == words[indicatingPointX][alphabetTest] )
 			{
 				
 				System.out.println("right alphabet");
 				
 				alphabetTest++;
 				return checkLeft(tempX, tempY, alphabetTest);
 			} else {
 				return false;
		 	}
 		}
 		private boolean checkUp(int x, int y, int indicatingPointY)
 		{
 			alphabetTest = indicatingPointY;

 			tempX = Math.floorMod((x-1),N);
 			tempY = y;

 			System.out.printf("in U(%d, %d, %d), ", x, y, alphabetTest);
			System.out.printf("tempLoc is (%d, %d)\n", tempX, tempY);
 			
			if(tempX < 0 || tempY < 0)
			{
				System.out.println("modular");
				System.exit(1);
			}
			if(alphabetTest >= this.words[indicatingPointX].length)
 			{	
 	 			System.out.println("checkU finished");

 				return true;
 			}
 			if ( dictionary[tempX][tempY] == words[indicatingPointX][alphabetTest] )
 			{
 				
 				System.out.println("right alphabet");
 				
 				alphabetTest++;
 				return checkUp(tempX, tempY, alphabetTest);
 			} else {
 				return false;
		 	}
 		}
 		private boolean checkDown(int x, int y, int indicatingPointY)
 		{
 			alphabetTest = indicatingPointY;

 			tempX = Math.floorMod((x+1),N);
 			tempY = y;
 			
 			System.out.printf("in D(%d, %d, %d), ", x, y, alphabetTest);
			System.out.printf("tempLoc is (%d, %d)\n", tempX, tempY);
 			
			if(tempX < 0 || tempY < 0)
			{
				System.out.println("modular");
				System.exit(1);
			}
			if(alphabetTest >= this.words[indicatingPointX].length)
 			{	
 	 			System.out.println("checkD finished");

 				return true;
 			}
 			if ( dictionary[tempX][tempY] == words[indicatingPointX][alphabetTest] )
 			{
 				
 				System.out.println("right alphabet");
 				
 				alphabetTest++;
 				return checkDown(tempX, tempY, alphabetTest);
 			} else {
 				return false;
		 	}
 		}
 		private boolean checkUpRight(int x, int y, int indicatingPointY)
 		{
 			alphabetTest = indicatingPointY;
 			
 			tempX = Math.floorMod((x-1),N);
 			tempY = Math.floorMod((y+1),N);

 			System.out.printf("in UR(%d, %d, %d), ", x, y, alphabetTest);
			System.out.printf("tempLoc is (%d, %d)\n", tempX, tempY);

 			if(tempX < 0 || tempY < 0)
			{
				System.exit(1);
			}
			if(alphabetTest >= this.words[indicatingPointX].length)
 			{	
 	 			System.out.println("checkUR finished");

 				return true;
 			}
 			if ( dictionary[tempX][tempY] == words[indicatingPointX][alphabetTest] )
 			{
 				
 				System.out.println("right alphabet");
 				
 				alphabetTest++;
 				return checkUpRight(tempX, tempY, alphabetTest);
 			} else {
 				return false;
		 	}
 		}
 		private boolean checkDownLeft(int x, int y, int indicatingPointY)
 		{
 			alphabetTest = indicatingPointY;

 			tempX = Math.floorMod((x+1), N);
 			tempY = Math.floorMod((y-1), N);
 			
 			System.out.printf("in DL(%d, %d, %d), ", x, y, alphabetTest);
			System.out.printf("tempLoc is (%d, %d)\n", tempX, tempY);

			if(tempX < 0 || tempY < 0)
			{
				System.exit(1);
			}
			if(alphabetTest >= this.words[indicatingPointX].length)
 			{	
 	 			System.out.println("checkDL finished");

 				return true;
 			}
 			if ( dictionary[tempX][tempY] == words[indicatingPointX][alphabetTest] )
 			{
 				
 				System.out.println("right alphabet");
 				
 				alphabetTest++;
 				return checkDownLeft(tempX, tempY, alphabetTest);
 			} else {
 				return false;
		 	}
 		}
 
//WORKING ON
 		
 		private boolean checkUpLeft(int x, int y, int indicatingPointY)
 		{
 			alphabetTest = indicatingPointY;
  			
			if (x <= y)
			{	
				tempX = Math.floorMod((x-1),(N-(y-x)));
				tempY = (y-x) + Math.floorMod((x-1),(N-(y-x)));
			} else {

				tempX = (x-y) + Math.floorMod((y-1),(N-(x-y)));
				tempY = Math.floorMod((y-1),(N-(x-y))); 
			}

			System.out.printf("in UpLeft(%d, %d, %d), ", x, y, alphabetTest);
			System.out.printf("tempLoc is (%d, %d)\n", tempX, tempY);

			if(tempX < 0 || tempY < 0)
			{
				System.exit(1);
			}
			if(alphabetTest >= this.words[indicatingPointX].length)
 			{	
// 	 			System.out.println("checkUpLeft finished");

 				return true;
 			}
 			if ( dictionary[tempX][tempY] == words[indicatingPointX][alphabetTest] )
 			{
 				
 				System.out.println("right alphabet from UL");
 				
 				alphabetTest++;
 				System.out.printf("returning UL(%d, %d, %d)", tempX, tempY, alphabetTest);
 				return checkUpLeft(tempX, tempY, alphabetTest);
 			} else {
 
 				System.out.println("wrong alphabet from UL");
 				System.out.printf("dictionary[%d][%d] (%c) != words[%d][%d] (%c)\n", tempX, tempY, 
 						dictionary[tempX][tempY], indicatingPointX, alphabetTest, words[indicatingPointX][alphabetTest]);

 				return false;
		 	}
 		}
 		
 		private boolean checkDownRight(int x, int y, int indicatingPointY)
 		{
 			alphabetTest = indicatingPointY;

			if (x < y)
			{
				tempX = Math.floorMod((x+1), (N-(y-x)));
				tempY = (y-x) + Math.floorMod((x+1), (N-(y-x)));
			} else {
				tempX = (x-y) + Math.floorMod((y+1), (N-(x-y)));
				tempY = Math.floorMod((y+1), (N-(x-y)));
			}
			
 			System.out.printf("in DR(%d, %d, %d), ", x, y, alphabetTest);
			System.out.printf("tempLoc is (%d, %d)\n", tempX, tempY);
			
			if(tempX < 0 || tempY < 0)
			{
				System.out.println("modular");
				System.exit(1);
			}
			if(alphabetTest >= this.words[indicatingPointX].length)
 			{	
 	 			System.out.println("checkDR finished");

 				return true;
 			}
 			if ( dictionary[tempX][tempY] == words[indicatingPointX][alphabetTest] )
 			{
 				
 				System.out.println("right alphabet");
 				
 				alphabetTest++;
 				return checkDownRight(tempX, tempY, alphabetTest);
 			} else {
 				return false;
		 	}
 		}
 	
}