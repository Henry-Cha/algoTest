package algoTest.차성원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class BOJ14003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] answer = new int[n];
        List<Integer> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int top=-1;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            if (list.isEmpty() || arr[i] > arr[list.get(top)]) {
                list.add(i);
                answer[i] = list.size()-1;
                top++;
            } else if (arr[i] <= arr[list.get(top)]) {
                int l = 0;
                int r = top;
                while(l<=r) {
                    int m = (l+r)/2;
                    if(arr[i]<arr[list.get(m)])
                        r=m-1;
                    else if(arr[i]>arr[list.get(m)]) {
                        l=m+1;
                    }
                    else {
                        l=m;
                        break;
                    }
                }
                list.set(l, i);
                answer[i]=l;
            }
        }
        StringBuilder sb = new StringBuilder();
        int cnt = list.size();
        sb.append(cnt).append("\n");
        int[] res = new int[list.size()];
        while(cnt-->=0){
            while(--n>=0){
                if(answer[n]==cnt){
                    res[list.size()-cnt-1] = arr[n];
                    break;
                }
            }
        }
        for(int i=res.length-1;i>-1;i--){
            sb.append(res[i]).append(" ");
        }
        System.out.println(sb);
    }
}