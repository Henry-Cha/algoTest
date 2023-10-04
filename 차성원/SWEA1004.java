package algoTest.차성원;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
 
public class SWEA1004 {
    static int[][] graph;
    static boolean[][] visit;
    static int N;
    static int M;
 
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            graph = new int[N + 1][N + 1];
            int answer = 0;
 
            for (int i = 0; i < M; i++) {
                String[] ab = br.readLine().split(" ");
                int a = Integer.parseInt(ab[0]);
                int b = Integer.parseInt(ab[1]);
                graph[a][b] = 1;
                graph[b][a] = 2;
            }
            for (int n = 1; n <= N; n++) {
                visit = new boolean[2][N + 1];              
                int upCnt=0;
                int downCnt=0;
                Queue<Integer> up=new LinkedList<Integer>();
                up.add(n);
                visit[0][n]=true;
                while(!up.isEmpty()) {
                    int now = up.poll();
                    for(int i=1;i<=N;i++) {
                        if(graph[now][i]!=1) continue;
                        if(visit[0][i]) continue;
                        visit[0][i] = true;
                        up.add(i);
                        upCnt++;
                    }
                }
                Queue<Integer> down=new LinkedList<Integer>();
                down.add(n);
                visit[1][n]=true;
                while(!down.isEmpty()) {
                    int now = down.poll();
                    for(int i=1;i<=N;i++) {
                        if(graph[now][i]!=2) continue;
                        if(visit[1][i]) continue;
                        visit[1][i] = true;
                        down.add(i);
                        downCnt++;
                    }
                }
                if (upCnt + downCnt == N - 1) {
                    answer++;
                }
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}