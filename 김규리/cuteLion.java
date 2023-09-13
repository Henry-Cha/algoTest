package gitCodding;


import java.io.*;
import java.util.*;

public class cuteLion {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int dolls[] = new int[N];
        
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        
        for(int i = 0; i < N; i++) {
            dolls[i] = Integer.parseInt(st2.nextToken());
        }
        
        // 라이언 = 1
        // 어피치 = 2
        
        int s = 0;
        int f = 0;
        int cnt = 0;
        int len = 0;
        int min = Integer.MAX_VALUE;

        while(s <= f && f <= dolls.length) {
            if(cnt < K) {
                if(f == dolls.length) {
                    break;
                }
                
                if(dolls[f] == 1) {
                    cnt += 1;
                }
                
                f += 1;
            }
            else {
                if(cnt == K) {
                    min = Math.min(min, f - s);
                }
                
                if(dolls[s] == 1) {
                    cnt -= 1;
                }
        
                s += 1;
            }

        }
        
        if(min == Integer.MAX_VALUE) {
            System.out.println(-1);
        }
        else {
            System.out.println(min);
        }
      
    }

}