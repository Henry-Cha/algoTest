package algoTest.차성원;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ13023 {
    static List<Integer>[] graph;
    static boolean[] visit;
    static int N;
    static int M;
    static int answer=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        visit = new boolean[N+1];
        for (int i = 0; i < N+1; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        for(int i=0;i<N;i++) {
            visit[i]=true;
            boolean ans = dfs(i,0);
            visit[i]=false;
            if(ans) break;
        }
        System.out.println(answer);
    }
    public static boolean dfs(int s,int d) {
        if(d==4) {
            answer=1;
            return true;
        }
        if(graph[s].size()==0) return false;
        for(int x:graph[s]) {
            if(visit[x]) continue;
            visit[x]=true;
            boolean flag = dfs(x,d+1);
            if(flag) return true;
            visit[x]=false;
        }
        return false;
    }
}