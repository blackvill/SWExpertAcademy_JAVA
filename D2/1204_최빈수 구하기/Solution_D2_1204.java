package swet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution_D2_1204 {

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/input_1204.txt"));
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		for(int i = 1; i <= t; i++)
        {
            int n = sc.nextInt();
            int max = 0;		
            int scores = 0;		//점수
            int count[]  = new int[101];		//0~100 카운트
            for(int j =0; j< 1000; j++) 
            {
                scores = sc.nextInt();		//점수입력
                count[scores] ++ ;			//해당 점수 count++
            }
            for(int j = 0; j < count.length - 1 ; j++)
                if(count[max] <= count[j]) 
                	max = j;
            System.out.println("#" + n + " " + max);
        }
	}
}