package algoTest.차성원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ10026 {
	static int n;
	static char[][] board;
	static boolean[][] visit;
	static boolean[][] visit2;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int ans1 = 0;
	static int ans2 = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		board = new char[n][];
		for (int i = 0; i < n; i++) {
			board[i] = br.readLine().toCharArray();
		}
		visit = new boolean[n][n];
		visit2 = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visit2[i][j]) {
					bfs(i, j,2);
					ans2++;
				}
				if (!visit[i][j]) {
					bfs(i, j,1);
					ans1++;
				}
			}
		}
		System.out.print(ans1+" "+ans2);
	}

	public static void bfs(int i, int j,int type) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] { i, j });
		if(type==1) {
			visit[i][j]=true;
		}else {
			visit2[i][j]=true;
		}
		char c = board[i][j];
		while (!q.isEmpty()) {
			int[] now = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				if (nx < 0 || nx >= n || ny < 0 || ny >= n)
					continue;
				if(type==1) {
					if (visit[nx][ny])
						continue;
					if(board[nx][ny]==c) {
						visit[nx][ny]=true;
						q.add(new int[] { nx, ny });
					}
				}
				else {
					if (visit2[nx][ny])
						continue;
					if(board[nx][ny]==c || (c=='R' && board[nx][ny]=='G') || (c=='G' && board[nx][ny]=='R')) {
						visit2[nx][ny]=true;
						q.add(new int[] { nx, ny });
					}
				}
			}
		}
	}
}
