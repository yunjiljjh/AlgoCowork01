package WordSearch;

import java.util.ArrayList;
import java.util.List;

public class Solve {
	private int N; //size of N in N*N dictionary array
	private int M; //number of words to be searched
	private char [][] dictionary; // tile array given
	private char [][] words; // words you have to search
	
	/*저는 각 알파벳을 다 배열객체로 만들어서 alphabet객체내에서 위치를 가지도록 했어요.*/
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
	private int[8][2] canditLocs; // canditLocs[up][0]: word를  up방향에서 찾았을 때 x의 좌표
	public int[20][4] wordsLoc; // wordsLoc[word][0]: canditLocs 중 endingPoint의 좌표가 가장 00에 가까운 word의 좌표
	// words[0][0]: 첫 번째 word의 startinPointX, words[0][1]: startingPointY, words[0][2]: endingPointX, words[0][3]: endingPointY

	//RIM
//	public Solve(char[][] dictionary, char[][] words, int N, int M){
	public int[][] Solve(char[][] dictionary, char[][] words, int N, int M){
			alpha = new Alphabet[26];
			this.N = N;
			this.M = M;
			fillAlpha(); //with dictionary[][], record where each alphabet is located
			this.dictionary = dictionary;
			this.words = words;

			findWords(); //실제 퍼즐을 푸는 함수
			
			//RIM
			return wordsLoc;
		}
		
		private void fillAlpha(){
			for (int i = 0 ; i < N ; i++){
				for (int j = 0 ; j < N ; j++){
					int alphaACII = (int)dictionary[i][j] - 97;
					alpha[alphaACII].setXY(i, j);
				}
			}
		}

		
		
		private void findWords(){ //실제 퍼즐을 푸는 함수
			for (int k = 0 ; k < M ; k++){ //traverse M number of words
				 		//Search for a word in a loop
				 		char firstLetterofTheWord = words[k][0];
				 		int howManyinDictionary = alpha[(int)firstLetterofTheWord-97].getSize();
				 					if (howManyinDictionary == 0)
				 					{//--> 없으니까 print 0
				 						wordsLoc[k] = "0\n";
				 					}
				 					else{
				 						canditLocs[0][0] = -1;
				 						
				 						while(true)
				 						{
					 						if(k+1 < alpha.getSize())
					 						{
					 							//locating potential start point
						 						this.currentPointX = alpha.getX(k+1); // getX의 함수를 고치는 방법도.
						 						this.currentPointY = alpha.getY(k+1);						
					 						} else if (k+1 >= 800) {
					 							this.currentPointX = (this.currrentPointX + 1)% this.N;
												this.currentPointY = (this.currrentPointY + 1);			
												if (this.currentPointX == N && this.currentPointY == N)
												{
													wordsLoc[k] = "0\n";
													break;
												}
											} else {
												wordsLoc[k] = "0\n";
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
					 						//continue
					 						
					 						//determines 
					 						setWordsLoc(k); 
				 						
				 					//	밑에 만들어놓은 seek함수와 go함수를 이용해 왓다리 갔다리 비교하면 될듯
				 					}
				 			}
				 		
				 		}
				 		
				 		//Search canditLocs[][] and set wordsLoc
				 		private void setWordsLoc(int k)
						{
				 			if (canditLocs[0][0] == -1)
				 			{
				 				wordsLoc[k] = '-1';
				 				
				 			}else{
				 				for (i=0; i < this.M-1; i++)
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
				 			for (i=0; i<this.M; i++)
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
				 			if (words[indicatingPointX][indicatingPointY] == ‘\n’) 
				 			{	
				 				return true;
				 			}
				 			if ( this.dictionary[x][this.tempY] == this.words[indicatingPointX][indicatingPointY] )
				 			{
				 				indicatingPointY += 1;
				 				return checkRight(x, tempY);
				 			} else {
				 		return false;
				 	}
				 		}
				 		
		
		/*
		 * 주변 문자 seek 함수. dictionary[][]에서 current point 주변을 살핀다.
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
		 * 칸 이동 함수. dictionary[][]에서 current point를 움직인다.
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
