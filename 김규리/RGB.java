package gitCodding;

import java.io.*;
import java.util.*;

public class RGB {
    
    static String RGB[][];
    static String RGB2[][];
    static List<Integer> color = new ArrayList<>();
    static boolean visited[][];
    static int dx[] = {0, 0, -1, 1};
    static int dy[] = {1, -1, 0, 0};
    static int N;
    static int result;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        RGB = new String[N][N];
        RGB2 = new String[N][N];
        visited = new boolean[N][N];
        result = 0;
        
        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            String n[] = s.split("");
            
            for(int j = 0; j < N; j++) {
                
                RGB[i][j] = n[j];
                
                if(n[j].equals("G")) {
                    RGB2[i][j] = "R";
                }
                else {
                    RGB2[i][j] = n[j];
                }
            }
        }
        
        check(RGB);
        sb.append(result + " ");
        
        color = new ArrayList<>();
        visited = new boolean[N][N];
        result = 0;
        
        check(RGB2);
        sb.append(result + " ");
        
        System.out.println(sb);
        
    }
    
    static class Node{
        int x;
        int y;
        
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static void bfs(String g[][], int x, int y) {
        Queue<Node> q = new LinkedList<>();
        
        q.offer(new Node(x, y));
        int cnt = 1;

        visited[x][y] = true;
        
        while(!q.isEmpty()) {
            Node node = q.poll();
            
            int g_x = node.x;
            int g_y = node.y;
            
            for(int i = 0; i < 4; i++) {
                int nx = g_x + dx[i];
                int ny = g_y + dy[i];
                
                if(nx == -1 || nx == N || ny == -1 || ny == N) {
                    continue;
                }
                
                if(!visited[nx][ny] && g[nx][ny].equals(g[x][y])) {
                    visited[nx][ny] = true;
                    q.offer(new Node(nx, ny));
                }
            }
        }
    }
    
    public static void check(String g[][]) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!visited[i][j]) {
                    bfs(g, i, j);
                    result += 1;
                }
            }
        }
    }
}