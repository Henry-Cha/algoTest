import java.io.*;
import java.util.*;

class Main {
	static int[] dx = {-1, 0, 1, 0};	//북 동 남 서
	static int[] dy = {0, 1, 0, -1};
	static String[][] map;
    static int N, M, x, y, d, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new String[N][M];
        
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        
        for(int i=0; i<N; i++) 
        	map[i] = br.readLine().split(" ");
        
        boolean flg = true;
        cnt = 0;
        
        while(flg) {
        	if(map[x][y].equals("0")) {
        		cnt ++;
        		map[x][y] = "2";
        	}
        	
        	boolean dirFlg = false;
        	for(int i=0; i<4; i++) {
        		d --;
        		if(d < 0) d = 3;
        		
        		int nx = x + dx[d];
        		int ny = y + dy[d];
        		

        		if(map[nx][ny].equals("0")) {
        			x = nx;
        			y = ny;
        			dirFlg = true;
        			break;
        		}
        		      		
        	}
        	
        	if(!dirFlg) {
        		int nx = x - dx[d];
        		int ny = y - dy[d];
        		if(map[nx][ny].equals("1")) flg = false;
        		else {
        			x = nx;
        			y = ny;
        		}
        	}
        }
        System.out.println(cnt);
    }
}
