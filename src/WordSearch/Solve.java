package WordSearch;

import java.util.ArrayList;
import java.util.List;

public class Solve {
	private int N; //size of N in N*N dictionary array
	private int M; //number of words to be searched
	private char [][] dictionary; // tile array given
	private char [][] words; // words you have to search
	
	/* deleted.*/
	private Alphabet[] alpha; // from a-z location of each alphabet in the dictionary[][]
	
	private int currentPointX; //current location of 'x' in (x,y) of dictionary[][]
	private int currentPointY; //current location of 'y' in (x,y) of dictionary[][]
	private int indicatingPointX;  //current location of 'x' in (x,y) of words[][]
	private int indicatingPointY;  //current location of 'y' in (x,y) of words[][]
	
	private int startingPointX; //x location of starting point
	private int startingPointY; //y location of starting point
	private	int endingPointX; //x location of ending point
	private	int endingPointY; //y location of ending point
	
	//RIM
	private int tempX;
	private int tempY;
	private int[][] canditLocs = new int[8][2]; // canditLocs[up][0]: deleted
	public int[][] wordsLoc; // wordsLoc[word][0]: canditLocs deleted
	public String result = "";

	//RIM
//	public Solve(char[][] dictionary, char[][] words, int N, int M){
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
			findWords(); 
			
