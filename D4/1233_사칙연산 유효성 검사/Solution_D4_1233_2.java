package swet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution_D4_1233_2 {
 
    static String S = "";
  
    public static void main(String[] args) throws NumberFormatException, IOException {
    	System.setIn(new FileInputStream("res/input_1233.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int testcase = 1; testcase <= 10; testcase++) { //테스트 케이스 10번 반복    
            int n = Integer.parseInt(br.readLine());
            char[] list = new char[n + 1];
            S = "";
            for (int j = 1; j <= n; j++) { // 정점의 총 수만큼 반복
                String str = br.readLine();
                StringTokenizer st = new StringTokenizer(str, " ");
 
                while (st.hasMoreTokens()) { // 입력된 token이 존재하면 반복
                    int node = 0; //node 입력
                    char operator = 0; //연산자 입력
                    int num1 = 0; //왼쪽 자식 노드
                    int num2 = 0; //오른쪽 자식 노드
 
                    // 입력이 4개인 경우, 3개인 경우, 2개인 경우가 있으므로...
                    if (st.countTokens() == 4) {
                        node = Integer.parseInt(st.nextToken());
                        operator = st.nextToken().charAt(0);
                        num1 = Integer.parseInt(st.nextToken());
                        num2 = Integer.parseInt(st.nextToken());
                    }
                    else if(st.countTokens() == 3) {
                    	node = Integer.parseInt(st.nextToken());
                        operator = st.nextToken().charAt(0);
                        num1 = Integer.parseInt(st.nextToken());
                    }
                    else if (st.countTokens() == 2) {
                        node = Integer.parseInt(st.nextToken());
                        operator = st.nextToken().charAt(0);
                    }
 
                    // 1번 노드 저장
                    if (node == 1) {
                        list[1] = operator;
                        break;
                    }
                    
                    // 1번 노드가 아닌경우 왼쪽 자식노드가 비어있으면 왼쪽 자식노드에 먼저 넣어줌
                    for (int k = 1; k < list.length; k++) { //1부터 list 사이즈만큼 반복
                        if (k * 2 <= n && list[k * 2] == 0) { // 왼쪽 자식노드 비어있으면
                            list[k * 2] = operator;
                            break;
                        } else if (k * 2 + 1 <= n && list[k * 2 + 1] == 0) { // 왼쪽 자식노드가 있으면
                            list[k * 2 + 1] = operator;
                            break;
                        }
                    }
                }
            }
            
            inOrder(list, 1); //트리의 루트인 1부터 중위 순회 재귀 함수 호출 => 중위표기법완성
            
            // 중위표기법 완성하고 출력 조건 확인하기
            boolean check = false;
            for (int j = 1; j < S.length(); j++) {
                // 현재 값이 연산자이면서
                if (S.charAt(j) == '+' || S.charAt(j) == '-' || S.charAt(j) == '/' || S.charAt(j) == '*') {
                    // 그 전 값도 연산자 였으면 제대로 된 수식이 아니므로 0출력하고, check = true
                    if (S.charAt(j - 1) == '+' || S.charAt(j - 1) == '-' || S.charAt(j - 1) == '/' || S.charAt(j - 1) == '*') {
                        System.out.println("#"  + testcase+" " + 0);
                        check = true;
                        break;
                    }
                // 현재 값이 숫자이면서
                } else {
                    // 그 전 값이 연산자 였으면 제대로 된 수식이므로 continue
                    if (S.charAt(j - 1) == '+' || S.charAt(j - 1) == '-' || S.charAt(j - 1) == '/' || S.charAt(j - 1) == '*')
                        continue;
                    // 그 전 값이 숫자였으면 잘못된 수식이므로 0출력하고, check = true
                    else {
                        System.out.println("#"  + testcase+" " + 0);
                        check = true;
                        break;
                    }
                }
            }
            // 위 조건이 끝나면 제대로 된 수식이므로 check = flase인 상태이면 1을 출력
            if (!check) {
                System.out.println("#"  + testcase+" " + 1);
            }
        }
    }

    // 중위 순회 함수
    public static void inOrder(char[] arr, int index) {
        // 왼쪽자식 방문
        if (index * 2 < arr.length && arr[index * 2] != 0) {
            inOrder(arr, index * 2);
        }
        S += arr[index]; // s에 문자열로 1 * 2 - 3 중위 표기법으로
        // 오른쪽 자식 방문
        if (index * 2 + 1 < arr.length && arr[index * 2 + 1] != 0) {
            inOrder(arr, index * 2 + 1);
        }
    } 
}