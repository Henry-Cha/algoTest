import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ16236 {
    static int[][] map;
    static int N;
    static Point shark;
    static int sharkSize = 2;
    static int sizeUpCnt=0;
    static int time = 0;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    shark = new Point(i, j,0);
                    map[i][j]=0;
                }
            }
        }
        while(true) {
            visit = new boolean[N][N];
            PriorityQueue<Point> q = new PriorityQueue<>();
            q.add(shark);
            visit[shark.x][shark.y] = true;
            boolean flag = false;
            while (!q.isEmpty()) {
                Point now = q.poll();
                if(map[now.x][now.y]>0 && map[now.x][now.y]<sharkSize){
                    map[now.x][now.y]=0;
                    shark=new Point(now.x,now.y,0);
                    sizeUpCnt++;
                    time += (now.dis);
                    flag=true;
                    if(sizeUpCnt==sharkSize){
                        sharkSize++;
                        sizeUpCnt=0;
                    }
                    break;
                }
                for (int d = 0; d < 4; d++) {
                    int nx = now.x + dx[d];
                    int ny = now.y + dy[d];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    if(visit[nx][ny]) continue;
                    if (map[nx][ny] > sharkSize) continue;
                    q.add(new Point(nx,ny, now.dis+1));
                    visit[nx][ny] = true;
                }
            }
            if(!flag) break;
        }
        System.out.println(time);
    }
    static class Point implements Comparable<Point> {
        int x;
        int y;
        int dis;

        public Point(int x, int y,int dis) {
            this.x = x;
            this.y = y;
            this.dis=dis;
        }

        @Override
        public int compareTo(Point o) {
            if(this.dis == o.dis) {
                if (this.x == o.x) {
                    return this.y - o.y;
                }
                return this.x - o.x;
            }return this.dis-o.dis;
        }
    }
}
