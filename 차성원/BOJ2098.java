import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2098 {
    static int N;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int[N][N];
        dp = new int[N][(1<<N)-1];
        for(int i=0;i<N;i++){
            Arrays.fill(dp[i],21000000);
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(tsp(0,1));
    }
    public static int tsp(int now,int visit){
        if(visit==(1<<N)-1){
            if(map[now][0]==0)
                return 11000000;
            return map[now][0];
        }
        if(dp[now][visit]!=21000000){
            return dp[now][visit];
        }
        for(int i=1;i<N;i++){
            if(map[now][i]==0 || (visit&(1<<i))!=0) continue;
            dp[now][visit] = Math.min(dp[now][visit],tsp(i,visit|(1<<i))+map[now][i]);
        }
        return dp[now][visit];
    }
}
