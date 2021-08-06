package swet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_test_1952_김재욱 {

	static int[] cost = new int[4]; // 비용을 담을 배열 
	static int[] day = new int[12]; // 매달 몇일을 가는지
	static int min; // 최소 비용
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input_1952.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= T; t++) { 
			// 각 이용권의 비용 입력
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 4; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
			}
			// 각 달의 이용일수
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 12; i++) {
				day[i] = Integer.parseInt(st.nextToken());
			}
			// 1년권을 최소로 잡아두면 1년권보다 비싼 경우를 제외함
			min = cost[3];
			find(0, 0);
			System.out.printf("#%d %d%n", t, min);
		}
	}
	public static void find(int idx, int sum) {
        if (sum >= min) // 최소비용보다 비싸면 더이상 돌 필요가 없음
        	return;
        if(idx >= 12) { // 12보다 커지면 12월이상(11월에서 3달권을 선택한다던가 그런 경우)이란 소리
            min = Math.min(min, sum);
            return;
        }
        if(day[idx] == 0) // 해당 달에 수영을 안가는 경우
        	find(idx + 1, sum);
        // 1일권
        find(idx + 1, sum + cost[0] * day[idx]);
        // 1달권
        find(idx + 1, sum + cost[1]);
        // 3달권
        find(idx + 3, sum + cost[2]);
    }
}