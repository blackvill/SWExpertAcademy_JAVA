package swet;

import java.util.Scanner;

public class Solution_D3_1289 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);		
		int t = sc.nextInt(); //테스트케이스

		for (int i = 1; i <= t; i++) {
			String bit = sc.next();
			int count = 0; // 횟수
			char bits = '0';
			for (int j = 0; j < bit.length(); j++) {
				if (bits != bit.charAt(j)) {
					bits = bit.charAt(j);
					count++;
				}
			}
			System.out.printf("#%d %d", i, count);
		}
	}
}
