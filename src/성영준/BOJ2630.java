package 성영준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class BOJ2630 {
	static int w;
	static int b;
	static String[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		w = 0;
		b = 0;
		map = new String[n][n];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().split(" ");
		}
		divide(0, 0, n);

		System.out.println(w);
		System.out.println(b);
	}

	public static void divide(int y, int x, int len) {
		String ans = zoneSearch(y, x, len);
		if (ans != null)
			if (ans.equals("0")) {
				w += 1;
				return;
			} else if (ans.equals("1")) {
				b += 1;
				return;
			}
		if (len == 0)
			return;

		divide(y, x, len / 2);
		divide(y, x + len / 2, len / 2);
		divide(y + len / 2, x, len / 2);
		divide(y + len / 2, x + len / 2, len / 2);
	}

	public static String zoneSearch(int y, int x, int len) {
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (!map[y][x].equals(map[y + i][x + j]))
					return null;
			}
		}
		return map[y][x];
	}
}
