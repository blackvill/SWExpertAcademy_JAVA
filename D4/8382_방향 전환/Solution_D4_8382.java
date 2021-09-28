package swet;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_8382 {
	static int T;
	static int x1, y1;
	static int x2, y2;
	static int X, Y;
	static int goon;
	static int move;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 값 입력
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());

			// 0,0을 기준으로 위치를 설정
			X = Math.abs(x2 - x1);
			Y = Math.abs(y2 - y1);
			
			goon = (X + Y) / 2;
			move = Math.abs(X - goon) + Math.abs(Y - goon) + 2 * goon;
			// 군으로부터 떨어진 x거리와 y거리의 합 + 군 (1,1일때 2군 == 군은 * 2)
			
			System.out.println("#" + t + " " + move);
		}
	}
}