package swet;

import java.util.*;
import java.io.*;

public class Solution_D4_1865 {
	static int n;
	static double[][] arr; // 업무 처리
	static double max; // 최고값
	static boolean[] visited; // 방문처리

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			
			// 초기화
			arr = new double[n][n];
			visited = new boolean[n];
			
			max = 0.0;
			
			// 입력받기
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				// 소수로 계산해야 하므로 미리 100으로 나눠준다.
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken()) / 100.0;
				}
			}
			
			// dfs 탐색하기
			// 곱해야 하므로 0.0이 아니라 1.0
			dfs(0, 1.0);
			
			// 소수점 아래로 6번째 자리까지 출력해야 하므로 .6f 
			System.out.printf("#%d %.6f\n", t, max);
		}
	}

	public static void dfs(int depth, double sum) {
		// max 값보다 작으면 return
		if (sum * 100 <= max)
			return;
		
		// 탐색 깊이가 n이면
		if (depth == n) {
			// max보다 크다면
			if (sum * 100 > max) {
				// 갱신
				max = sum * 100;
				return;
			}
		}

		// 탐색문
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				// 성공할 확률은 모든 일들을 곱한것이기 때문에 sum * arr[][];
				dfs(depth + 1, sum * arr[depth][i]);
				visited[i] = false;
			}
		}
	}
}