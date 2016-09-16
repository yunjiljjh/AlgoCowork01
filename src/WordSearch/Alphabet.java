package WordSearch;

public class Alphabet {
	private int size; // 한 알파벳이 dictionary에서 몇번 나오는지
	private int[] x;
	private int[] y;
	
	public Alphabet(){
		size = 0;
		x = new int[800]; // 최대 50*50의 타일에서 한 알파벳이 최대 800번 이하로 나온다고 가정...
		y = new int[800]; // 최대 50*50의 타일에서 한 알파벳이 최대 800번 이하로 나온다고 가정...
	}

	public void setXY(int x, int y){
		this.x[size] = x;
		this.y[size] = y;
		size++;
	}

	public int getX(int num){ // num번째 x 위치를 불러다줌
		return x[num-1];
	}

	public int getY(int num){ // num번째 y 위치를 불러다줌
		return y[num-1];
	}
	
	public int getSize(){
		return this.size;
	}

	
}
