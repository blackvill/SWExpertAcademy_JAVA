package swet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D3_1225 {
	static int T;
	static int count;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res/input_1225.txt"));
		Queue<Integer> q = new LinkedList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 0; t < 10; t++) {
			T = Integer.parseInt(br.readLine().trim()); // 몇번째 테케인지 입력
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 8; i++)
				q.offer(Integer.parseInt(st.nextToken()));
			
			count = 1;
			while (q.peek() - count > 0) {
				q.offer(q.peek() - count); // 먼저 큐에 넣어주고
				q.poll(); // 큐의 peek을 제거
				count++; // count 증가
				if (count == 6) // 증가를 했을떄 6이라면 1사이클을 돈 것이기 때문에
					count = 1; // count를 1로 변경
			}
			q.offer(0);
			q.poll();
			// 출력문
			System.out.printf("#%d ", T);
			for (int k = 0; k < 8; k++)
				System.out.printf(q.poll() + " ");
			System.out.println();
		}
	}

}
