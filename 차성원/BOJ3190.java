package algoTest.차성원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int k;
    static int l;
    static Snake snake;
    static int[][] map; //0 빈칸, 1~4 뱀방향 5 사과
    static int[] dx={0,1,0,-1};
    static int[] dy={1,0,-1,0};
    static int time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n= Integer.parseInt(br.readLine());
        k= Integer.parseInt(br.readLine());
        snake = new Snake(new Point(0,0),new Point(0,0),1,0);
        map = new int[n][n];
        time = 0;
        map[0][0]=1;
        for(int i=0;i<k;i++){
            String[] xy=br.readLine().split(" ");
            int x=Integer.parseInt(xy[0])-1;
            int y=Integer.parseInt(xy[1])-1;
            map[x][y]=5;
        }
        l=Integer.parseInt(br.readLine());
        for(int i=0;i<l;i++) {
            String[] tmp = br.readLine().split(" ");
            move(Integer.parseInt(tmp[0]),tmp[1].charAt(0));
        }
        move(1000000,'a');
    }
    public static void move(int t,char d){
        while(time++<t){
            Point now = snake.head;
            snake.head = new Point(now.x+dx[snake.dir],now.y+dy[snake.dir]);
            if(time==t){
                if(d=='L') {
                    snake.dir -= 1;
                    if(snake.dir<0) snake.dir=3;
                }
                else {
                    snake.dir += 1;
                    if(snake.dir>3) snake.dir=0;
                }
            }
            if(!check(snake.head)){
                System.out.println(time);
                System.exit(0);
            }
        }
        time-=1;
    }

    public static boolean check(Point p){
        if(p.x < 0 || p.x>=n || p.y<0 || p.y>=n) return false;
        if(map[p.x][p.y]>0 && map[p.x][p.y]<5) return false;
        boolean appleFlag = false;
        if(map[p.x][p.y]==5) appleFlag = true;
        map[p.x][p.y] = snake.dir+1;
        if(!appleFlag){
            int next = map[snake.tail.x][snake.tail.y];
            map[snake.tail.x][snake.tail.y]=0;
            snake.tail = new Point(snake.tail.x+dx[next-1],snake.tail.y+dy[next-1]);
        }
        return true;
    }

    static class Point{
        int x;
        int y;
        public Point(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
    static class Snake{
        Point head;
        Point tail;
        int length;
        int dir;

        public Snake(Point head, Point tail, int length, int dir) {
            this.head = head;
            this.tail = tail;
            this.length = length;
            this.dir = dir;
        }
    }
}
