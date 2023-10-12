package algoTest.차성원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17087 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int l = 1;
        int r = arr[n-1];
        int answer=0;
        while(l<=r){
            int mid=(l+r)/2;
            int cnt=0;
            for(int i=0;i<n;i++){
                if(arr[i]<mid) continue;
                cnt+=arr[i]/mid;
            }
            if(cnt<m){
                r=mid-1;
            }else{
                answer = answer>mid?answer:mid;
                l=mid+1;
            }
        }
        System.out.println(answer);
    }
}
