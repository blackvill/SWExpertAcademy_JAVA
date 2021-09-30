package swet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_test_1949 {
	static int T;
	static int n;
	static int k;
	static int[][] map;
	static boolean[][] visited;
	static int max_height; // 최고 높이
	static int result; // 결과
	
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			// 전체 초기화
			map = new int[n][n];
			visited = new boolean[n][n];
			max_height = 0;
			result = 0;
			
			// 값을 입력받기
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					// 입력받으면서 최고 높이 갱신
					max_height = Math.max(map[i][j], max_height);
				}
			}
			
			// dfs를 돌릴껀데 최고높이에서 돌려야하니까
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] == max_height) { // 최고높이라면
						// 탐색
						visited[i][j] = true;
						dfs(i, j, map[i][j], 1, 1);
						// x좌표, y좌표, 시작점의 높이, 산을 깎을 수 있는 횟수, 이동 횟수
						visited[i][j] = false;
					}
				}
			}
			
			System.out.println("#" + t + " " + result);
		}
	}

	/*
	 * x, y의 좌표, 높이, 산을 깎은 횟수, 이동 횟수
	 */
	public static void dfs(int x, int y, int height, int cnt, int move) {
		for (int i = 0; i < 4; i++) {
			if (result < move) {
				result = move;
			}
			
			int nx = x + dr[i];
			int ny = y + dc[i];
			
			if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]) {
				// 범위 안에 있으면서 방문하지 않았다면
				if (height <= map[nx][ny]) {
					// 가야할 곳의 높이가 현재의 높이보다 높다면
					if (cnt == 1 && height > map[nx][ny] - k) {
						// 깎을 횟수가 남아 있고 깎았을 때 현재 높이 보다 낮아진다면
						visited[nx][ny] = true;
						dfs(nx, ny, height - 1, cnt - 1, move + 1);
						visited[nx][ny] = false;
					}
				}
				else { // 가야할 곳의 높이가 현재 높이보다 작으니까
					visited[nx][ny] = true;
					dfs(nx, ny, map[nx][ny], cnt, move + 1);
					visited[nx][ny] = false;
				}
			}
		}
	}
}
