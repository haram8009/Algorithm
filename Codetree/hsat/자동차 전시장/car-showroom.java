import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        int[][] edges = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            graph[edges[i][0]].add(edges[i][1]);
        }
        st = new StringTokenizer(br.readLine());
        int[] startPoints = new int[k];
        for (int i = 0; i < k; i++) {
            startPoints[i] = Integer.parseInt(st.nextToken());
        }
        // 모든사람이 도달 가능한 노드에만 전시장 설치 가능
        // 노드수 n 간선수 m 사람수 k (k는 전부 서로 다른 노드에 있음)

        // 잇지 않아도 되는 노드가 있을 수 있음
        // 사람이 서있지 않아도 이어야하는 노드가 있을 수 있음

        // 사람들이 출발하는 각 노드에서 다른 노드 방문하는 가장 짧은 루트찾기
        int[][] dist = new int[k][n + 1]; // dist[i][j]: i번째사람이 j노드 방문하는 가장짧은 거리
        for (int i = 0; i < k; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < k; i++) {
            boolean[] visited = new boolean[n + 1];
            Queue<Node> pq = new ArrayDeque<>();
            pq.offer(new Node(startPoints[i], 0));

            while (!pq.isEmpty()) {
                Node node = pq.poll();
                if (visited[node.to]) continue;

                dist[i][node.to] = Math.min(dist[i][node.to], node.w);
                visited[node.to] = true;

                for (int nnode : graph[node.to]) {
                    pq.offer(new Node(nnode, node.w + 1));
                }
            }
        }

        // for (int i = 0; i < k; i++) {
        //     System.out.println(Arrays.toString(dist[i]));
        // }

        int answer = Integer.MAX_VALUE;

        for (int j = 1; j <= n; j++) {
            int tmp = 0;
            for (int i = 0; i < k; i++) {
                tmp = Math.max(tmp, dist[i][j]);
            }

            answer = Math.min(answer, tmp);
        }

        if (answer == Integer.MAX_VALUE) answer = -1;
        System.out.println(answer);
    }

    static class Node implements Comparable<Node> {
        int to;
        int w;
        Node(int to, int w) {
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }
}
