package swet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_3234_perm {
	static int n;
	static int[] weight;
	static int[] w;
	static boolean[] visited;
	static int count;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testcase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testcase; t++) {
			n = Integer.parseInt(br.readLine());
			count = 0;
			weight = new int[n];
			w = new int[n];
			visited = new boolean[n];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
			}
			perm(0);
			System.out.printf("#%d %d%n", t, count);
		}
	}
	
	public static void perm(int cnt) { // 순열로 줄세우기
		if (cnt == n) {
			check(0, 0, 0);
			return;
		}
			
		for (int i = 0; i < n; i++) {
			if (visited[i]) continue;
			visited[i] = true; // 사용한것으로 처리
			w[cnt] = weight[i]; // w배열에 무게 배열값을 넣는다
			perm(cnt + 1); // 순열 재귀
			visited[i] = false; // 돌아왔으니 비사용으로 처리
		}
	}
	
	public static void check(int index, int l, int r) { // 여기서 확인
        if(index == n) { //index == n이라는 소리는 모든 추를 사용했다는 것
        	count++; // 카운트 증가
            return;
        }
        check(index + 1, l + w[index], r); // 왼쪽에 추를 올리는 방법
        if (r + w[index] <= l) // 오른쪽에 추를 추가할 때 왼쪽보다 작으면
        	check(index + 1, l, r + w[index]); // 오른쪽에 추를 올리는 방법
    }
}
