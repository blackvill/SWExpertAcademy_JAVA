package swet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_3289 {
	static int test; // 테스트 케이스의 갯수
	static int n; 
	static int m; // 연산의 갯수
	static int[] parents;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		test = Integer.parseInt(br.readLine());
		for (int t = 1; t <= test; t++) {
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()) + 1;
			m = Integer.parseInt(st.nextToken());
			parents = new int[n];
			makeSet();
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int use = Integer.parseInt(st.nextToken()); // 어떤것을 사용할 것인지
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				
				if (use == 0) {
					union(s, e);
				}
				else if (use == 1) {
					if (check(s, e))
						sb.append("0");
					else
						sb.append("1");
				}
			}
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	public static void makeSet() {
		for (int i = 1; i < n; i++) {
			parents[i] = i; // 자기자신으로 초기화
		}
	}
	
	public static int find(int a) {
		if (a == parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static boolean check(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) return false;
		return true;
	}
}
