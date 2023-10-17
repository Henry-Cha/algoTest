import java.io.*;
import java.util.*;

class Main {
	static int[] dx = {-1, 0, 1, 0}; //상 우 하 좌
	static int[] dy = {0, 1, 0, -1};
	
	static int N, M, emptyCnt, cameraCnt, result;
	static int[][] map;
	static List<Camera> list;
	static int[] comb;
	
	static class Camera{
		int x;
		int y;
		int type;
		public Camera(int x, int y, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}
	
	static void search(int dir, int x, int y, int[][] tmpMap) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		if(nx >= 0 && nx < N && ny >= 0 && ny < M && tmpMap[nx][ny] != 6) {
			if(tmpMap[nx][ny] == 0) {
				tmpMap[nx][ny] = -1;
				cameraCnt++;
			}
			search(dir, nx, ny, tmpMap);
		}
	}
	
	static void permutation(int depth) {
		if(depth == comb.length) {
//			System.out.println(Arrays.toString(comb));
			cameraCnt = 0;
			int[][] tmpMap = new int[N][M];
			for(int i=0; i<N; i++) tmpMap[i] = map[i].clone();
			
			for(int i=0; i<list.size(); i++) {
				Camera c = list.get(i);
				if(c.type == 1) {
					search(comb[i], c.x, c.y, tmpMap);
				}
				if(c.type == 2) {
					search(comb[i], c.x, c.y, tmpMap);
					search(comb[i] + 2, c.x, c.y, tmpMap);
				}
				if(c.type == 3) {
					int j = comb[i] + 1;
					if(j >= 4) j = 0;
					search(comb[i], c.x, c.y, tmpMap);
					search(j, c.x, c.y, tmpMap);
				}
				if(c.type == 4) {
					int j = comb[i] + 1;
					int k = comb[i] - 1;
					if(j >= 4) j = 0;
					if(k <= -1) k = 3;
					search(comb[i], c.x, c.y, tmpMap);
					search(j, c.x, c.y, tmpMap);
					search(k, c.x, c.y, tmpMap);
				}
				if(c.type == 5) {
					search(0, c.x, c.y, tmpMap);
					search(1, c.x, c.y, tmpMap);
					search(2, c.x, c.y, tmpMap);
					search(3, c.x, c.y, tmpMap);
				}
			}
			
			result = Math.min(result, emptyCnt - cameraCnt);
			
			return;
		}
		
		int dir = 4;
		int type = list.get(depth).type;
		if(type == 5) dir = 1;
		else if(type == 2) dir = 2;
		
		
		for(int i=0; i<dir; i++) {
			comb[depth] = i;
			permutation(depth + 1);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		list = new ArrayList<>();
		emptyCnt = 0;
		result = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) emptyCnt++;
				else if(map[i][j] != 6) {
					list.add(new Camera(i, j, map[i][j]));
				}
			}
		}
		
		comb = new int[list.size()];
		permutation(0);
		
		System.out.println(result);
		
		
	}
}
