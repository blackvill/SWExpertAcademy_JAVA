package swet;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_D3_11856_김재욱 {
	static int test;
	static int[] visited;
	static boolean check;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		test = Integer.parseInt(br.readLine());
		for (int t = 1; t <= test; t++) {
			String str = br.readLine();
			visited = new int[26];
			check = true;
			for (int i = 0; i < str.length(); i++) {
				visited[str.charAt(i) - 'A']++;
			}
			int count = 0;
			for (int i = 0; i < 26; i++) {
				if (visited[i] > 0) {
					if (visited[i] != 2) {
						check = false; // check를 false
						break;
					}
					count++;
				}
			}
			if (check && count == 2)
				System.out.println("#" + t + " Yes");
			else
				System.out.println("#" + t + " No");
		}
	}
}