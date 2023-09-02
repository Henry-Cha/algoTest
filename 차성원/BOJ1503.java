package algoTest.차성원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1503 {
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        boolean[] s=new boolean[1002];
        for(int i=0;i<m;i++){
            s[Integer.parseInt(st.nextToken())]=true;
        }
        int answer = Integer.MAX_VALUE;
        for(int i=1;i<1002;i++){
            if(s[i]) continue;
            for(int j=i;j<1002;j++) {
                if (s[j]) continue;
                for (int k = j; k < 1002; k++) {
                    if (s[k]) continue;
                    int tmp = Math.abs(n-i*j*k);
                    answer = answer<tmp?answer:tmp;
                }
            }
        }
        System.out.println(answer);
    }
}
