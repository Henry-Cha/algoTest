package gitCodding;

import java.io.*;
import java.util.*;

public class lap {
	
	static int N;
	static int M;
	static int lapZone[][];
	static int dx[] = {0, 0, -1, 1};
	static int dy[] = {1, -1, 0, 0};
	static int result = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lapZone = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < M; j++) {
				lapZone[i][j] = Integer.parseInt(st2.nextToken());
			}
		}
		
		dfs(0);
		
		System.out.println(result);
	}
	
	public static void dfs(int cnt) {
		if(cnt == 3) {
			bfs();
			return;
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(lapZone[i][j] == 0) {
					lapZone[i][j] = 1;
					dfs(cnt + 1);
					lapZone[i][j] = 0;
				}
			}
		}
	}
	
	static class Node{
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void bfs() {
		Queue<Node> q = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(lapZone[i][j] == 2) {
					q.offer(new Node(i, j));
				}
			}
		}
		
		int lapZone2[][] = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				lapZone2[i][j] = lapZone[i][j];
			}
		}
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			
			int g_x = node.x;
			int g_y = node.y;
			
			for(int i = 0; i < 4; i++) {
				int nx = g_x + dx[i];
				int ny = g_y + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}
				
				if(lapZone2[nx][ny] == 0) {
					q.offer(new Node(nx, ny));
					lapZone2[nx][ny] = 2;
				}
			}
		}
		
		safeZone(lapZone2);
	}
	
	public static void safeZone(int lap[][]) {
		int safe = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(lap[i][j] == 0) {
					safe += 1;
				}
			}
		}
		
		if(result < safe) {
			result = safe;
		}
	}

}
