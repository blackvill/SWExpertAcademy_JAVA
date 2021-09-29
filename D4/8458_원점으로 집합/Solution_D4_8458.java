package swet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Solution_D4_8458 {
	static int T;
	static int n;
	static int[] x; // 각 x좌표를 받기위한 배열
	static int[] y; // 각 y좌표를 받기위한 배열
	static int result; // 결과값 변수
	static boolean[] odd; // 홀수인지를 저장하기 위한 배열
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		start :
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			
			x = new int[n];
			y = new int[n];
			odd = new boolean[n];
			Arrays.fill(odd, false); // 모든 값을 false로 초기화
			
			result = 0;
			long max = Integer.MIN_VALUE;
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				x[i] = Integer.parseInt(st.nextToken());
				y[i] = Integer.parseInt(st.nextToken());
				
				int goon = Math.abs(x[i]) + Math.abs(y[i]); // 절대값으로 구해 최고값을 매겨준다
				// 3,1일때와 4,0일때의 결과값은 동일하기 때문에
				// x,y의 좌표로 두지 않고 한 직선 위에 오도록 해준다.
				max = Math.max(max, goon); // 최고값을 저장
				if (goon % 2 == 1) // 홀수인지 짝수인지 체크
					odd[i] = true;
			}
			
			// 만약 전부 다 짝수거나 홀수가 아니면 불가능하다.
			boolean check = odd[0];
			for (int i = 1; i < n; i++) {
				if (check != odd[i]) { // 만약에 처음의 값과 다른 값이 나온다면
					System.out.println("#" + t + " -1"); // -1을 출력해주고
					continue start; // 시작으로 이동한다.
				}
			}
			
			int i = 1;
			int sum = 0; // 누적합 변수
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