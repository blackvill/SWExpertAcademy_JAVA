package swet;

import java.io.*;
import java.util.*;

class Solution_D2_1983
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());

		String []grade = {"A+","A0","A-","B+","B0","B-","C+","C0","C-","D0"};   //학점 배열

		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer tk = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(tk.nextToken());   //학생수
			int K = Integer.parseInt(tk.nextToken());   //K번째
			
			Double []ans = new Double[N];   //총점담을 배열
			
			double temp=0.0;
			
			for(int i=0;i<N;i++){
				tk = new StringTokenizer(bf.readLine());
				int mid = Integer.parseInt(tk.nextToken());
				int fin = Integer.parseInt(tk.nextToken());
				int rep = Integer.parseInt(tk.nextToken());
				double sum = mid*0.35 + fin*0.45 + rep*0.20;
				ans[i] = sum;
				if(i+1 == K)
					temp = sum;
			}
			
			Arrays.sort(ans,Collections.reverseOrder());
			int index = 0;
			for(int i=0;i<ans.length;i++){
				if(ans[i] == temp){
					index = i;
				}
			}
			
			index = index/(N/10);
			System.out.println("#"+test_case+" "+grade[index]);

		}
	}
}