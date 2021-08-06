package swet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution_D3_1208_김재욱 {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res/input_1208.txt"));
		Scanner sc = new Scanner(System.in);
		
		for (int t = 1; t <= 10; t++) {
			int demp = sc.nextInt();
			int[] arr = new int[100];
			for (int i = 0; i < 100; i++)
				arr[i] = sc.nextInt();
			int min;
			int max;
			for (int i = 0; i < demp; i++) {
				max = max(arr);
	            min = min(arr);
	            arr[max]--;
	            arr[min]++;
				if (arr[max] - arr[min] == 0 || arr[max] - arr[min] == 1)
					break;
			}
			max = max(arr);
            min = min(arr);
            System.out.println("#"+ t + " " + (arr[max] - arr[min]));
		}
	}

	public static int max(int n[]) {
        int max = n[0];
        int index = 0;

        for (int i = 1; i < n.length; i++)
          if (n[i] > max) {
              max = n[i];
              index = i;
          }
        return index;
    }

    public static int min(int n[]) {
        int min = n[0];
        int index = 0;

        for (int i = 1; i < n.length; i++)
          if (n[i] < min) {
              min = n[i];
              index = i;
          }
        return index;
    }
}