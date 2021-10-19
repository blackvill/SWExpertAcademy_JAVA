package swet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_User_4727 {
	static int T;
	static int N;
	static int M;
	static int ans;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	// 견우
	static class Altair {
		int x; // 현재 좌표
		int y; // 현재 y좌표
		int t; // 현재까지 걸린 시간
		
		Altair(int x, int y, int t){
			this.x = x;
			this.y = y;
			this.t = t;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			ans = Integer.MAX_VALUE;

			map = new int[N][N];

			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());

				for(int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			// 전체 배열을 탐색하면서 절벽인 곳을 찾아
			// 해당 절벽이 교차하는 곳인지를 확인한다.
			// 교차하는 곳이 아니라면 해당 위치에 오작교를 건설하고 탐색
			// 이후 돌아와서 해당 오작교를 다시 절벽으로 만들고 다음에 오는 교차하지 않는 절벽에 오작교 건설 후 탐색
			// 이를 반복하고 최종적으로 가장 적게 걸리는 것이 결과
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {

					// 절별이라면
					if(map[r][c] == 0) {
						boolean[] check = new boolean[4];

						// 사방탐색
						for(int i = 0 ; i < 4 ; i++) {
							int nr = r + dr[i];
							int nc = c + dc[i];

							// 범위를 벗어나면
							if(nr >= N || nr < 0 || nc >= N || nc < 0)
								continue;

							// 탐색하는 곳이 절벽이라면
							if(map[nr][nc] == 0) {
								check[i] = true; // 해당 위치를 절벽으로 체크
							}
						}

						// 현재 절벽을 기준으로 위쪽과 왼쪽이 절벽이면 교차하는 절벽이다.
						if((check[0] && check[2]))
							continue;
						// 현재 절벽을 기준으로 위쪽과 오른쪽이 절벽이면 교차하는 절벽이다.
						else if (check[0] && check[3])
							continue;
						// 현재 절벽을 기준으로 아래쪽과 왼쪽이 절벽이면 교차하는 절벽이다.
						else if (check[1] && check[2])
							continue;
						// 현재 절벽을 기준으로 아래쪽과 오른쪽이 절벽이면 교차하는 절벽이다.
						else if (check[1] && check[3])
							continue;

						map[r][c] = M;
						visited = new boolean[N][N];

						bfs();

						map[r][c] = 0;
					}
				}
			}

			System.out.println("#" + t + " " + ans);
		}
	}
	
	private static void bfs() {
		Queue<Altair> q = new LinkedList<>();
		visited[0][0] = true; // 0, 0에서 시작
		q.offer(new Altair(0, 0, 0));
		
		while(!q.isEmpty()) {
			Altair cur = q.poll();
			
			if(cur.x == N - 1 && cur.y == N - 1) {
				ans = Math.min(ans, cur.t);
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int nr = cur.x + dr[i];
				int nc = cur.y + dc[i];
				
				// 범위를 벗어나는 경우
				if(nr >= N || nr < 0 || nc >= N || nc < 0)
					continue;
				
				// 갈 수 없는 경우
				if(map[nr][nc] == 0 || visited[nr][nc])
					continue;
				
				// 땅인 경우 이동
				if(map[nr][nc] == 1) {
					visited[nr][nc] = true;
					
					q.offer(new Altair(nr, nc, cur.t + 1));
				}
				
				// 오작교가 생성된 절벽인 경우
				if(map[nr][nc] >= 2 && map[cur.x][cur.y] == 1) {
					// 만약 다음으로 이동을 할 때 시간을 오작교 주기로 나눠서 0이라면 이동 가능 
					if((cur.t + 1) % map[nr][nc] == 0) {
						visited[nr][nc] = true;
						
						q.offer(new Altair(nr, nc, cur.t + 1));
					} else {
						q.offer(new Altair(cur.x, cur.y, cur.t + 1));
					}
				}
			}
		}
	}
}
