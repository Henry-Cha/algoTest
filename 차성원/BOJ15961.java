package algoTest.차성원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15961 {
    static int N;
    static int d;
    static int k;
    static int c;
    static int[] rail;
    static int[] sushi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        rail = new int[N+k-1];
        sushi = new int[d+1];
        for(int i=0;i<N;i++){
            rail[i] = Integer.parseInt(br.readLine());
        }
        for(int i=0;i<k-1;i++){
            rail[N+i]=rail[i];
        }
        sushi[c]=1;
        int answer =1;
        int cnt=answer;
        for(int i=0;i<k;i++){
            if(sushi[rail[i]]==0) {
                cnt++;
            }
            sushi[rail[i]]++;
        }
        answer=answer>cnt?answer:cnt;
        for(int i=k;i<N+k-1;i++){
            if(sushi[rail[i]]==0) {
                cnt++;
            }
            sushi[rail[i]]++;

            sushi[rail[i-k]]--;
            if(sushi[rail[i-k]]==0) {
                cnt--;
            }
            answer=answer>cnt?answer:cnt;
        }
        System.out.println(answer);
    }
}
