package algoTest.차성원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2042 {
	static long[] arr;
	static Tree tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		arr = new long[n + 1];
		for (int i = 1; i < n + 1; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		tree = new Tree(n);
		tree.make(1, 1, n);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m + k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if (a == 1) {
				tree.update(1, b, 1, n, c);
				arr[b] =c;
			} else {
				sb.append(tree.getSum(1, 1, n, b, c)).append('\n');
			}
		}
		System.out.println(sb);
	}

	static class Tree {
		long[] trees;
		int size;

		public Tree(int size) {
			int h = (int) Math.ceil(Math.log(size) / Math.log(2));
			this.size = (int) Math.pow(2, h + 1);
			trees = new long[this.size];
		}

		public long make(int idx, int left, int right) {
			if (left == right) {
				trees[idx] = arr[left];
			} else {
				trees[idx] = make(idx * 2, left, (left + right) / 2) + make(idx * 2 + 1, (left + right) / 2 + 1, right);
			}
			return trees[idx];
		}

		public void update(int idx, int b, int left, int right, long c) {
			if (b < left || b > right)
				return;
			trees[idx] += c - arr[b];
			
			if (left != right) {
				update(idx * 2, b, left, (left + right) / 2, c);
				update(idx * 2 + 1, b, (left + right) / 2 + 1, right, c);
			}
		}

		public long getSum(int idx, int left, int right, int b, long c) {
			if (right < b || left > c)
				return 0;
			if (b <= left && c >= right) {
				return trees[idx];
			}
			return getSum(idx * 2, left, (left + right) / 2, b, c)
					+ getSum(idx * 2 + 1, (left + right) / 2 + 1, right, b, c);
		}
	}
}
