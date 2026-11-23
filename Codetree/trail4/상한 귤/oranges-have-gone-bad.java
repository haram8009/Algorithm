import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int n;
    static int k;
    static int[][] grid;
    static int[][] map;

    static int[] dr = new int[] {-1, 1, 0, 0};
    static int[] dc = new int[] {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
        // Please write your code here.

        map = new int[n][n];
        for (int[] row : map) {
            Arrays.fill(row, -1);
        }

        Queue<Node> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) map[i][j] = -2;
                else if (grid[i][j] == 2) {
                    // map[i][j] = 0;
                    q.offer(new Node(i, j, 0));
                }
            }
        }

        boolean[][] visited = new boolean[n][n];

        while (!q.isEmpty()) {
            Node node = q.poll();
            if (visited[node.r][node.c]) continue;
            visited[node.r][node.c] = true;
            map[node.r][node.c] = node.w;

            for (int d = 0; d < 4; d++) {
                int nr = node.r + dr[d];
                int nc = node.c + dc[d];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    if (grid[nr][nc] == 1) {
                        q.offer(new Node(nr, nc, node.w + 1));
                    }
                }

            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

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

}
