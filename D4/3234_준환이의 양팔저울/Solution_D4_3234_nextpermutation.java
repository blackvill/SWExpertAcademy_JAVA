package swet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_3234_nextpermutation {
	static int n;
	static int[] weight;
	static int count;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testcase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testcase; t++) {
			n = Integer.parseInt(br.readLine());
			count = 0;
			weight = new int[n];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(weight);
			do {
				check(0,0,0);
			}while (np(weight));
			
			System.out.printf("#%d %d%n", t, count);
		}
	}
	
	// 다음 큰 순열이 있으면 true, 없으면 false
	private static boolean np(int[] numbers) {
		int n = numbers.length - 1;
		
		// step 1. 꼭대기(i)를 찾는다. 꼭대기를 통해 교환위치(i - 1) 찾기
		int i = n;
		while (i > 0 && numbers[i - 1] >= numbers[i]) --i;
		
		if (i == 0) return false;
		
		// step 2. i - 1 위치값과 교환할 큰 값을 찾기
		int j = n;
		while (numbers[i - 1] >= numbers[j]) --j; // 맨 뒤에서부터 한칸씩 앞으로 오면서 비교
		
		// step 3. i - 1 위치값과 j 위치값 교환
		swap(numbers, i - 1, j);
		
		// step 4. 꼭대기부터 맨 뒤까지 내림차순 형태의 순열을 오름차순으로 처리
		int k = n;
		while (i < k) {
			swap(numbers, i++, k--);
		}
		return true;
	}
	
	private static void swap(int[] numbers, int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
	
	public static void check(int index, int l, int r) { // 여기서 확인
        if(index == n) { //index == n이라는 소리는 모든 추를 사용했다는 것
        	count++; // 카운트 증가
            return;
        }
        check(index + 1, l + weight[index], r); // 왼쪽에 추를 올리는 방법
        if (r + weight[index] <= l) // 오른쪽에 추를 추가할 때 왼쪽보다 작으면
        	check(index + 1, l, r + weight[index]); // 오른쪽에 추를 올리는 방법
    }
}
