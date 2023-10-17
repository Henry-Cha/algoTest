import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int V, E, K; //정점의 개수, 간선의 개수, 시작점
	static int[] visit;
	static ArrayList<Edge>[] list;
	
	static class Edge implements Comparable<Edge>{
		int end;
		int val;
		public Edge(int end, int val) {
			this.end = end;
			this.val = val;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.val - o.val;
		}
		
		
	}
	
	static void bfs() {
		PriorityQueue<Edge> q = new PriorityQueue<>();
		
		visit[K] = 0;
		q.add(new Edge(K, 0));
		
		while(!q.isEmpty()) {
			Edge e = q.poll();

			for(Edge ne : list[e.end]) {
				if(visit[ne.end] > e.val + ne.val) {
					visit[ne.end] = e.val + ne.val;
					q.add(new Edge(ne.end, visit[ne.end]));
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine()) - 1;

		list = new ArrayList[V];
		for(int i=0;i<V;i++) 
			list[i] = new ArrayList<Edge>();
		visit = new int[V];
		Arrays.fill(visit, Integer.MAX_VALUE);
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
//			System.out.println(a - 1);
			list[a - 1].add(new Edge(b - 1, val));
		}
		
		
		
//		for(int i=0; i<list.size(); i++) {
//			System.out.println(list.get(i).val);
//		}
		
		bfs();
		
		for(int i=0; i<V; i++) {
			if(visit[i] == Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(visit[i]);
		}
	}
}
