package gitCodding;

import java.io.*;
import java.util.*;

public class noClone {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int arr[] = new int[100001];
        int hong[] = new int[N];
        int max = 0;
        int cnt = 0;
        
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        
        for(int i = 0; i < N; i++) {
            hong[i] = Integer.parseInt(st2.nextToken());
        }
        
        int s = 0;
        int f = 0;
        
        // 2중 포문을 사용하면 시간초과가 나기때문에
        // 두 포인터를 무조건 써야함
        // 시작지점과 끝지점 두 곳에 포인터를 두어 하나씩 증가시켜가면서 
        // 값을 구하는 알고리즘
        
        while(f < hong.length) {
        	while(f < hong.length && arr[hong[f]] + 1 <= K) {
        		arr[hong[f]] += 1;
        		f += 1;
        	}
        	
        	cnt = f - s;
        	max = Math.max(max, cnt);
        	
        	// 시작값을 뒤로 옮기므로 시작점에 해당하는 값을
        	// 카운트 했던 부분을 --시킴
        	arr[hong[s]] -= 1; 
        	
        	s += 1;
        }
        
        System.out.println(max);
    }

}