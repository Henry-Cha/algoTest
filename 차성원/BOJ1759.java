package algoTest.차성원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1759 {
    static int L;
    static int C;
    static boolean[] visit;
    static String[] alpha;
    static  int moCnt=0;
    static  int jaCnt=0;
    static StringBuilder sb;
    static String mo = "aeiou";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lc = br.readLine().split(" ");
        L = Integer.parseInt(lc[0]);
        C = Integer.parseInt(lc[1]);
        alpha = br.readLine().split(" ");
        visit=new boolean[C];
        sb=new StringBuilder();
        Arrays.sort(alpha);
        find(0,0);
        System.out.println(sb);
    }
    public static void find(int dep,int cnt){
        if(cnt==L){
            if(moCnt>=1 && jaCnt>=2){
                for(int i=0;i<C;i++){
                    if(visit[i]){
                        sb.append(alpha[i]);
                    }
                }
                sb.append("\n");
            }
            return;
        }
        for(int i=dep;i<C;i++){
            if(mo.contains(alpha[i])){
                moCnt++;
            }else{
                jaCnt++;
            }
            visit[i]=true;
            find(i+1,cnt+1);
            if(mo.contains(alpha[i])){
                moCnt--;
            }else{
                jaCnt--;
            }
            visit[i]=false;
        }
    }
}
