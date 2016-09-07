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
	
		public Solve(char[][] dictionary, char[][] words, int N, int M){
			alpha = new Alphabet[26];
			this.N = N;
			this.M = M;
			fillAlpha(); //with dictionary[][], record where each alphabet is located
			this.dictionary = dictionary;
			this.words = words;
			
			findWords(); //실제 퍼즐을 푸는 함수
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
				char firstLetterofTheWord = words[k][0];
				int howManyinDictionary = alpha[(int)firstLetterofTheWord-97].getSize();
					if (howManyinDictionary == 0) --> 없으니까 print 0
					else{
						밑에 만들어놓은 seek함수와 go함수를 이용해 왓다리 갔다리 비교하면 될듯
					}
			}
		
		}
		
		/*
		 * 주변 문자 seek 함수. dictionary[][]에서 current point를 움직인다.
		 */
		private char seekRight(int x, int y){return dictionary[x][(y+1)%N];}
		private char seekLeft(int x, int y){return dictionary[x][(y-1)%N];}
		private char seekUp(int x, int y){return dictionary[(x-1)%N][y];}
		private char seekDown(int x, int y){return dictionary[(x+1)%N][y];}
		private char seekUpRight(int x, int y){return dictionary[(x-1)%N][(y+1)%N];}
		private char seekDownLeft(int x, int y){return dictionary[(x+1)%N][(y-1)%N];}
		private char seekDownRight(int x, int y){return  쓰고 ;}
		private char seekUpLeft(int x, int y){return 쓰고;}
		
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
