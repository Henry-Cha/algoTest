import java.io.*;
import java.util.*;

public class Main {
	static int N, M, result;
	static int[][] map;
	static List<Node> homeList, storeList;
	static int[] comb;
	
	static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}	
	}
	
	static int check() {
		int totalDist = 0;
		
		for(int i=0; i<homeList.size(); i++) {
			int dist = Integer.MAX_VALUE;
			for(int j=0; j<storeList.size(); j++) {
				if(comb[j] == 1) {
					int nowDist = Math.abs(homeList.get(i).x - storeList.get(j).x) + Math.abs(homeList.get(i).y - storeList.get(j).y); 
					dist = Math.min(dist, nowDist);
				}
			}

			totalDist += dist;
		}
		
		return totalDist;
	}
	
	static void recur(int depth, int cnt) {
		if(depth == comb.length) {
			if(cnt == M) 
				result = Math.min(result, check());

			return;
		}
		
		comb[depth] = 0;
		recur(depth + 1, cnt);
		comb[depth] = 1;
		recur(depth + 1, cnt + 1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		result = Integer.MAX_VALUE;
		homeList = new ArrayList<>();
		storeList = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) homeList.add(new Node(i, j));
				else if(map[i][j] == 2) storeList.add(new Node(i, j));
			}
		}
		
		comb = new int[storeList.size()];
		
		recur(0, 0);
		System.out.println(result);
	}
}
