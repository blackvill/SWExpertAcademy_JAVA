package swet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_test_4012 {
	static int n;
	static int[][] map; // 시너지 배열
	static boolean[] visited; //사용한 것인지
	static int min; // 최소값
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 테케
		int testcase = Integer.parseInt(br.readLine());
		// 테케만큼 반복
		for (int t = 1; t <= testcase; t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			visited = new boolean[n];
			min = Integer.MAX_VALUE;
			
			// 시너지 입력
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			comb(0, 0);
			System.out.printf("#%d %d%n", t, min);
		}
	}
	
	public static void comb(int cnt, int start) {
		if (cnt == n / 2) { // cnt가 n/2이면 총 음식의 수 중 반을 선택했다는 의미
			int a = 0; // a음식의 시너지
			int b = 0; // b음식의 시너지
			int diff = 0; // 두 음식의 시너지 간의 차
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) { // 이렇게 돌면 전체를 돌기때문에 같은게 2번 더해짐
					if (visited[i]) {// 만약 사용한 음식이라면
						a += map[i][j]; 
						a += map[j][i]; // a시너지에 추가
					}
					else {// 아니라면
						b += map[i][j];
						b += map[j][i]; // b시너지 추가
					}
				}
			}
			a /= 2; // 2번 더해졌기때문에
			b /= 2; // a와 b를 2로 나눠줌
			diff = Math.abs(a - b); // 절대값으로 시너지 간의 차를 구함
			min = Math.min(diff, min); // 더 작은 것을 min으로 설정
			return;
		}
		
		for (int i = start; i < n; i++) {
			visited[i] = true; // 음식을 사용함
			comb(cnt + 1, i + 1); // 다음 재귀
			visited[i] = false; // 사용했던것을 다시 풀어줌
		}
	}
}
