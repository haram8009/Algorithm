import java.util.*;

public class Main {
    // 상하좌우
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    // union find arr
    static int[] parents;

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int w;

        Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static class Point implements Comparable<Point> {
        int r;
        int c;
        int w;

        Point(int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.w, o.w);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][n];

        List<int[]> nodes = new ArrayList<>(); // [r, c]

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
                if (grid[i][j] == 1 || grid[i][j] == 2) {
                    nodes.add(new int[] { i, j });
                }
            }
        }
        // Please write your code here.

        // 0. 가지치기: 상하좌우 중 하나라도 접근 가능한 길이 없으면 도달불가한 기지 존재하는거임 -> -1 출력
//        for (int[] node : nodes) {
//            int possible = 0;
//            for (int d = 0; d < 4; d++) {
//                int nr = node[0] + dr[d];
//                int nc = node[1] + dc[d];
//                if (isInRange(nr, nc, n) && grid[nr][nc] != -1) {
//                    possible++;
//                }
//            }
//            if (possible == 0) {
//                // 도달불가한기지존재
//                System.out.println(-1);
//                return;
//            }
//        }

        // 활성화 기지 단 1개
        // 모든 활성화 로봇의 총 이동 거리를 최소화 => 최소신장트리
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        // 1. 간선리스트 만들기: 모든 기지 쌍에서 최소거리 간선 구하기
        // 두 기지중 하나는 활성화 기지여야한다. < 근데 사실 순서를 맘대로 할 수 있으니까 고려 안해도 될듯

        for (int i = 0; i < nodes.size(); i++) {
            int[][] dp = new int[n][n]; // i 노드에서 도달할 수 있는 최소 거리 구하기
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[j], Integer.MAX_VALUE);
            }

            // bfs -> dp 완성
            PriorityQueue<Point> que = new PriorityQueue<>();
            que.offer(new Point(nodes.get(i)[0], nodes.get(i)[1], 0));
            dp[nodes.get(i)[0]][nodes.get(i)[1]] = 0;

            while (!que.isEmpty()) {
                Point node = que.poll();
                if (node.w > dp[node.r][node.c])
                    continue;
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + dr[d];
                    int nc = node.c + dc[d];
                    if (isInRange(nr, nc, n) && grid[nr][nc] != -1) {
                        int dist = node.w + 1;
                        if (dist < dp[nr][nc]) {
                            dp[nr][nc] = dist;
                            que.offer(new Point(nr, nc, dist));
                        }
                    }
                }
            }

            for (int j = i + 1; j < nodes.size(); j++) {
                int r = nodes.get(j)[0];
                int c = nodes.get(j)[1];
                if (dp[r][c] != Integer.MAX_VALUE) {
                    pq.offer(new Edge(i, j, dp[r][c]));
//                    System.out.println("pq.offer " + i + j + dp[r][c]);
                }
            }
        }

        // 2. 거리 기준으로 간선리스트 정렬 -> pq

        // 3. union -> 트리만들기
        parents = new int[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            parents[i] = i;
        }

        int answer = 0;
        int cnt = 0;
        while (!pq.isEmpty() && cnt < nodes.size() - 1) {
            Edge e = pq.poll();
            if (union(e.from, e.to)) {
                answer += e.w;
                cnt++;
            }
        }

        System.out.println(cnt == nodes.size() - 1 ? answer : -1);
    }

    static boolean isInRange(int i, int j, int n) {
        return i >= 0 && i < n && j >= 0 && j < n;
    }

    static int find(int a) {
        if (parents[a] == a) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int A = find(a);
        int B = find(b);

        if (A == B)
            return false;

        parents[A] = parents[B];
        return true;
    }
}
