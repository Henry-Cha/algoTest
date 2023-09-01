import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17070 {
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];

		int[][] dp1 = new int[n][n];
		int[][] dp2 = new int[n][n];
		int[][] dp3 = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp1[0][1] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (dp1[i][j] != 0) {
					if (j + 1 < n) {
						if (check(i, j, 1)) {
							dp1[i][j + 1] += dp1[i][j];
						}
					}
					if (i + 1 < n && j + 1 < n) {
						if (check(i, j, 2)) {
							dp2[i + 1][j + 1] += dp1[i][j];
						}
					}
				}
				if (dp2[i][j] != 0) {
					if (j + 1 < n) {
						if (check(i, j, 1)) {
							dp1[i][j + 1] += dp2[i][j];
						}
					}
					if (i + 1 < n) {
						if (check(i, j, 3)) {
							dp3[i + 1][j] += dp2[i][j];
						}
					}
					if (i + 1 < n && j + 1 < n) {
						if (check(i, j, 2)) {
							dp2[i + 1][j + 1] += dp2[i][j];
						}
					}
				}
				if (dp3[i][j] != 0) {
					if (i + 1 < n) {
						if (check(i, j, 3)) {
							dp3[i + 1][j] += dp3[i][j];
						}
					}
					if (i + 1 < n && j + 1 < n) {
						if (check(i, j, 2)) {
							dp2[i + 1][j + 1] += dp3[i][j];
						}
					}
				}
			}
		}
		System.out.println(dp1[n - 1][n - 1] + dp2[n - 1][n - 1] + dp3[n - 1][n - 1]);
	}

	public static boolean check(int i, int j, int dir) {
		if (dir == 1) {
			if (map[i][j + 1] == 1)
				return false;
		} else if (dir == 2) {
			if (map[i][j + 1] == 1 || map[i + 1][j] == 1 || map[i + 1][j + 1] == 1)
				return false;
		} else if (dir == 3) {
			if (map[i + 1][j] == 1)
				return false;
		}
		return true;
	}
}
