package swet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_D4_1218 {
	static int length;
	static String str;
	static char c;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input_1218.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> stack = new Stack<>();
		
		for (int t = 1; t <= 10; t++) {
			length = Integer.parseInt(br.readLine()); //입력값 길이
			str = br.readLine();
			for (int i = 0; i < length; i++) {
				c = str.charAt(i);
				if (c == '{' || c == '[' || c == '<' || c == '(' ) {// 열리는 괄호의 경우면 그냥 stack에 추가
					stack.push(c);
				}
				else if (c == '}') {
					if (stack.peek() == '{')
						stack.pop();
					else {
						break;
					}
				}
				else if (c == ']') {
					if (stack.peek() == '[')
						stack.pop();
					else {
						break;
					}
				}
				else if (c == ')') {
					if (stack.peek() == '(')
						stack.pop();
					else {
						break;
					}
				}
				else if (c == '>') {
					if (stack.peek() == '<')
						stack.pop();
					else {
						break;
					}
				}
			}
			if (stack.isEmpty())
				System.out.printf("#%d 1%n", t);
			else
				System.out.printf("#%d 0%n", t);
			stack.clear();
		}
	}
}