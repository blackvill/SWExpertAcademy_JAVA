package swet;

import java.util.Scanner;

public class Solution_D2_1954_김재욱 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int test = sc.nextInt();
		// 테스트 케이스만큼 반복
		for (int t = 1; t <= test; t++) {
			
			int n = sc.nextInt();
			int[] dx = {0, 1, 0, -1}; 
			int[] dy = {1, 0, -1, 0};
			
			int[][] arr = new int[n][n];
			
			if (n == 1) {
				System.out.println("#" + t);
				System.out.println("1");
				continue;
			}
			
			int x = 0;
			int y = -1;
			int count = 0;
			// 주요 소스코드
			for (int i = 0; i < n * n; i++) {
				x += dx[count];
				y += dy[count];
				arr[x][y] = i + 1;
				if ((y >= n - 1 && x >= n -1) || (y >= n - 1 && x == 0) || (x >= n - 1 && y == 0)) {
					count++;
					if (count == 4)
						count = 0;
				}
				if (arr[x + dx[count]][y + dy[count]] != 0) {
					count++;
					if (count == 4)
						count = 0;
				}
			}
			// 출력문
			System.out.println("#" + t);
			for (int i = 0; i < n; i++) { 
				for (int j = 0; j < n; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}