			//RIM
			//return wordsLoc;
			//convert int[][] to string
			for (int i=0; i < M; i++)
			{
				if(wordsLoc[i][0] == -1){
					result += "0\n";
				}else{
					result = result + java.util.Arrays.toString(wordsLoc[i]) + '\n';
				}
			}
		}
		
		private void fillAlpha(){
			for (int i = 0 ; i < N ; i++){
				for (int j = 0 ; j < N ; j++){
					int alphaACII = (int)(dictionary[i][j]) - 97;
					alpha[alphaACII].setXY(i, j);	
				}
			}
		}

		
		
		private void findWords()
		{ //deleted
			for (int k = 0 ; k < M ; k++)
			{ //traverse M number of words
			//Search for a word in a loop
				boolean isFound = false; //flag
				char firstLetterofTheWord = words[k][0];
				int howManyinDictionary = alpha[(int)firstLetterofTheWord-97].getSize();
				if (howManyinDictionary == 0)
			 	{//--> deleted
			 		wordsLoc[k][0] = -1;
			 	} else {
			 		canditLocs[0][0] = -1;
			 						
	 				while(!isFound)
	 				{
	 					if(k+1 < alpha[(int)firstLetterofTheWord-97].getSize())
	 					{
	 						//locating potential start point
	 						this.currentPointX = alpha[(int)firstLetterofTheWord-97].getX(k+1); // getX deleted
	 						this.currentPointY = alpha[(int)firstLetterofTheWord-97].getY(k+1);						
	 					} else if (k+1 >= 800) {
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
	 					
	 					if ( checkRight(this.currentPointX, this.currentPointY)  )
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
	 					if ( checkUpLeft(this.currentPointX, this.currentPointY) )
	 					{
	 						setCanditLocs();
	 					}
	 					if ( checkDownRight(this.currentPointX, this.currentPointY) )
	 					{
	 						setCanditLocs();
	 					}
	 					//determines 
	 					setWordsLoc(k);
	 				}	 						
		 				//	deleted
				 }		
			}
		}
				 		
 		//Search canditLocs[][] and set wordsLoc
 		private void setWordsLoc(int k)
		{//return true if find the word
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
 		private boolean checkRight(int x, int y)
 		{
 			this.tempX = x;
 			this.tempY = (y+1)%N;
 			if (words[indicatingPointX][indicatingPointY] < 'a' || words[indicatingPointX][indicatingPointY] > 'z') 
 			{	
 				return true;
 			}
 			if ( this.dictionary[x][this.tempY] == this.words[indicatingPointX][indicatingPointY] )
 			{
 				indicatingPointY++;
 				return checkRight(x, tempY);
 			} else {
 				return false;
		 	}
 		}
 		private boolean checkLeft(int x, int y)
 		{
 			this.tempX = x;
 			this.tempY = (y-1)%N;
 			if (words[indicatingPointX][indicatingPointY] < 'a' || words[indicatingPointX][indicatingPointY] > 'z') 
 			{	
 				return true;
 			}
 			if ( this.dictionary[x][this.tempY] == this.words[indicatingPointX][indicatingPointY] )
 			{
 				indicatingPointY++;
 				return checkLeft(tempX, tempY);
 			} else {
 				return false;
		 	}
 		}
 		private boolean checkUp(int x, int y)
 		{
 			this.tempX = (x-1)%N;
 			this.tempY = y;
 			if (words[indicatingPointX][indicatingPointY] < 'a' || words[indicatingPointX][indicatingPointY] > 'z') 
 			{	
 				return true;
 			}
 			if ( this.dictionary[x][this.tempY] == this.words[indicatingPointX][indicatingPointY] )
 			{
 				indicatingPointY++;
 				return checkUp(tempX, tempY);
 			} else {
 				return false;
		 	}
 		}
 		private boolean checkDown(int x, int y)
 		{
 			this.tempX = (x+1)%N;
 			this.tempY = y;
 			if (words[indicatingPointX][indicatingPointY] < 'a' || words[indicatingPointX][indicatingPointY] > 'z') 
 			{	
 				return true;
 			}
 			if ( this.dictionary[x][this.tempY] == this.words[indicatingPointX][indicatingPointY] )
 			{
 				indicatingPointY++;
 				return checkDown(tempX, tempY);
 			} else {
 				return false;
		 	}
 		}
 		private boolean checkUpRight(int x, int y)
 		{
 			this.tempX = (x-1)%N;
 			this.tempY = (y+1)%N;
 			if (words[indicatingPointX][indicatingPointY] < 'a' || words[indicatingPointX][indicatingPointY] > 'z') 
 			{	
 				return true;
 			}
 			if ( this.dictionary[x][this.tempY] == this.words[indicatingPointX][indicatingPointY] )
 			{
 				indicatingPointY++;
 				return checkUpRight(tempX, tempY);
 			} else {
 				return false;
		 	}
 		}
 		private boolean checkDownLeft(int x, int y)
 		{
 			this.tempX = (x+1)%N;
 			this.tempY = (y-1)%N;
 			if (words[indicatingPointX][indicatingPointY] < 'a' || words[indicatingPointX][indicatingPointY] > 'z') 
 			{	
 				return true;
 			}
 			if ( this.dictionary[x][this.tempY] == this.words[indicatingPointX][indicatingPointY] )
 			{
 				indicatingPointY++;
 				return checkDownLeft(tempX, tempY);
 			} else {
 				return false;
		 	}
 		}
 		private boolean checkUpLeft(int x, int y)
 		{
			if (currentPointX < currentPointY)
			{
				this.tempX = (currentPointX+1)%(N-(currentPointY-currentPointX));
				this.tempY = (currentPointY-currentPointX) + (currentPointX+1)%(N-(currentPointY-currentPointX));
			} else {
				this.tempX = (currentPointX-currentPointY) + (currentPointY+1)%(N-(currentPointX-currentPointY));
				this.tempY = (currentPointY+1)%(N-(currentPointX-currentPointY));
			}
 			if (words[indicatingPointX][indicatingPointY] < 'a' || words[indicatingPointX][indicatingPointY] > 'z') 
 			{	
 				return true;
 			}
 			if ( this.dictionary[x][this.tempY] == this.words[indicatingPointX][indicatingPointY] )
 			{
 				indicatingPointY++;
 				return checkUpLeft(tempX, tempY);
 			} else {
 				return false;
		 	}
 		}
 		private boolean checkDownRight(int x, int y)
 		{
			if (currentPointX < currentPointY)
			{
				this.tempX = (currentPointX-1)%(N-(currentPointY-currentPointX));
				this.tempY = (currentPointY-currentPointX) + (currentPointX-1)%(N-(currentPointY-currentPointX));
			} else {
				this.tempX = (currentPointX-currentPointY) + (currentPointY-1)%(N-(currentPointX-currentPointY));
				this.tempY = (currentPointY-1)%(N-(currentPointX-currentPointY)); 
			}
 			if (words[indicatingPointX][indicatingPointY] < 'a' || words[indicatingPointX][indicatingPointY] > 'z') 
 			{	
 				return true;
 			}
 			if ( this.dictionary[x][this.tempY] == this.words[indicatingPointX][indicatingPointY] )
 			{
 				indicatingPointY++;
 				return checkDownRight(tempX, tempY);
 			} else {
 				return false;
		 	}
 		}


		/*
		 * deleted
		 */
		private char seekRight(int x, int y){return dictionary[x][(y+1)%N];}
		private char seekLeft(int x, int y){return dictionary[x][(y-1)%N];}
		private char seekUp(int x, int y){return dictionary[(x-1)%N][y];}
		private char seekDown(int x, int y){return dictionary[(x+1)%N][y];}
		private char seekUpRight(int x, int y){return dictionary[(x-1)%N][(y+1)%N];}
		private char seekDownLeft(int x, int y){return dictionary[(x+1)%N][(y-1)%N];}
		private char seekDownRight(int x, int y){
			if (currentPointX < currentPointY) return dictionary[(currentPointX-1)%(N-(currentPointY-currentPointX))][(currentPointY-currentPointX) + (currentPointX-1)%(N-(currentPointY-currentPointX))];
			else return dictionary[(currentPointX-currentPointY) + (currentPointY-1)%(N-(currentPointX-currentPointY))][(currentPointY-1)%(N-(currentPointX-currentPointY))]; 
			}
		private char seekUpLeft(int x, int y){
			if (currentPointX < currentPointY) return dictionary[(currentPointX+1)%(N-(currentPointY-currentPointX))][(currentPointY-currentPointX) + (currentPointX+1)%(N-(currentPointY-currentPointX))];
			else return dictionary[ (currentPointX-currentPointY) + (currentPointY+1)%(N-(currentPointX-currentPointY))][(currentPointY+1)%(N-(currentPointX-currentPointY))];
		}
		
		/*
		 * deleted
		 */
		private void goRight(){currentPointY =  (currentPointY+1)%N;}
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

		
}
