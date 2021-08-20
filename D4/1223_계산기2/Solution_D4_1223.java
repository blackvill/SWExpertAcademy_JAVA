package swet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_D4_1223 {
	static int n;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> oper = new Stack<>();
		Stack<Integer> num = new Stack<>();
		
		for (int t = 1; t <= 10; t++) {
			String str = ""; // 후위연산자로 만들 문자열
			n = Integer.parseInt(br.readLine());
			String line = br.readLine(); // 한번에 다 받아온다.
			for (int i = 0; i < n; i++) {
				char c = line.charAt(i); // 받은 문자열을 c에 담아 준다
				if (c - '0' >= 0 && c - '0' <= 9) { // 숫자면
					str += c; // 문자열에 바로 추가
				}
				else { // 아니라면
					if (c == '*') // 곱하기일 경우
						oper.push(c); // 연산자 스택에 추가
					else { // 아니면
						while (!oper.isEmpty()) // oper 스택이 비어있지 않다면
							str += oper.pop(); // 연산자를 문자열에 추가
						oper.push(c); // 연산자 스택에 추가
					}
				}
			}
			while (!oper.isEmpty()) { // 처리 안되고 남은 연산자를 문자열에 추가
				str += oper.pop();
			}
			
			for (int i = 0; i < n; i++) {
				char c = str.charAt(i);
				
				if (c - '0' >= 0 && c - '0' <= 9) { // 숫자라면
					num.push(c - '0'); // 숫자로 바꿔서 스택에 넣기
				}
				else { // 아니면
					int n1 = num.pop(); // 숫자 1
					int n2 = num.pop(); // 숫자 2
					
					if (c == '+') // +라면
						num.push(n1 + n2); // +해서 넣기
					else if (c == '*') // *라면
						num.push(n1 * n2); // *해서 넣기
				}
			}
			System.out.println("#"+ t + " " +num.pop());
		}
	}

}
