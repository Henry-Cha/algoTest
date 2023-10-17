import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[] visit, parent;
	
	static class Point implements Comparable<Point>{
		int p, val;

		public Point(int p, int val) {
			this.p = p;
			this.val = val;
		}

		@Override
		public int compareTo(Point o) {
			return this.val - o.val;
		}
	}
	
	static void bfs() {
		PriorityQueue<Point> q = new PriorityQueue<>();
		
		visit[N] = 0;
		q.add(new Point(N, 0));
		
		while(!q.isEmpty()) {
			Point point = q.poll();
			
			if(point.p == K) continue; 
			if(point.p > 0) {			
				if(visit[point.p - 1] > point.val + 1) {
					visit[point.p - 1] = point.val + 1;
					parent[point.p - 1] = point.p;
					q.add(new Point(point.p - 1, point.val + 1));
				}
			}

			if(point.p < K) {
				if(visit[point.p + 1] > point.val + 1) {
					visit[point.p + 1] = point.val + 1;
					parent[point.p + 1] = point.p;
					q.add(new Point(point.p + 1, point.val + 1));
				}
				if(visit[point.p * 2] > point.val + 1) {
					visit[point.p * 2] = point.val + 1;
					parent[point.p * 2] = point.p;
					q.add(new Point(point.p * 2, point.val + 1));
				}
			}
			
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visit = new int[(N + K + 1) * 2];
		parent = new int[(N + K + 1) * 2];
		Arrays.fill(visit, Integer.MAX_VALUE);
		
		bfs();
		StringBuilder sb = new StringBuilder();
		sb.append(visit[K]).append("\n");
		
		//parent로 루트를 표시하고 도착지 부터 거꾸로 스택에 넣은 후 출력
		int i = K;
		Stack<Integer> stack = new Stack<>();
		stack.push(K);
		while(i != N) {
			stack.push(parent[i]);
			i = parent[i];
		}
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		System.out.println(sb);
	}
}