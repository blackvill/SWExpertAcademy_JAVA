package swet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_test_2112 {
	static int T; // 테케수
	static int D; // 행
	static int W; // 열
	static int K; // 통과기준
	static int result;
	static int map[][]; // map
	static int copy[][]; // map을 복구하기 위한 copy
	static boolean[] isSelected; // 부분집합에서 선택한 것을 나타낼 boolean배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			D = Integer.parseInt(st.nextToken()); // 행
			W = Integer.parseInt(st.nextToken()); // 열
			K = Integer.parseInt(st.nextToken()); // 통과기준
			
			map = new int[D][W];
			copy = new int[D][W];
			isSelected = new boolean[D];
			result = Integer.MAX_VALUE;
			
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < W; j++) {
					map[i][j] = copy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if (check()) {
				System.out.println("#" + t + " 0");
			}
			else {
				subset(0);
				System.out.println("#" + t + " " + result);
			}
			
		}
	}
	
	// 부분집합으로 어디에 약물을 투여할 지 선택
	public static void subset(int cnt) {
		if (cnt == D) {
			dfs(isSelected, 0, 0);
			mapReset();
			return;
		}
		isSelected[cnt] = true;
		subset(cnt+1);
		isSelected[cnt] = false;
		subset(cnt+1);
	}
	
	// 각 행마다 약물을 투여해보고 결과구하기
	public static void dfs(boolean[] check, int cnt, int index) {
		if (cnt >= result) // 현재 결과값보다 크면 더 탐색할 필요가 없음
			return;
		
		if (index == D) {
			if (check()) { // 유효성 검사하고
				result = Math.min(result, cnt); // 통과하면 최소값으로 결과 갱신
			}
			return;
		}
		
		if (check[index]) {
			// A로 약물 투여
			Arrays.fill(map[index], 0);
			dfs(check, cnt + 1, index + 1);
			
			// B로 약물 투여
			Arrays.fill(map[index], 1);
			dfs(check, cnt + 1, index + 1);
		}
		else {
			dfs(check, cnt, index + 1);
		}
	}

	// 행을 기준으로 k만큼의 구역이 있는지를 확인
	public static boolean check() { 
		for (int i = 0; i < W; i++) {
			int cnt = 1; // 시작점을 포함하기 때문에 1
			int start = map[0][i]; // 시작점을 기준
			boolean pass = false;
			
			for (int j = 1; j < D; j++) {
				if (start == map[j][i]) { // 시작과 같으면
					cnt++; // 수를 증가
				}
				else { // 다르면
					start = map[j][i]; // 시작점을 바꿔주고
					cnt = 1; // cnt를 1로 초기화
				}
				
				if (cnt == K) { // 합격 기준을 넘어가면 더 체크할 필요가 없음
					pass = true;
					break;
				}
			}
			if (pass == false) // 하나라도 만족하지 않으면 실패기 때문에 돌아가기
				return false;
		}
		return true;
	}
	
	// 맵 초기화하기
	public static void mapReset() {
		for (int i = 0; i < D; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = copy[i][j];
			}
		}
	}
}
