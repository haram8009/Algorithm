import java.util.*;

public class Main {
    static List<Integer>[] graph;
    static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        graph = new List[n + 1];
        for (int i = 1; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        // [노드][해당 노드에 가해지는 최대 압력, 해당 압력 받는 개수]
        dp = new int[n + 1];

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            // 해당 노드에 압력을 주는 노드 저장
            graph[v].add(u);
        }

        // Please write your code here.
        int answer = 0;
        for (int i = 1; i < graph.length; i++) {
            int tmp = dfs(i);
            if(tmp>answer)
                answer = tmp;
        }

//        System.out.println(Arrays.toString(dp));
        System.out.println(answer);
    }

    public static int dfs(int node) {
        if (dp[node] != 0) {
            return dp[node];
        }

        if (graph[node].isEmpty()) {
            dp[node] = 1;
            return dp[node];
        }

        int max_w = 0;
        int cnt = 0;
        for (int next : graph[node]) {
            int tmp = dfs(next);
            if (tmp > max_w) {
                cnt = 1;
                max_w = tmp;
            } else if (tmp == max_w) {
                max_w = tmp;
                cnt++;
            }
        }

        dp[node] = cnt > 1 ? max_w + 1 : max_w;
        return dp[node];
    }
}