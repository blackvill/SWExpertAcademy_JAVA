package swet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_3124 {
	static int T; // 테케 수
	static int V; // 정점 수
	static int E; // 간선 수
	static int[] parents;
	static Node[] edgelist;
	
	public static class Node implements Comparable<Node>{
		int s;
		int e;
		int w;
		
		public Node(int v, int e, int w) {
			this.s = v;
			this.e = e;
			this.w = w;
		}
		
		@Override
		public int compareTo(Node o) {
			return w - o.w;
		}
	}
	
	private static void makeSet() {
		parents = new int[V + 1];
		// 모든 원소를 자신을 대표자로 만듦
		for (int i = 0; i <= V; i++) {
			parents[i] = i;
		}
	}
	
	private static int find(int a) {
		if (a == parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			edgelist = new Node[E];
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int edge = Integer.parseInt(st.nextToken());
				
				edgelist[i] = new Node(start, end, edge);
			}
			
			makeSet();
			Arrays.sort(edgelist); // 가중치로 오름차순 정렬
			
			// 간선 하나씩 시도하며 트리를 만들어 감
			int cnt = 0;
			long result = 0;
			for (Node node : edgelist) {
				if (union(node.s, node.e)) {
					result += node.w;
					if (++cnt == V - 1) break; // 신장 트리 완성
				}
			}
			System.out.println("#" + t + " " + result);
		}
	}
}