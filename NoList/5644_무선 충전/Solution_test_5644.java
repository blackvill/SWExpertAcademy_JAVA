package swet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_test_5644 {
	static int T;
	static int m;
	static int a;
	static int result;
	static int[] arrA;
	static int[] arrB;
	static int[][] move;
	// 이동방향
	// 0 : 이동 x	1 : 위	2 : 오른쪽	3 : 아래	4 : 왼쪽
	static int[] dr = {0, -1, 0, 1, 0};
	static int[] dc = {0, 0, 1, 0, -1};
	static ArrayList<BC> list;
	
	public static class BC {
		int x;
		int y;
		int coverage;
		int perf;
		
		public BC(int x, int y, int coverage, int perf) {
			this.x = x;
			this.y = y;
			this.coverage = coverage;
			this.perf = perf;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			m = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			
			move = new int[m][2];
			arrA = new int[a];
			arrB = new int[a];
			
			// 사람 a와 b의 이동 정보에 대한 것을 저장
			// [i][0] 은 a의 이동정보
			// [i][1] 은 b의 이동정보
			for (int p = 0; p < 2; p++) {
				st = new StringTokenizer(br.readLine());
				
				for (int i = 0; i < m; i++) {
					move[i][p] = Integer.parseInt(st.nextToken());
				}
			}
			
			// BC의 정보를  list에 저장
			list = new ArrayList<>();
			for (int i = 0; i < a; i++) {
				st = new StringTokenizer(br.readLine());
				
				int loc_x = Integer.parseInt(st.nextToken());
				int loc_y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				// 문제의 그림을 보면 2,5가 아닌 5,2로 적혀있어서
				// x와 y의 위치를 변경
				list.add(new BC(loc_y, loc_x, c, p));
			}
			location();
			
			System.out.println("#" + t + " " + result);
		}
	}
	
	public static void location() {
		// 사람 a의 시작 좌표
		int x1 = 1;
		int y1 = 1;
		// 사람 b의 시작 좌표
		int x2 = 10;
		int y2 = 10;
		
		arrA = check(x1, y1);
		arrB = check(x2, y2);
		result = charge(arrA, arrB);

		// a와 b의 이동처리
		for (int i = 0; i < m; i++) {
			x1 = x1 + dr[move[i][0]];
			y1 = y1 + dc[move[i][0]];
			x2 = x2 + dr[move[i][1]];
			y2 = y2 + dc[move[i][1]];
			arrA = check(x1, y1);
			arrB = check(x2, y2);
			result += charge(arrA, arrB);
		}
	}
	
	// 해당 좌표에서 각 충전기의 거리 비교를 통해 충전량을 arr에 저장시키기 위한 메소드
	public static int[] check(int x1, int y1) {
		int[] arr = new int[a];
		
		// a의 이동에 따른 충전량 저장
		for (int i = 0; i < a; i++) {
			int x = Math.abs(x1 - list.get(i).x);
			int y = Math.abs(y1 - list.get(i).y);
			// 거리가 coverage(충전범위)안에 들어오면
			if (x + y <= list.get(i).coverage)
				arr[i] = list.get(i).perf; // 해당 BC의 충전량 
			else
				arr[i] = 0; // 범위 밖이면 0
		}
		return arr; // arr의 값을 리턴
	}
	
	public static int charge(int[] arrA, int[] arrB) {
		// 서로의 충전량의 최고를 구하기
		int max = 0;
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < a; j++) {
				int sum = arrA[i] + arrB[j];
				
				// 같은 곳의 충전기를 사용할 경우 이를 절반씩 나눠서 사용한다.
				if (i == j && arrA[i] == arrB[j]) {
					sum /= 2;
				}
				max = Math.max(max, sum);
			}
		}
		return max;
	}
}
