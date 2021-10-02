package swet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_test_4014 {
	static int T;
	static int n;
	static int x;
	static int[][] map;
	static int count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());

			map = new int[n][n];
			count = 0;

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i == 0) {
						rowsearch(i, j);
					}
					if (j == 0) {
						colSearch(i, j);
					}
				}
			}
			System.out.println("#" + t + " " + count);
		}
	}

	public static void rowsearch(int r, int c) {
		int length = 1;
		int height = map[r][c];

		if (r == 0) {
			for (int i = 1; i < n; i++) {
				if (height - map[i][c] == 0) { // 높이가 같다면
					length++;
				}
				else if (height - map[i][c] == 1) { // 다음지점의 높이가 1 낮다면
					if (n - i < x) { // 남아있는 길이가 x보다 작다면 이후에 다 같은 높이가 나와도 경사로 생성이 불가능
						return;
					}
					else {
						for (int j = 1; j < x; j++) {
							if (height - map[++i][c] != 1)
								return;
						}
					}
					height = map[i][c]; // 높이를 바꿔준다
					length = 0; // 길이를 초기화 해준다
				}
				else if (height - map[i][c] == -1) { // 다음 지점의 높이가 1 높다면
					if (length >= x) { // 경사로를 설치할 수 있는 길이가 충분하다면
						height = map[i][c];
						length = 1;
					}
					else
						return;
				}
				else // 나머지는 경사로 생성 불가
					return;
			}
//			System.out.println("r일때 r : " + r + " c : " + c);
			count++;
		}	
	}

	public static void colSearch(int r, int c) {
		int length = 1;
		int height = map[r][c];

		if (c == 0) {
			for (int i = 1; i < n; i++) 
				if (height - map[r][i] == 0) { // 높이가 같다면
					length++;
				}
				else if (height - map[r][i] == 1) { // 다음지점의 높이가 1 낮다면
					if (n - i < x) { // 남아있는 길이가 x보다 작다면 이후에 다 같은 높이가 나와도 경사로 생성이 불가능
						return;
					}
					else {
						for (int j = 1; j < x; j++) {
							if (height - map[r][++i] != 1)
								return;
						}
					}
					height = map[r][i]; // 높이를 바꿔준다
					length = 0; // 길이를 초기화 해준다
				}
				else if (height - map[r][i] == -1) { // 다음 지점의 높이가 1 높다면
					if (length >= x) { // 경사로를 설치할 수 있는 길이가 충분하다면
						height = map[r][i];
						length = 1;
					}
					else
						return;
				}
				else // 나머지는 경사로 생성 불가
					return;
		}
//		System.out.println("c일때, r : " + r + " c : " + c);
		count++;
	}
}
