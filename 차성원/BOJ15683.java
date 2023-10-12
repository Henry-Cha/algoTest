package algoTest.차성원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15683 {
    static int n;
    static int m;
    static int[][] map;
    static int[] dx={-1,0,1,0};
    static int[] dy={0,1,0,-1};
    static List<int[]> cctvs;
    static int answer;
    static int left=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        cctvs = new ArrayList<>();
        List<int[]> all = new ArrayList<>();
        map=new int[n][m];
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==0) {
                    left++;
                    continue;
                }
                if(map[i][j]==6) continue;
                if(map[i][j]==5){
                    all.add(new int[]{i,j});
                }else{
                    cctvs.add(new int[]{i,j});
                }
            }
        }
        if(!all.isEmpty()){
            for(int[] tmp : all){
                for(int d=0;d<4;d++){
                    showAndBack(-1,tmp[0],tmp[1],d);
                }
            }
        }
        answer=left;
        dfs(0,cctvs.size());
        System.out.println(answer);
    }
    public static void dfs(int idx,int len){
        if(idx==len){
            answer=answer>left?left:answer;
            return;
        }
        if(left==0) {
            answer=0;
            return;
        }
        int[] cctv = cctvs.get(idx);
        for(int d=0;d<4;d++) {
            rotate(-1, cctv[0], cctv[1], d);
            dfs(idx + 1, len);
            rotate(1, cctv[0], cctv[1], d);
        }
    }
    public static void rotate(int type,int i,int j,int d){
        int cctv = map[i][j];
        if(cctv==1){
            showAndBack(type,i,j,d);
        }else if(cctv==2){
            if(d==2 || d==3) return;
            showAndBack(type,i,j,d);
            showAndBack(type,i,j,d+2);
        } else if (cctv==3) {
            showAndBack(type,i,j,d);
            showAndBack(type,i,j,d+1>3?0:d+1);
        }else if(cctv==4){
            for(int k=0;k<4;k++) {
                if(k==d) continue;
                showAndBack(type, i, j, k);
            }
        }
    }
    public static void showAndBack(int type,int i,int j,int d){
        i+=dx[d];
        j+=dy[d];
        while(true){
            if(i<0 || i>=n || j<0 || j>=m) break;
            if(map[i][j]==6) break;
            if(map[i][j]<=0) {
                if (map[i][j] == 0) {
                    left+=type;
                }
                map[i][j] += type;
                if (map[i][j] == 0) {
                    left+=type;
                }
            }
            i+=dx[d];
            j+=dy[d];
        }
    }
}