import java.io.*;
import java.util.*;

class Main {
	static int N, X;
	static boolean[] con;
	
	static boolean checkLeft(int[] arr, int idx) {
		if(idx + 1 - X < 0) {
			return false;
		}
		
		int a = arr[idx];
		for(int i=idx; i>=idx+1-X; i--) {
			if(arr[i] == a && !con[i]) con[i] = true;
			else return false;
		}
		return true;
	}
	
	static boolean checkRight(int[] arr, int idx) {
		if(idx + X >= arr.length) return false;
		
		int a = arr[idx+1];
		for(int i=idx+1; i<=idx+X; i++) {
			if(arr[i] == a) con[i] = true;
			else return false;
		}
		
		return true;
	}
	
	static boolean lineCheck(int[] arr) {
		con = new boolean[N];
		for(int i=0; i<arr.length-1; i++) {
			if(Math.abs(arr[i] - arr[i+1]) >= 2) {
				return false;
			}
			if(arr[i] > arr[i+1]) {
				if(!checkRight(arr, i)) return false; 
			}
			else if(arr[i] < arr[i+1]) {
				if(!checkLeft(arr, i)) return false; 
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		int cnt = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}

		for(int i=0; i<N; i++) {
			if(lineCheck(Arrays.copyOf(map[i], N))) cnt++;

			int[] tmpArr = new int[N];
			for(int j=0; j<N; j++) {
				tmpArr[j] = map[j][i];
			}
			if(lineCheck(Arrays.copyOf(tmpArr, N))) cnt++;
			
		}
		System.out.println(cnt);
	}
}
