package swet;

import java.util.Scanner;

public class Solution_D2_2005 {
	static int n;
	static int test;
	static int[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		test = sc.nextInt();
		for (int t = 1; t <= test; t++) {
			n = sc.nextInt();
			map = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j <= i; j++) {
					if (j == 0 || j == i) { // 각 행의 시작과 마지막
						map[i][j] = 1;
					}
					else { // 나머지
						map[i][j] = map[i-1][j-1] + map[i - 1][j];
					}
				}
			}
			System.out.println("#" + t + " ");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] == 0)
						continue;
					else
						System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}
