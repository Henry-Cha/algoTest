package gitCodding;

import java.io.*;
import java.util.*;

public class MN2 {

    static int m_arr[];
    static boolean visited[];
    static int N;
    static int M;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        m_arr = new int[M];
        visited = new boolean[N];
        
        dfs(1, 0);
    }
    
    public static void dfs(int n, int d) {
        if(d == M) {
            for(int i : m_arr) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }
        
        for(int i = n; i <= N; i++) {
            m_arr[d] = i;
            dfs(i + 1, d + 1);    
        }
    }
}
