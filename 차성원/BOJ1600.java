import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int k, w, h;
	static int[][] map;
	static boolean[][][] visit;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[] jx = { 2, 1, -1, -2, -2, -1, 1, 2 };
	static int[] jy = { 1, 2, 2, 1, -1, -2, -2, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		String[] wh = br.readLine().split(" ");
		w = Integer.parseInt(wh[0]);
		h = Integer.parseInt(wh[1]);
		map = new int[h][w];
		visit = new boolean[h][w][k + 1];
		StringTokenizer st;
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		map[0][0] = 0;
		run();
		System.out.println(-1);
	}

	public static void run() {
		Queue<Move> q = new LinkedList<>();
		q.add(new Move(0, 0, 0, 0));
		while (!q.isEmpty()) {
			Move now = q.poll();
			if (now.x == h - 1 && now.y == w - 1) {
				System.out.println(now.cnt);
				System.exit(0);
			}
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (nx < 0 || nx >= h || ny < 0 || ny >= w)
					continue;
				if (map[nx][ny] == 1)
					continue;
				if (visit[nx][ny][now.jump])
					continue;
				q.add(new Move(now.cnt + 1, now.jump, nx, ny));
				visit[nx][ny][now.jump] = true;

			}
			if (now.jump >= k)
				continue;
			for (int i = 0; i < 8; i++) {
				int nx = now.x + jx[i];
				int ny = now.y + jy[i];
				if (nx < 0 || nx >= h || ny < 0 || ny >= w)
					continue;
				if (map[nx][ny] == 1)
					continue;
				if (visit[nx][ny][now.jump+1])
					continue;
				q.add(new Move(now.cnt + 1, now.jump + 1, nx, ny));
				visit[nx][ny][now.jump+1] = true;

			}
		}
	}

	static class Move {
		int cnt;
		int jump;
		int x;
		int y;

		public Move(int cnt, int jump, int x, int y) {
			this.cnt = cnt;
			this.jump = jump;
			this.x = x;
			this.y = y;
		}
	}
}
