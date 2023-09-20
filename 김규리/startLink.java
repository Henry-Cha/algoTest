package gitCodding;

import java.io.*;
import java.util.*;

public class startLink {
	
	static int N;
	static int stln[][];
	static boolean visited[];
	static int min;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		stln = new int[N][N];
		visited = new boolean[N];
		min = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				stln[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0);
		
		System.out.println(min);
	}
	
	public static void dfs(int s, int d) {
		if(d == N/2) {
			int a[] = new int[N/2];
			int b[] = new int[N/2];
			
			int cnt_a = 0;
			int cnt_b = 0;
			
			for(int i = 0; i < N; i++) {
				if(visited[i]) {
					a[cnt_a] = i;
					cnt_a += 1;
				}
				else {
					b[cnt_b] = i;
					cnt_b += 1;
				}
			}
			
			cal(a, b);
			return;
		}
		
		for(int i = s; i < N; i ++) {
			visited[i] = true;
			dfs(i + 1, d + 1);
			visited[i] = false;
		}
	}
	
	public static void cal(int a[], int b[]) {
		int sum = 0;
		int sum2 = 0;
		
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a.length; j++) {
				sum += stln[a[i]][a[j]];
				sum2 += stln[b[i]][b[j]];
			}
		}
		
		min = Math.min(min, Math.abs(sum - sum2));
	}
}
