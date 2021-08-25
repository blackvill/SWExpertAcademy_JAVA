package swet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution_D4_1251_Kruskal {
	static int test;
	static int n;
	static int[] parents;
	static Island[] island;
	static double e;
	
	static class Island {
		int r, c;
		double cost;
		
		public Island(int r, int c, double cost) {
			this.r = r;
			this.c = c;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		test = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		for (int t = 1; t <= test; t++) {
			n = Integer.parseInt(br.readLine()); // 섬 개수
			island = new Island[n];
			parents = new int[n];
			
			StringTokenizer st = new StringTokenizer(br.readLine());// 각 섬간의 연결
			for (int i = 0; i < n; i++) { // x좌표 입력
				island[i] = new Island(Integer.parseInt(st.nextToken()), 0, 0);
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) { // y좌표 입력
				island[i].c = Integer.parseInt(st.nextToken());
			}
			e = Double.parseDouble(br.readLine());
			ArrayList<Island> bill = new ArrayList<>();
			
			for (int i = 0; i < n - 1; i++) { // 마지막 섬은 이전 섬들을 계산하면서 계산이 됨
				for (int j = i + 1 ; j < n; j++) { // 자기자신을 제외한 섬을 구해야함
					double cost = e * Math.pow(Math.sqrt(Math.pow(island[j].r - island[i].r, 2) + Math.pow(island[j].c - island[i].c, 2)),2);
					// 비용 = e * 해저 터널의 길이^2
					bill.add(new Island(i, j, cost));
				}
			}
			
			// 비용을 기준으로 정렬(오름차순)
			Collections.sort(bill, new Comparator<Island>() {
				@Override
				public int compare(Island o1, Island o2) {
					if (o1.cost > o2.cost)
						return 1;
					else if (o2.cost > o1.cost)
						return -1;
					return 0;
				}
			});
			
			makeSet(); // 자기자신을 연결한 상태
			double result = 0.0;
			for (int i = 0; i < bill.size(); i++) {
				if (find(bill.get(i).r) != find(bill.get(i).c)) { // 부모가 다르다면(연결되어 있지 않다면)
					union(bill.get(i).r, bill.get(i).c); // 유니온(결합) 시켜준다.
					result += bill.get(i).cost; // 비용 증가
				}
			}
			System.out.printf("#%d %.0f\n", t, result);
		}
	}
	
	public static void makeSet() {
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}
	}
	
	public static int find(int a) {
		if (a == parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
}
