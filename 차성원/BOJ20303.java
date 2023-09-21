import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20303 {
    static int N, M, K;
    static int[] arr;
    static int[] parents;
    static int[] treeCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        parents = new int[N + 1];
        treeCnt = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            parents[i] = i;
            treeCnt[i] = 1;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a,b);
        }
        for(int i=1;i<=N;i++){
            if(parents[i]==i) continue;
            int ip = find(i);
            arr[ip]+=arr[i];
            treeCnt[ip]+=treeCnt[i];
        }

        int[] dp = new int[K+1];
        for(int i=1;i<=N;i++) {
            if(parents[i]!=i) continue;
            for (int j = K-1; j-treeCnt[i] >= 0; j--) {
                dp[j] = Math.max(dp[j],dp[j-treeCnt[i]]+arr[i]);
            }
        }
        System.out.println(dp[K-1]);

    }
    public static void union(int a,int b){
        int ap = find(a);
        int bp = find(b);
        if(ap<bp) parents[bp] = ap;
        else parents[ap]=bp;
    }
    public static int find(int a){
        if(a==parents[a]) return a;
        return parents[a]=find(parents[a]);
    }
}
