package gitCodding;

import java.io.*;
import java.util.*;

public class line {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int result = 0;
		int ln[][] = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			ln[i][0] = a;
			ln[i][1] = b;
		}
		
        Arrays.sort(ln, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) {
                	return o1[1] - o2[1];
                }
                else {
                	return o1[0] - o2[0];
                }
            }
        }); 
		
		int S = ln[0][0];
		int F = ln[0][1];
		result += (F - S);
        
		for(int i = 1; i < N; i++) {
			
			int a = ln[i][0];
			int b = ln[i][1];
			
			if(a > F) {
				result += (b - a);
				F = b;		
			}
			else {
				if(b > F) {
					result += (b - F);
					F = b;
				}
			}
		}
		System.out.println(result);
	}

}
