package swet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_test_1953 {
	static int T;
	static int n; // 세로 크기
	static int m; // 가로 크기
	static int r; // 맨홀 세로 위치
	static int c; // 맨홀 가로 위치
	static int l; // 탈출에 소요된 시간
	static int map[][]; // 파이프 상태를 받을 배열
	static boolean[][] visited; // 방문처리용 배열
	static int count; // 있을 수 있는 곳의 개수

	// 1~7 모양의 델타 배열
	static int[][] dr = {{-1, 1, 0, 0}, {-1, 1, 0, 0}, {0, 0, 0, 0}, {-1, 0, 0, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}, {-1, 0, 0, 0}};
	static int[][] dc = {{0, 0, -1, 1}, {0, 0, 0, 0}, {0, 0, -1, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, -1, 0}, {0, 0, -1, 0}};
	// 다음 위치의 파이프가 해당 모양일 때 이동가능함을 담은 배열
	static int[][] next = {{1, 2, 5, 6}, {1, 2, 4, 7}, {1, 3, 4, 5}, {1, 3, 6, 7}};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken()) - 1; // 1시간이 되어야 맨홀 안으로 들어가니까 1을 빼준다.

			// 초기화 해주기
			map = new int[n][m];
			visited = new boolean[n][m];
			count = 1; // 맨홀 구멍으로 들어간다고 하면 들어간 상태일 때 해당 자리를 포함해야 하니까

			// 입력받기
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			bfs(r, c);

			System.out.println("#" + t + " " + count);
		}
	}

	public static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		visited[x][y] = true;
		q.add(new int[] {x, y, 0});
		// x, y, 시간

		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int delta = map[cur[0]][cur[1]] - 1; // 현재 좌표의 파이프
			
			if(cur[2] == l) { // 시간이 주어진 시간과 동일해지면 종료
				return;
			}

			for(int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[delta][i];
				int nc = cur[1] + dc[delta][i];

				if (cur[0] == nr && cur[1] == nc) // 이동을 했는데 그게 현재 위치라면 탐색 불가
					continue;

				if (nr >= 0 && nc >= 0 && nr < n && nc < m && map[nr][nc] != 0) {
					// 범위안에 들어있고 0이 아닌 곳(파이프가 아니면 이동 불가)
					for (int j = 0; j < 4; j++) {
						if (next[i][j] == map[nr][nc]) {
							// 다음 지점이 갈수 있는 곳인지(가려고 해도 파이프가 연결되지 않은 모양이라면 못가니까)
							if (!visited[nr][nc]){ // 방문하지 않은 곳이라면 
								visited[nr][nc] = true; // 방문처리하고
								count++; // 횟수 증가
								q.add(new int[] {nr, nc, cur[2] + 1});
								// 다음 위치와 증가한 시간
							}
						}
					}
				}
			}
		}
	}
}
