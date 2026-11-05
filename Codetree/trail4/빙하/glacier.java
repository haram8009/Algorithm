import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                grid[i][j] = sc.nextInt();
        // Please write your code here.
        int t = 0;
        int cnt = 0;
        boolean[][] visited = new boolean[n][m];

        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(new Node(0, 0, 0));

        while (!q.isEmpty()) {
            Node node = q.poll();
            // System.out.println(node);
            if (visited[node.r][node.c]) {
                continue;
            }

            visited[node.r][node.c] = true;

            if (grid[node.r][node.c] == 1) {
                grid[node.r][node.c] = 0;
                if (node.t == t) {
                    cnt++;
                } else if (node.t > t) {
                    t = node.t;
                    cnt = 1;
                }

            }

            for (int d = 0; d < 4; d++) {
                int nr = node.r + dr[d];
                int nc = node.c + dc[d];
                if (inRange(n, m, nr, nc) && !visited[nr][nc]) {
                    if (grid[nr][nc] == 1)
                        q.offerLast(new Node(nr, nc, node.t + 1));
                    else
                        q.offerFirst(new Node(nr, nc, node.t));
                }
            }

        }

        System.out.println(t + " " + cnt);
    }

    // 시계방향
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    static class Node implements Comparable<Node> {
        int r;
        int c;
        int t;
        Node(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.t, o.t);
        }

        public String toString() {
            return "Name[" + this.r + ", " + this.c + ", " + this.t + "]";
        }
    }

    static boolean inRange(int n, int m, int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }
}
