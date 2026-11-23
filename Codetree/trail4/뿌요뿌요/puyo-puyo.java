import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    static int n;
    static int[][] grid;
    static boolean[][] visited;
    static TreeMap<Integer, Integer> map;

    static int num;
    static int cnt;

    static int totalCnt;
    static int maxCnt;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();

        visited = new boolean[n][n];
        // Please write your code here.
        map = new TreeMap<>();

        totalCnt = 0;
        maxCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {

                    num = grid[i][j];
                    cnt = 1;
                    visited[i][j] = true;
                    dfs(i, j);
                    if (cnt > maxCnt)
                        maxCnt = cnt;
                    if (cnt >= 4) {
                        totalCnt++;

                    }
                }

            }
        }

        System.out.println(totalCnt + " " + maxCnt);
    }

    static int[] dr = new int[] {-1, 1, 0, 0};
    static int[] dc = new int[] {0, 0, -1, 1};

    static void dfs(int r, int c) {
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                if (grid[nr][nc] == num && !visited[nr][nc]) {
                    cnt++;
                    visited[nr][nc] = true;
                    dfs(nr, nc);
                }
            }
        }

    }
}
