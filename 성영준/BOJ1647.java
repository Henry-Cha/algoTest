package 성영준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1647 {
    static class Edge implements Comparable<Edge> {
        int connect;    // 길 반대쪽 집의 번호 입니다.
        int weight;     // 길의 유지비 입니다.

        public Edge(int connect, int weight) {
            this.connect = connect;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {  // 유지비가 낮은 길을 높은 우선 순위로 두었습니다.
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 집의 수입니다.
        int m = Integer.parseInt(st.nextToken());   // 길의 수입니다.

        List<Edge>[] houses = new LinkedList[n + 1];// 집과 연결된 길을 저장할 List 입니다.
        for (int i = 1; i <= n; i++)
            houses[i] = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());       // 한 쪽 집의 번호 입니다.
            int b = Integer.parseInt(st.nextToken());       // 반대 쪽 집의 번호 입니다.
            int weight = Integer.parseInt(st.nextToken());  // 길의 유지비 입니다.

            houses[a].add(new Edge(b, weight));
            houses[b].add(new Edge(a, weight));
        }

        System.out.println(prim(houses, n, m));             // 가장 낮은 두 마을의 유지비를 출력합니다.
    }

    private static int prim(List<Edge>[] houses, int n, int m) {
        int ans = 0; // 가장 낮은 총 유지비 입니다.
        int max = 0; // 그 중 가장 높은 유지비 입니다.
        Queue<Edge> q = new PriorityQueue<>();      // 낮은 유지비를 가진 길부터 선택 하기 위한 q입니다.
        boolean[] visited = new boolean[n + 1];     // 중복 선택을 방지할 array입니다.

        q.add(new Edge(1, 0));        // q에 1번 길부터 넣고 시작합니다.

        while (!q.isEmpty()) {                      // q가 비면 중지합니다.
            Edge now = q.poll();                    // 현재 유지비가 가장 낮은 길을 통한 집이

            if (visited[now.connect])               // 방문한 집이라면
                continue;                           // 아래 코드를 실행하지 않습니다.
            visited[now.connect] = true;            // 아니라면 방문합니다.
            ans += now.weight;                      // 총 유지비에 더합니다.
            max = Math.max(max, now.weight);        // 가장 높은 유지비인지 확인합니다.

            for (Edge next : houses[now.connect]) { // 그 집과 연결된 모든 길들을 q에 더합니다.
                if (visited[next.connect])          // 길을 통한 집이 방문한 집이라면
                    continue;                       // 방문하지 않습니다.
                q.add(next);                        // 방문하지 않은 집이라면, q에 추가합니다.
            }
        }

        return ans - max;                           // 가장 높은 유지비를 제외하여 마을을 2개로 만듭니다.
    }
}
