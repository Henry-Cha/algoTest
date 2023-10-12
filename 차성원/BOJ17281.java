import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17281 {
    static int N;
    static int[][] points;
    static int[] players;
    static boolean[] visit;
    static int answer=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        points = new int[N][9];
        for(int i=0;i<N;i++){
            StringTokenizer st=  new StringTokenizer(br.readLine());
            for(int j=0;j<9;j++){
                points[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        players = new int[9];
        //players[3] = 0;
        visit = new boolean[9];
        visit[0]=true;
        permu(0);
        System.out.println(answer);
    }
    public static void permu(int dep){
        if(dep==9){
            play();
            return;
        }
        if(dep==3){
            permu(dep+1);
        }else {
            for (int i = 1; i < 9; i++) {
                if (visit[i]) continue;
                visit[i] = true;
                players[dep] = i;
                permu(dep + 1);
                visit[i] = false;
            }
        }
    }
    public static void play(){
        int pointSum=0;
        int prev=0;
        for(int round=0;round<N;round++){
            int outCnt=0;
            int start = prev;
            boolean[] base = new boolean[4];
            out:
            while(true){
                int point = points[round][players[start++]];
                switch (point) {
                    case 0:
                        outCnt++;
                        if (outCnt == 3) {
                            prev = start;
                            if(prev==9) prev=0;
                            break out;
                        }
                        break;
                    case 1:
                        if (base[3]) pointSum++;
                        base[3] = base[2];
                        base[2] = base[1];
                        base[1] = true;
                        break;
                    case 2:
                        if (base[3]) pointSum++;
                        if (base[2]) pointSum++;
                        base[3] = base[1];
                        base[2] = true;
                        base[1]=false;
                        break;
                    case 3:
                        if (base[3]) pointSum++;
                        if (base[2]) pointSum++;
                        if (base[1]) pointSum++;
                        base[3] = true;
                        base[2]=false;
                        base[1]=false;
                        break;
                    case 4:
                        if (base[3]) pointSum++;
                        if (base[2]) pointSum++;
                        if (base[1]) pointSum++;
                        base[3] = false;
                        base[2]=false;
                        base[1]=false;
                        pointSum++;
                        break;
                }
                if(start>8) start=0;
            }
        }
        answer=answer>pointSum?answer:pointSum;
    }
}
