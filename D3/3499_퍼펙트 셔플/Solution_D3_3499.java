package swet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D3_3499_김재욱2 {
	static int n;
	static int T;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input_3499.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Queue<String> card1 = new LinkedList<String>(); // 쪼개진 1번
		Queue<String> card2 = new LinkedList<String>(); // 쪼개진 2번
		
		T = Integer.parseInt(br.readLine().trim()); // 테케수
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine()); // 글자 수
			if (n % 2 == 0) { // 짝수
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int i = 0; i < n / 2; i++)
					card1.offer(st.nextToken());
				for (int i = 0; i < n / 2; i++)
					card2.offer(st.nextToken());
			}
			else { // 홀수
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int i = 0; i < n / 2 + 1; i++)
					card1.offer(st.nextToken());
				for (int i = 0; i < n / 2; i++)
					card2.offer(st.nextToken());
			}
			sb.append("#").append(t).append(" ");
			for (int i =0; i < n / 2; i++) {
				sb.append(card1.poll()).append(" ");
				sb.append(card2.poll()).append(" ");
			}
			if (!card1.isEmpty())
				sb.append(card1.poll()).append(" ");
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
