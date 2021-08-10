package swet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

import jdk.internal.util.xml.impl.Input;

public class Solution_D4_1233 {
    static int check;
    
    public static void main(String[] args) throws IOException {
    	System.setIn(new FileInputStream("res/input_1233.txt"));
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	for (int t = 1; t <= 10; t++) {
            check = 1; // 기본값을 유효하다로 적용
            int n = Integer.parseInt(br.readLine().trim());
            for(int i = 0; i < n; i++) {
            	String[] str = br.readLine().split(" "); // 입력받은것을 " "을 기준으로 잘라서 배열에 넣는다
            	if (i < n / 2) { // n / 2까지는 연산자가 나와야한다
            		if (str[1].equals("+") || str[1].equals("-") || str[1].equals("*") || str[1].equals("/")) {
            			// 연산자가 나오면 넘어가고
            		}
            		else { // 연산자가 나오지 않으면
            			check = 0; // 유효하지 않음으로 변경
            		}
            	}
            	else { // 이후에는 숫자만 나와야한다.
            		if (str[1].equals("+") || str[1].equals("-") || str[1].equals("*") || str[1].equals("/")) {
            			check = 0; // 유효하지 않은으로 변경
            		}
            	}
            }
            System.out.println("#"+ t + " " + check);
        }
    }
}
