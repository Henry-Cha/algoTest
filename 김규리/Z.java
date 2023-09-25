package gitCodding;

import java.io.*;
import java.util.*;

public class Z {
    
    static int len;
    static int cnt;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        len = (int) Math.pow(2, N);
        
        numZ(len, r, c);
        
        System.out.println(cnt);
    }
    
    public static void numZ(int l, int r, int c) {
        if(l == 1) {
            return;
        }
        
        // 2사분면
        if(r < (l / 2) && c < (l / 2)) {
            numZ(l/2, r, c);
        }
        // 1사분면
        else if(r < (l / 2) && c >= (l / 2)) {
            cnt += l * l / 4;
            numZ(l/2, r, c - l/2);
        }
        // 3사분면
        else if(r >= (l / 2) && c < (l / 2)) {
            cnt += (l * l / 4) * 2;
            numZ(l/2, r - l/2, c);
        }
        // 4사분면
        else if(r >= (l / 2) && c >= (l / 2)) {
            cnt += (l * l / 4) * 3;
            numZ(l/2, r - l/2, c - l/2);
        }
    }

}