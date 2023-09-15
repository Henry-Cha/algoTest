import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ16236 {
    static List<Long> list;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list= new ArrayList<>();
        if(N<=10) {
            System.out.println(N);
            return;
        }else if(N>1022) {
            System.out.println(-1);
            return;
        }
        for(int i=0;i<10;i++){
            find(i);
        }
        list.sort(null);
        System.out.println(list.get(N));
    }
    public static void find(long i){
        list.add(i);
        long next = i%10;
        if(next==0) return;
        for(long j=next-1;j>-1;j--){
            find(i*10+j);
        }
    }
}