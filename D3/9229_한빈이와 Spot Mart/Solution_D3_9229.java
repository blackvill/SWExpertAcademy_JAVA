package swet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import sun.util.locale.StringTokenIterator;

public class Solution_D3_9229 {
	static int n; // 과자봉지의 개수
	static int weight_sum; // 무게합
	static int max;
	static int sum;
	static int[] snack;
	static int[] isSelected;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input_9229.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken()); // 과자 개수
			weight_sum = Integer.parseInt(st.nextToken()); // 과자 봉지 무게 총합
			max = -1;
			sum = 0;
			snack = new int[n];
			isSelected = new int[2];
			StringTokenizer sn = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n; i++) {
				snack[i] = Integer.parseInt(sn.nextToken());
			}
			dfs(0, 0);
			
			System.out.println("#" + t + " " + max);
		}
	}
	
	private static void dfs(int cnt, int start) {
		if (cnt == 2) {
			if (sum > weight_sum)
				return;
			max = Math.max(max, sum);
			return;
		}
		for (int i = start; i < n; i++) {
			isSelected[cnt] = snack[i];
			sum += snack[i];
            dfs(cnt+1, i+1);
            sum -= snack[i];
		}
	}
}