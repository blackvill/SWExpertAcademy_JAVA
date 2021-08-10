package swet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Solution_D3_1228 {

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/input_1228.txt"));
		Scanner sc = new Scanner(System.in);
		String str;
		
		for (int t = 1; t <= 10; t++) {
			LinkedList<Integer> list = new LinkedList<>();
			int n = sc.nextInt(); // 원본 암호문의 길이
			for (int i = 0; i < n; i++) // 원본 암호문
				list.add(sc.nextInt());
			int count = sc.nextInt(); // 명령어의 개수
			for (int i = 0; i < count; i++) {
				str = sc.next(); // |를 걸러주기 위함
				int x = sc.nextInt(); // x 인덱스
				int y = sc.nextInt(); // 추가할 개수
				for (int j = 0; j < y; j++) {
					list.add(x, sc.nextInt());
					x++;
				}
			}
			System.out.printf("#%d ", t);
			for (int i = 0; i < 10; i++) {
				System.out.print(list.poll() + " ");
			}
			System.out.println();
		}
	}
}