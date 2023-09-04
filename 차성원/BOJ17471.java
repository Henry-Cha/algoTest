package algoTest.차성원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17471 {
    static int n;
    static int[] nums;
    static boolean[][] graph;
    static int bit;
    static int answer = Integer.MAX_VALUE;
    static int visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        bit=0;
        nums = new int[n];
        graph = new boolean[n][n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(st.nextToken());
            for(int j=0;j<tmp;j++){
                int v = Integer.parseInt(st.nextToken())-1;
                graph[i][v] = true;
            }
        }
        combi();
        System.out.println(answer==Integer.MAX_VALUE?-1:answer);
    }
    public static void combi(){
        for(int bit=1;bit<(1<<n);bit++){
            int tmp = calc(bit);
            if(tmp<answer){
                if(check(bit)){
                    if(tmp==0){
                        System.out.println(0);
                        System.exit(0);
                    }
                    answer=tmp;
                }
            }
        }
    }
    public static boolean check(int bit){
        visit = 0;
        boolean flag1=false;
        boolean flag2=false;
        for(int i=0;i<n;i++){
            if((visit&(1<<i))==(1<<i)) continue;

            if((bit&(1<<i))==(1<<i)){//1팀
                if(flag1) continue;
                flag1=true;
                dfs(bit,i,1);
            }else{//0팀
                if(flag2) continue;
                flag2=true;
                dfs(bit,i,0);
            }
        }
        if(visit==(1<<n)-1)
            return true;
        else return false;
    }
    public static void dfs(int bit,int i,int team){
        visit|=(1<<i);
        for(int j=0;j<n;j++){
            if(!graph[i][j]) continue;
            if((visit&(1<<j))==(1<<j)) continue;
            if((bit&(1<<j))==(team<<j)){
                dfs(bit,j,team);
            }
        }
    }
    public static int calc(int bit){
        int a=0;
        int b=0;
        for(int i=0;i<n;i++){
            if((bit&(1<<i))==(1<<i)) a+=nums[i];
            else b+=nums[i];
        }
        return Math.abs(a-b);
    }
}
