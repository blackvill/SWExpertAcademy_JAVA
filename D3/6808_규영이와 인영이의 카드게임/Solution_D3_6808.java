package swet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_6808 {
	
	static int[] deck1 = new int[9]; // 규영이의 카드 패
	static int[] deck2 = new int[9]; // 인영이의 카드 패
	static boolean[] isSelected; // 사용된 카드인지 체크
	static boolean[] check = new boolean[9]; // 순열재귀안에서 체크
	static int wincount; // 승리 카운트
	static int losecount; // 패배 카운트
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_6808.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testcase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testcase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			wincount = 0;
			losecount = 0;
			isSelected = new boolean[19];
			for (int i = 0; i < 9; i++) {
				deck1[i] = Integer.parseInt(st.nextToken()); // 규영이 카드 패 입력
				isSelected[deck1[i]] = true; // deck1에서 사용된 수를 사용하였음으로 표시
			}
			for (int i = 1, j = 0; i < 19; i++) { // i는 1부터인 이유는 isSelecte에 0번째는 언제나 빈칸
				if (isSelected[i] == false) // 선택되지 않은 카드면
					deck2[j++] = i; // 인영이 패에 준다.
			}
			perm(0, 0, 0);
			System.out.println("#" + t + " " + wincount + " " + losecount);
		}
	}

	public static void perm(int cnt, int sum_kyu, int sum_in) {
		if (cnt == 9) {
			if (sum_kyu > sum_in) // 규경이가 최종 합이 크면
				wincount++; // 승리 카운트 ++
			if (sum_in > sum_kyu)  // 인영이의 최종 합이 크면
				losecount++; // 패배카운트 ++
			return;
		}
		for (int i = 0; i < 9; i++) {
			if (!check[i]) {
				check[i] = true;
				if (deck1[cnt] > deck2[i]) { // 규영이의 카드가 인영이의 카드보다 크면
					perm(cnt + 1, sum_kyu + deck1[cnt] + deck2[i], sum_in);
					// 다음 카드 탐색
					// 규영이 합 증가
				}
				else { // 아니면
					perm(cnt + 1, sum_kyu, sum_in + deck1[cnt] + deck2[i]);
					// 다음 카드 탐색
					// 인영이 합 증가
				}
				check[i] = false;
			}
		}
	}
}
