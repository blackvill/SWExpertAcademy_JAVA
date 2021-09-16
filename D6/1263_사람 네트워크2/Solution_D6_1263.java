package swet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D6_1263 {
	static int T;
	static int n;
	static int[][] map;
	static int max = 100000000;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			map = new int[n + 1][n + 1];
			
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (i != j && map[i][j] == 0)
						map[i][j] = max;
				}
			}
			
			for (int k = 1; k <= n; k++) {
				for (int i = 1; i <= n; i++) {
					if (k == i) continue;
					for (int j = 1; j <= n; j++) {
						if (j == i || j == k) continue;
						map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
					}
				}
			}
			int min = Integer.MAX_VALUE;
			for (int i = 1; i <= n; i++) {
				int sum = 0;
				for (int j = 1; j <= n; j++) {
					sum += map[i][j];
				}
				min = Math.min(min, sum);
			}
			System.out.println("#" + t + " " + min);
		}
	}

}
