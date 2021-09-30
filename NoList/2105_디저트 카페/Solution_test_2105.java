package swet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_test_2105 {
	static int T;
	static int n;
	static int[][] map;
	static ArrayList<Integer> list;
	static int x, y; // 시작할 x와 y의 좌표값
	static int result;

	static int[] dr = {1, 1, -1, -1};
	static int[] dc = {1, -1, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());

			list = new ArrayList<>();
			result = -1; // 없으면 -1이니까 -1로 초기화
			map = new int[n][n];

			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < n - 2; i++) { // 행은 이렇게 움직임
				for (int j = 1; j < n - 1; j++) { // 열은 이렇게 움직임
					list.add(map[i][j]); // 현재 값을 리스트에 넣어준다.
					x = i; // 스타트 지점을 지정
					y = j; // 스타트 지점을 지정
					move(x, y, 0);
					list.clear(); // 다 돌고 왔으니까 다음을 돌리기 위해 비워준다
				}
			}

			System.out.println("#" + t + " " + result);
		}
	}

	public static void move(int r, int c, int direct) {
		int nr = r + dr[direct];
		int nc = c + dc[direct];

		if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
			if (direct == 3 && nr == x && nc == y) {
				result = Math.max(list.size(), result);
				return;
			}

			if (!list.contains(map[nr][nc])) {
				list.add(map[nr][nc]);
				move(nr, nc, direct);
				list.remove(list.size() - 1);
			}
		}
		
		direct += 1;
		
		if(direct == 4) // 4개 방향만 돌리니까
			return;

		if(nr >= 0 && nr < n && nc >= 0 && nc < n) { // 범위 안에 들어가면서
			if(!list.contains(map[nr][nc])) { // 없을 때
				list.add(map[nr][nc]); // 추가하고
				move(nr, nc, direct); // 다음 방향으로 이동
				list.remove(list.size()-1);
			}
		}
	}
}