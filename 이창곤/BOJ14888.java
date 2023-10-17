import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long min, max;
	static int[] opers, comb;
	static long[] nums;
	static final int PLUS = 0;
	static final int MINUS = 1;
	static final int MULTI = 2;
	static final int DIVISION = 3;
	
	static long calc(long a, long b, int oper) {	//연산자에 해당하는 수를 미리 final로 선언하고 비교해서 계산함
		switch(oper) {
		case(PLUS):
			return a + b;
		case(MINUS):
			return a - b;
		case(MULTI):
			return a * b;
		case(DIVISION):
			return a / b;
		}
		return -1;
	}
	
	static void dfs(int depth) {	//재귀로 모든 경우의 수를 구함
		if(depth == N-1) {
			long tmp = nums[0];
			for(int i=0; i<N-1; i++) {
				tmp = calc(tmp, nums[i+1], comb[i]);
			}
			
			max = Math.max(max, tmp);
			min = Math.min(min, tmp);
			
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(opers[i] != 0) {
				comb[depth] = i;
				opers[i]--;
				dfs(depth + 1);
				opers[i]++;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		nums = new long[N];
		opers = new int[4];
		comb = new int[N-1];
		min = Long.MAX_VALUE;
		max = Long.MIN_VALUE;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) nums[i] = Long.parseLong(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) opers[i] = Integer.parseInt(st.nextToken());
		
		dfs(0);
		
		System.out.println(max);
		System.out.println(min);
	}
}
