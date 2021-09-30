package swet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_test_1767 {
	static int T; // 테케수 
	static int n; // n
	static int[][] map; // 값을 입력받을 map
	static int[][] map_copy; // map을 복사해주기 위한 공간
	static int core; // 코어의 개수
	static int result; // 결과
	static ArrayList<Processor> mc; // Processor의 ArrayList
	static boolean[] is_used; // 사용을 했는지 처리하기 위한 배열

	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};

	// 프로세서의 x, y값을 저장하는 클래스
	public static class Processor {
		int x;
		int y;

		public Processor(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());

			// 여기서 값 다 초기화
			result = Integer.MAX_VALUE;
			map = new int[n][n];
			map_copy = new int[n][n];
			mc = new ArrayList<>();
			is_used = new boolean[12]; // 최대값 12
			core = 0;

			// map에 값 입력
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					map_copy[i][j] = map[i][j]; // map에 입력되어진 값을 map_copy에 넣어준다
					if (i >= 1 && i < n - 1 && j >= 1 && j < n - 1 && map[i][j] == 1) {
						// 가장자리는 이미 전기가 들어가고 있음
						core++; // 코어의 개수 증가
						mc.add(new Processor(i, j)); // 가장자리가 아닌 것들의 정보를 저장
					}
				}
			}

			for(int i = core; i >= 0; i--) { // 최고 개수부터 감소시키면서 조합돌리기
				// 구하고자 하는게
				// 1순위 : 최대한 많은 개수의 연결
				// 2순위 : 같은 개수라면 최대한 적은 길이
				comb(0, 0, i);
				if(result < Integer.MAX_VALUE)
					break; // 결과가 나왔다는 뜻임
			}
			System.out.println("#" + t + " " + result);
		}
	}

	// 조합 처리
	public static void comb(int cnt, int start, int r) {
		if (cnt == r) { // 골라야 할 갯수를 다 골랐다면
			check(0, 0); // 체크 수행
			return;
		}
		
		for (int i = start; i < core; i++) {
			is_used[i] = true; // 해당 번째를 사용한 것으로 변경
			comb(cnt + 1, i + 1, r);
			is_used[i] = false; // 해당 번째를 사용하지 않은 것으로 변경
		}
	}

	// 전선을 깔 수 있는지를 체크
	public static void check(int index, int length) {
		if (index == core) { // 다 돌았다면
//			System.out.println(length);
			result = Math.min(result, length);
			// 최소값을 갱신해준다
			return;
		}

		if (is_used[index] != true) {
			// 조합에서 사용을 한 것인지를 확인하고 아니라면 다음것으로 넘김
			check(index + 1, length);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int len = 0; // 전선의 길이를 구하기 위한 변수
			int nx = mc.get(index).x; // x좌표 값을 가져온다
			int ny = mc.get(index).y; // y좌표 값을 가져온다
			boolean ok = false; // 마지막까지 도달하는지 판단용

			// 무한 반복
			while(true) {
				// 해당 값을 계속 더해준다
				nx += dr[i];
				ny += dc[i];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= n) {
					// 끝까지 가면
					ok = true;
					break;
				}
				
				if(map_copy[nx][ny] != 0) // 빈칸이 아니면 못가니까
					break;
				map_copy[nx][ny] = 2; // 전선 표시
				len++; // 전선 길이 합
			}
			
			if(ok == true) // 참이면 그 다음 것에 대해 선을 연결
				check(index + 1, length + len);

//			for (int r = 0; r < n; r++) {
//				// 탐색이 끝나면 그 다음 탐색을 해야하므로 map_copy를 map으로 초기화
//				for (int c = 0; c < n; c++) {
//					map_copy[r][c] = map[r][c];
//				}
//			}
//					// 근데 이렇게 하면 아예 처음으로 돌아가니까 문제가 생기지 않을까..

			
			// 그냥 해당 상태에서 바뀐거만 뒤로 돌리자.
			while (true) {
				// 위에선 +해줬으니 -해주면서 반대로 돌아가자
				nx -= dr[i];
				ny -= dc[i]; 
				
				// 0 > 2로 바꿔줬으니 2 > 0으로 바꿔주자.
				if (map_copy[nx][ny] == 2)
					map_copy[nx][ny] = 0;
				// 1이면 시작한 곳이니까 여기에 도달하면 탈출시키자.
				if (map_copy[nx][ny] == 1)
					break;
			}
			// 근데 이렇게 되니까 굳이 map을 복사할 필요가 없었네;;;
		}
	}
}
