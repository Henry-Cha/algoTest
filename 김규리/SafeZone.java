package gitCodding;

import java.io.*;
import java.util.*;

public class SafeZone {
    
	static int N;
    static int area[][];
    static boolean visited[][];
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        area = new int[N][N];
        int max_h = 0;
        
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
                
                if(area[i][j] > max_h) {
                	max_h = area[i][j];
                }
            }
        }
        
        int max = 0;
        
        for(int i = 0; i < max_h + 1; i++) {
            visited = new boolean[N][N];
            int cnt = 0;
            for(int j = 0; j < N; j++) {
            	for(int k = 0; k < N; k++) {
            		if(!visited[j][k] && area[j][k] > i) {
            			cnt += bfs(j, k, i);
            		}
            	}
            }
            max = Math.max(max, cnt);
        }
        System.out.println(max);
    }
    
    static class Node{
        int x;
        int y;
        
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static int bfs(int x, int y, int h) {
         Queue<Node> q = new LinkedList<>();
         
         int dx[] = {0, 0, 1, -1};
         int dy[] = {1, -1, 0, 0};
         
         q.offer(new Node(x, y));
         visited[x][y] = true;
         
         while(!q.isEmpty()) {
             Node node = q.poll();
             
             int g_x = node.x;
             int g_y = node.y;
             
             for(int i = 0; i < 4; i++) {
            	 int nx = g_x + dx[i];
            	 int ny = g_y + dy[i];
            	 
            	 if(nx < 0 || ny < 0 || nx > N-1 || ny > N-1) {
            		 continue;
            	 }
            	 
            	 if(visited[nx][ny]) {
            		 continue;
            	 }
            	 
            	 if(area[nx][ny] > h) {
            		 visited[nx][ny] = true;
            		 q.offer(new Node(nx, ny));
            	 }
             }
         }
         
         return 1;
    }

}