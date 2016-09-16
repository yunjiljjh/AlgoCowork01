/* (CSI 3108-01) Algorithm Analysis class HW1
 * Lee, Yun Ji	(2013198070)  	 
 * Jeong, Da Som(2012127028)	
 * Nam, Hyo Rim (2013147531)
 * 2016 Fall */
/* OutputWrite: get String result from Solve and write it to [studentID].txt*/

package WordSearch;

import java.io.FileWriter;
import java.io.IOException;

public class OutputWriter {
	private FileWriter fw;

	public void print(String str){
		try{
			fw=new FileWriter("C:\\hw1\\2013198070.txt",true);

			fw.write(str);
			fw.close();
		}catch(IOException e){}
		
	}
}
