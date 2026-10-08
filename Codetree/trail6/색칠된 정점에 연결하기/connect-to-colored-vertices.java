import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();
        int[] coloredVertices = new int[K];
        for (int i = 0; i < K; i++) {
            coloredVertices[i] = sc.nextInt();
        }
        // int[][] edges = new int[M][3];

        List<int[]>[] graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            // edges[i][0] = sc.nextInt();
            // edges[i][1] = sc.nextInt();
            // edges[i][2] = sc.nextInt();
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            graph[u].add(new int[] {v, w});
            graph[v].add(new int[] {u, w});
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dp = new int[N + 1];
        boolean[] selected = new boolean[N + 1];
        Arrays.fill(dp, 10001);

        // 1. 색칠된 노드를 시작노드로 일단 트리에 넣어버림
        for (int i = 0; i < K; i++) {
            int node = coloredVertices[i];
            dp[node] = 0;
            pq.offer(new Node(node, 0));
        }
        // 2. 거기서부터 그리디하게 노드 연결시킴
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.w > dp[node.to]) continue;
            selected[node.to] = true;

            // System.out.println("+++poll+++\n" + node.to + " " + node.w);
            for (int[] nnode : graph[node.to]) {
                // nnode: [to, w]
                if (!selected[nnode[0]] && nnode[1] < dp[nnode[0]]) {
                    // System.out.println("===offer===\n" + nnode[0] + " " + nnode[1]);
                    dp[nnode[0]] = nnode[1];
                    pq.offer(new Node(nnode[0], nnode[1]));
                    // System.out.println(Arrays.toString(dp));
                }
            }
        }

        // 3. 필요한 간선만 채택하여 여러개의 트리가 생성돼있음 -> solved
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            answer += dp[i];
        }

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
