package swet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution_D2_2001 {

	static int[][] map;
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res/input_2001.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt(); // 테케
		for (int t = 1; t <= T; t++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			int max = 0;
			map = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt(); // 배열 값 입력
				}
			}
			for (int i = 0; i < n - m + 1; i++) { // n-m+1인 이유는 n-m을 하고 +1을 해주지 않으면 1줄을 버리게 됨
				for (int j = 0; j < n - m + 1; j++) {
					int sum = 0; // 선언과 동시에 초기화
					for (int k = 0; k < m; k++) { // m*m 범위의 배열을 만든다는 느낌
						for (int l = 0; l < m; l++) {
							sum += map[i + k][j + l]; // 반복문을 돌기때문에 m*m범위만큼을 다 탐색함
							// i, j에 더해주는 이유는 i,j부터 i+m, j+m까지의 정사각형을 탐색하기 위함 
						}
					}
					if (max < sum) // 최고값이 갱신되면 변경
						max = sum;
				}
			}
			System.out.println("#"+t+" " + max);
		}
		
	}

}
