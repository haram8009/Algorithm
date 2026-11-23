import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
public class Main {
    static int n, k, u, d;
    static int[][] grid;

    static int maxDist;

    static int[] dr = new int[] {-1, 1, 0, 0};
    static int[] dc = new int[] {0, 0, -1, 1};

    static boolean[][] visited;

    static int[][] starts;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        u = sc.nextInt();
        d = sc.nextInt();
        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.
        maxDist = 0;

        starts = new int[k][2]; // k개의 좌표 r, c

        dfs(0, 0);

        System.out.println(maxDist);

    }

    static class Node {
        int r;
        int c;
        int w;
        Node(int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }
    }
    static void dfs(int depth, int start) {
        if (depth == k) {
            bfs();
            return;
        }

        for (int idx = start; idx < n * n; idx++) {
            starts[depth][0] = idx / n;
            starts[depth][1] = idx % n;
            dfs(depth + 1, idx + 1);
        }
    }

    static void bfs() {
        Queue<Node> q = new ArrayDeque<>();
        visited = new boolean[n][n];

        int cnt = 0;
        for (int i = 0; i < k; i++) {
            q.offer(new Node(starts[i][0], starts[i][1], 1));
            visited[starts[i][0]][starts[i][1]] = true;
            cnt++;
        }

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nr = node.r + dr[dir];
                int nc = node.c + dc[dir];

                if ((nr >= 0 && nr < n && nc >= 0 && nc < n) && !visited[nr][nc]) {
                    int diff = Math.abs(grid[node.r][node.c] - grid[nr][nc]);

                    if (diff >= u && diff <= d) {
                        q.offer(new Node(nr, nc, node.w + 1));
                        visited[nr][nc] = true;
                        cnt++;
                    }
                }
            }
        }

        maxDist = Math.max(maxDist, cnt);
    }
}
