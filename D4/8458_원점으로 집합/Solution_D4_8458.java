package swet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Solution_D4_8458 {
	static int T;
	static int n;
	static int[] x;
	static int[] y;
	static int result;
	static boolean[] odd;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		start :
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			
			x = new int[n];
			y = new int[n];
			odd = new boolean[n];
			Arrays.fill(odd, false);
			
			result = 0;
			long max = Integer.MIN_VALUE;
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				x[i] = Integer.parseInt(st.nextToken());
				y[i] = Integer.parseInt(st.nextToken());
				
				int goon = Math.abs(x[i]) + Math.abs(y[i]);
				max = Math.max(max, goon);
				if (goon % 2 == 1) // 홀수인지 짝수인지 체크
					odd[i] = true;
			}
			
			// 만약 전부 다 짝수거나 홀수가 아니면 불가능하다.
			boolean check = odd[0];
			for (int i = 1; i < n; i++) {
				if (check != odd[i]) {
					System.out.println("#" + t + " -1");
					continue start;
				}
			}
			
			int i = 1;
			int sum = 0;
			while (true) {
				// sum의 값이 짝수일때는 짝수로만 이동 가능하고, 홀수일때는 홀수로만 이동 가능
				// max값보다 합이 크고 |max - sum|의 값이 짝수일 때
				if (sum >= max && Math.abs(sum - max) % 2 == 0)
					break;
				sum += i;
				i++;
				result++;
//				System.out.println("max :" + max + ", sum : " + sum + ", result : " + result);
			}
			
			System.out.println("#" + t + " " + result);
		}
	}
}