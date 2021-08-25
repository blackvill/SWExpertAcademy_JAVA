package swet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_D4_1251_Prim_PrimaryQueue {
	static int test;
	static int n;
	static int[] parents;
	static Island[] island;
	static int[] x;
	static int[] y;
	static double e;
	
	static class Island implements Comparable<Island> {
		int no;
		double cost;
		
		public Island(int no, double cost) {
			this.no = no;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Island o) {
			if (this.cost > o.cost)
				return 1;
			else if (this.cost < o.cost)
				return -1;
			return 0;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		test = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		for (int t = 1; t <= test; t++) {
			n = Integer.parseInt(br.readLine()); // 섬 개수
			island = new Island[n];
			double[][] adjMatrix = new double[n][n];
			boolean[] visited = new boolean[n];
			double[] minEdge = new double[n]; // 정점까지 가는 최소 비용을 저장하는 배열
			x = new int[n];
			y = new int[n];
			
			StringTokenizer st = new StringTokenizer(br.readLine());// 각 섬간의 연결
			for (int i = 0; i < n; i++) { // x좌표 입력
				x[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) { // y좌표 입력
				y[i] = Integer.parseInt(st.nextToken());
			}
			
			e = Double.parseDouble(br.readLine()); // 환경 부담 세율 실수
			
			for (int i = 0; i < n; i++) { // 마지막 섬은 이전 섬들을 계산하면서 계산이 됨
				for (int j = 0 ; j < n; j++) { // 자기자신을 제외한 섬을 구해야함
					double cost = e * Math.pow(Math.sqrt(Math.pow(x[j] - x[i], 2) + Math.pow(y[j] - y[i], 2)), 2);
					// 비용 = e * 해저 터널의 길이^2
					adjMatrix[i][j] = adjMatrix[j][i] = cost;
				}
				minEdge[i] = Double.MAX_VALUE;
			}
			
			int start = 0; // 입력받으면 됨
			double result = 0.0; // 최소신장트리의 비용
			minEdge[0] = 0; // 임의의 시작점 0의 간선비용을 0으로 세팅
			int nodeCount = 0; // 노드가 연결될 때마다 카운트 => v-1개가 되면 다 연결됨
			PriorityQueue<Island> queue = new PriorityQueue<Island>();
			
			queue.offer(new Island(start, 0)); // 시작 정점을 queue에 담는다.
			minEdge[start] = 0.0; // 시작 정점은 비용이 없으므로 0값 세팅
			
			while (!queue.isEmpty()) {
				Island min = queue.poll(); // PriorityQueue이므로 앞으로 가봐야 할 정점 중 최소비용의 정점이 추출됨
				if (visited[min.no]) continue; // 최소 비용의 정점이 방문했던 정점이라면 pass 다음 정점을 처리

				// 최소 비용의 정점이 방문하지 않은 정점이라면 선택
				visited[min.no] = true; // 신장트리에 포함시킴
				result += min.cost; // 간선비용 누적
				
				if (++nodeCount == n) break; // 모든 정점이 다 연결됐다면 중단
				
				// 2. 선택된 정점 기준으로 신장트리에 연결되지 않은 타 정점과의 간선 비용을 최소로 업데이트
				for (int j = 0; j < n; j++) {
					if (!visited[j] && adjMatrix[min.no][j] != 0 && minEdge[j] > adjMatrix[min.no][j]) {
						// 신장트리에 포함이 되어있지 않고, 인접 여부 확인, 간선의 최소 비용을 비교 (기존이 크다면 새로 들어오는게 작음)
						minEdge[j] = adjMatrix[min.no][j];
						queue.offer(new Island(j, adjMatrix[min.no][j]));
					}
				}
			}
			System.out.printf("#%d %.0f\n", t, result);
		}
	}
}
