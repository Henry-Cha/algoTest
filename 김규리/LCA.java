package gitCodding;

import java.io.*;
import java.util.*;

public class LCA {
    
    static int V;
    static int E;
    static ArrayList<ArrayList<Integer>> tree;
    static int d[];
    static int p[];
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
//        int T = Integer.parseInt(br.readLine());
//        
//        for(int i = 1; i <= T; i++) {
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        
//        V = Integer.parseInt(st.nextToken());
//        E = Integer.parseInt(st.nextToken());
//        int V1 = Integer.parseInt(st.nextToken());
//        int V2 = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(br.readLine());
        
        d = new int[V + 1];
        p = new int[V + 1];
        
        tree = new ArrayList<ArrayList<Integer>>();
        
        for(int j = 0; j < V + 1; j++) {
            tree.add(new ArrayList<Integer>());
        }
        
        for(int j = 0; j < V - 1; j++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken());
            
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        dfs(1, 1);
        // 공통조상 찾는 노드
        
        int M = Integer.parseInt(br.readLine());
        
        for(int j = 0; j < M; j++) {
            StringTokenizer st3 = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st3.nextToken());
            int b = Integer.parseInt(st3.nextToken());
            
            int root;
            
            if(d[a] > d[b]) {
                root = parent(a, b);
            }
            else {
                root = parent(b, a);
            }
            
            sb.append(root + "\n");
        }
        
        System.out.println(sb);
    }
//            int root;
//            
//            if(d[V1] > d[V2]) {
//                root = parent(V1, V2);
//            }
//            else {
//                root = parent(V2, V1);
//            }
//            
//            sb.append("#" + i + " " + root + " " + subTreeSize(root)  +"\n");
//        }
//        System.out.println(sb);
//    }
    
    public static void dfs(int s, int cnt) {
        d[s] = cnt++;

        for(int n : tree.get(s)) {
            if(d[n] == 0) {
                dfs(n, cnt);
                
                p[n] = s;
            }
        }
    }
    
    public static int subTreeSize(int p) {
        Stack<Integer> s = new Stack<>();
        boolean visited[] = new boolean[V + 1];
        int child_cnt = 1;
        
        s.push(p);
        
        visited[p] = true;
        
        while(!s.isEmpty()) {
            int n = s.pop();
            
            for(int child :tree.get(n)) {
                if(d[child] > d[n] && !visited[child]) {
                    s.push(child);
                    child_cnt += 1;
                    visited[child] = true;
                }
            }
        }
        
        return child_cnt;
    }
    
    public static int parent(int a, int b) {
        
        while(d[a] != d[b]) {
            a = p[a];
        }
        
        while(a != b) {
            a = p[a];
            b = p[b];
        }
        
        return a;
        
    }

}