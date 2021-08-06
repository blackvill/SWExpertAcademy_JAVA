package swet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 중복 순열을 만든다.
 * 2. 완성된 순열 => 인접된 국가가 같은 color가 안되도록 한다
 * 3. 기존에 입력된 것과 비교해서
 * 4. 바뀌는 개수 count++
 * 
 */
public class Solution_D4_7208_김재욱2 {
	
	static int N;			//국가 숫자 
	static int map[][];		//인접 국가 위치 
	static int color[];		//국가에 배정된 color
	static int fill[];		//새로 배정 시킬  color => 순열
	static int min	 ;		//color에 대한 최소 변경 값
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine().trim());
		StringTokenizer st = null;
		for(int t=0; t<T; ++t) {
			N = Integer.parseInt(in.readLine().trim());
			map = new int[N][N];
			color=new int[N];
			fill= new int[N];
			st = new StringTokenizer(in.readLine().trim());
			for (int i = 0; i <N; i++) { //color값 읽기
				color[i] = Integer.parseInt(st.nextToken().trim());
			}
			//국가 인접 정보 load
			for (int i = 0; i <N; i++) {
				st = new StringTokenizer(in.readLine().trim());
				for (int j = 0; j <N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken().trim());
				}
			}
			min = Integer.MAX_VALUE;
			permutation(0);
			System.out.println("#"+(t+1)+" "+min);
		}
	}
	/**새로 생성한 색상배열(fill)이 국가 위치에 고려해서 모두 중복 되지 않는지 
	 * 검사  */
	private static boolean allAvailable() {
		for (int i = 0; i <N; i++) {    //현재 검사할 국가
			for (int j = 0; j <N; j++) {//인접된 국가 
//				      인접 된 경우             현재 국가의 색   인접 국가의 색
				if(map[i][j]==1 && fill[i] == fill[j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 새로운  color에 대한 순열을 만드는 기능 
	 * @param i
	 */
	private static void permutation(int cnt) {
		if(cnt == N) {
			if(allAvailable()) {
				//새로 생성한 색상 값이  인접 국가간에 중복이 없으면
				int change =0;
				for (int i = 0; i < N; i++) {
//					기존에 배치된 색과  새로 칠한 색이 다르면
					if(color[i] != fill[i]) {
						change++;			//
					}
				}
				min = Math.min(min, change);
			}
			return;
		}
		
		for (int i = 1; i <=4; i++) {
			fill[cnt]=i;
			permutation(cnt+1);
		}
	}
}
