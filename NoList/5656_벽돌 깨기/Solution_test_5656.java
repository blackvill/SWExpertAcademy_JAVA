package swet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_test_5656 {
	static int T; // 테케수
	static int N; // 구슬의 수
	static int W; // 열
	static int H; // 행
	static int map[][]; // map
	static int copy[][]; // map을 초기화할때 사용할 copy배열
	static int numbers[]; // 중복 순열에서 선택한 값을 저장하기 위한 배열
	static int min;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			// 초기화
			map = new int[H][W];
			copy = new int[H][W];
			numbers = new int[N];
			min = Integer.MAX_VALUE;
			
			// map 입력 받기
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = copy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 중복 순열 돌리기
			perm(0);
			System.out.println("#" + t + " " + min);
		}
	}

	public static void perm(int cnt) {
		if (cnt == N) {
			// 탐색 시작
			start(numbers);
			// 최소값을 갱신
			min = Math.min(min, countMap());
			// map을 초기화
			mapcopy();
			
			return;
		}
		
		// 중복을 포함한 순열을 구한다.
		for (int i = 0; i < W; i++) {
			numbers[cnt] = i;
			perm(cnt + 1);
		}
	}
	
	public static void start(int[] arr) {
		int x = 0;
		int y = 0;
		
		for (int i = 0; i < N; i++) { // 구슬의 개수만큼 반복
			for (int j = 0; j < H; j++) { // 열은 고정이므로 행만 이동
				if (map[j][arr[i]] != 0) { // 내려가면서 0이 아니면 맨 위의 블록이라는 소리
					x = j; // 해당 행
					y = arr[i]; // 해당 열
					break;
				}
			}
			bfs(x, y); // bfs 탐색 시작
			blockdown(); // 벽돌이 부서지고 위에 있던 블록이 아래로 내려옴
		}
	}
	
	public static void blockdown() {
		Stack<Integer> s = new Stack<>();
		
		for (int i = 0; i < W; i++) { // 열만큼 반복
			for (int j = 0; j < H; j++) { // 행만큼 반복
				if (map[j][i] != 0) // 0이 아니면 벽돌이니까
					s.add(map[j][i]); // 벽돌의 값을 저장
			}
			for (int j = H - 1; j >= 0; j--) { // 밑에서부터 올라가면서
				if (s.isEmpty()) // 스택이 비었다면 0으로
					map[j][i] = 0;
				else // 스택이 남아있다면
					map[j][i] = s.pop(); // 스택의 값을 map에 저장
			}
		}
	}
	
	public static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {x, y, map[x][y]});
		map[x][y] = 0;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int power = cur[2];
			
			for (int i = 1; i < power; i++) { // 블록의 숫자만큼 반복
				for (int j = 0; j < 4; j++) {
					int nx = cur[0] + dr[j] * i; // 블록의 숫자만큼 움직여야하니까
					int ny = cur[1] + dc[j] * i;
					
					// 범위를 벗어나거나 블록이 아닌 경우
					if (nx < 0 || nx >= H || ny < 0 || ny >= W || map[nx][ny] == 0)
						continue;
					
					if (map[nx][ny] != 0) { // 0이 아니라면 블록이니까
						q.add(new int[] {nx, ny, map[nx][ny]}); // 해당 블록을 큐에 넣어주고
						map[nx][ny] = 0; // 폭발 처리고 0으로 바꿔준다.
					}
				}
			}
		}
	}
	
	// map에 몇개의 블록이 남았는지 수를 세기 위한 함수
	public static int countMap() {
		int count = 0;
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] != 0)
					count++;
			}
		}
		return count;
	}
	
	// map을 초기화하는 함수
	public static void mapcopy() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = copy[i][j];
			}
		}
	}
}
