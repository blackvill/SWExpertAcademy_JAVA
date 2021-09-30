package swet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_D4_1249 {
	static int T;
	static int n;
	static int[][] map;
	static int[][] visited;
	
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			
			map = new int[n][n];
			visited = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				char[] c = br.readLine().toCharArray();
				for (int j = 0; j < n; j++) {
					map[i][j] = c[j] - '0';
					visited[i][j] = Integer.MAX_VALUE >> 2;
				}
			}
			visited[0][0] = map[0][0];
			bfs();
			
			sb.append("#").append(t).append(" ").append(visited[n - 1][n - 1]).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		
		q.add(new int[] {0, 0});
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dr[i];
				int ny = cur[1] + dc[i];
				
				if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
					if (visited[nx][ny] > visited[cur[0]][cur[1]] + map[nx][ny]) {
						visited[nx][ny] = visited[cur[0]][cur[1]] + map[nx][ny];
						q.offer(new int[] {nx, ny});
					}
				}
			}
		}
	}
}
