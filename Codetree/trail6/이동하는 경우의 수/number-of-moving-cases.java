import java.util.*;

public class Main {

    static int[] dp; // 1번노드에서 해당노드까지 걸리는 최장 시간, -1이면 도달불가
    static int[] degree; // 위상정렬
    static List<int[]>[] graph; // [ to, weight ]
    static List<int[]>[] oppGraph;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        graph = new List[n + 1];
        oppGraph = new List[n + 1];

        dp = new int[n + 1];
        degree = new int[n + 1];
        Arrays.fill(dp, -1); // 도달불가로 초기화

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            oppGraph[i] = new ArrayList<>();

        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int t = sc.nextInt();

            graph[u].add(new int[] { v, t });
            oppGraph[v].add(new int[] { u, t });

            degree[v]++;
        }

        // Please write your code here.

        Queue<Integer> q = new ArrayDeque<>(); // node i
        dp[1] = 0;
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 0) // 차수 0인 노드 다 넣어도 1번 노드만 도달가능하게 표시해놔서 ㄱㅊ음
                q.offer(i);
        }

        // 일단 최장시간 재고
        // 그후에 그 시간을 만드는 모든 경로에 해당하는 노드 개수를 센다. (중복없이)
        while (!q.isEmpty()) {
            int node = q.poll();

            for (int[] next : graph[node]) {
                int nnode = next[0];
                int nw = next[1];

                int newDist = dp[node] + nw;

                if (dp[node] >= 0 && newDist > dp[nnode]) {
                    dp[nnode] = newDist;
                }
                if (--degree[nnode] == 0) { // 갱신여부와 무관하게 노드 처리했으니 감소해야함
                    q.offer(nnode);
                }
            }
        }

        int cnt = 0;

        boolean[] visited = new boolean[n + 1]; // 큐에 한 번만 들어가도록 제한
        visited[n] = true;
        q.offer(n);
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int[] next : oppGraph[node]) {
                int nnode = next[0];
                int nw = next[1];
                if (dp[node] >= 0 && dp[nnode] + nw == dp[node]) {
                    cnt++;
                    if (!visited[nnode]) {
                        visited[nnode] = true;
                        q.offer(nnode);
                    }
                }
            }
        }
        System.out.println(dp[n] + " " + cnt);

    }
}