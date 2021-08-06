package swet;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution_D3_5215_김재욱 {

//	static int numbers[];
	static int result[];
	static int[][] tk;
	static int kcal;
	static int n; // 재료의 수
	static int l; // 제한 칼로리
	static int total;
	static int Max;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_5215.txt"));
		Scanner sc  = new Scanner(System.in);
		
		int T = sc.nextInt(); //테케 수
		for (int t = 1; t <= T; t++){
			Max = 0;
			n = sc.nextInt(); // 재료의 수
			l = sc.nextInt(); // 제한된 칼로리
			tk = new int[n][2];
//			numbers = new int[n];
			for (int i = 0; i < n; i++) {
				tk[i][0] = sc.nextInt(); // 맛 점수
				tk[i][1] = sc.nextInt(); // 칼로리
			}
			for (int i = 0; i <= n; i++) {
				kcal = 0;
				total = 0;
				combination(n, i);
			}
			System.out.println("#" + t + " " + Max);
		}
	}
	private static void combination (int n, int r) {
		if (r == 0) { // 조합의 요소를 모두 뽑은 경우
			if (l >= kcal)			
				Max = Math.max(total, Max);
			return;
		}
		if (n < r) return; // n이 r보다 커지면
		// 선택
//		numbers[r - 1] = tk[n-1][1];
		kcal += tk[n-1][1];
		total += tk[n - 1][0];
		combination(n - 1, r - 1);
		// 비 선택
		kcal -= tk[n-1][1];
		total -= tk[n - 1][0];
		combination(n - 1, r);
	}
}
