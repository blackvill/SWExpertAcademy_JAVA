package swet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_3307 {
	static int T;
	static int n;
	static int[] arr;
	static int[] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			
			arr = new int[n + 1];
			dp = new int[n + 1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 1; i <= n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int max = 0;
			
			for (int i = 1; i <= n; i++) {
				dp[i] = 1; // 자기자신만 세는 경우가 있으니 기본 값으로 1을 준다
				for (int j = 1; j < i; j++) {
					if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {
						dp[i] = dp[j] + 1;
					}
				}
				max = Math.max(max, dp[i]);
			}
			System.out.println("#" + t + " " + max);
		}
	}
}