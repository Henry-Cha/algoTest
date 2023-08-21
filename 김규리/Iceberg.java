package gitCodding;

import java.io.*;
import java.util.*;

public class Iceberg {
	
	static int ice[][];
	static boolean visited[][];
	static int N;
	static int M;
	static int dx[] = {0, 0, -1, 1};
	static int dy[] = {1, -1, 0, 0};
	
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ice = new int[N][M];
        int cnt = 0;
        int result = 0;
        
        for(int i = 0; i < N; i++) {
        	StringTokenizer st2 = new StringTokenizer(br.readLine());
        	
        	for(int j = 0; j < M; j++) {
        		ice[i][j] = Integer.parseInt(st2.nextToken());
        	}
        }
        
        while((cnt = icecnt()) < 2) {
        	if(cnt == 0) {
        		result = 0;
        		break;
        	}
        	
        	bfs();
        	result += 1;
        }
        
        System.out.println(result);
    }
    
    public static int icecnt() {
    	visited = new boolean[N][M];
    	
    	int cnt = 0;
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < M; j++) {
    			if(ice[i][j] != 0 && !visited[i][j]) {
    				dfs(i, j, visited);
    				cnt += 1;
    			}
    		}
    	}
    	return cnt;
    }
    
    public static void dfs(int x, int y, boolean visited[][]) {
    	visited[x][y] = true;
    	
    	for(int i = 0; i < 4; i++) {
    		int nx = x + dx[i];
    		int ny = y + dy[i];
    		
    		if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
    			continue;
    		}
    		
    		if(ice[nx][ny] != 0 && !visited[nx][ny]) {
    			dfs(nx, ny, visited);
    		}
    	}
    }
    
    static class Node{
    	int x;
    	int y;
    	
    	public Node(int x, int y) {
    		this.x = x;
    		this.y = y;
    	}
    }
    
    public static void bfs() {
    	Queue<Node> q = new LinkedList<>();
    	visited = new boolean[N][M];
    	
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < M; j++) {
    			if(ice[i][j] != 0) {
    				q.offer(new Node(i, j));
    				visited[i][j] = true;
    			}
    		}
    	}
    	
    	while(!q.isEmpty()) {
    		Node node = q.poll();
    		
    		int sea = 0;
    		int g_x = node.x;
    		int g_y = node.y;
    		
    		for(int i = 0; i < 4; i++) {
    			int nx = g_x + dx[i];
    			int ny = g_y + dy[i];
    			
    			if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
    				continue;
    			}
    			
    			if(!visited[nx][ny] && ice[nx][ny] == 0) {
    				sea += 1;
    			}
     		}
    		
    		if(ice[g_x][g_y] - sea < 0) {
    			ice[g_x][g_y] = 0;
    		}
    		else {
    			ice[g_x][g_y] -= sea;
    		}
    	}
    	
    }
}

