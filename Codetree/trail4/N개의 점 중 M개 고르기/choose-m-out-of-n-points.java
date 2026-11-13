import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] points;
    static int[][] dist;
    static int answer;
    static int[] selected;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        points = new int[n][2];
        for (int i = 0; i < n; i++) {
            points[i][0] = sc.nextInt();
            points[i][1] = sc.nextInt();
        }
        // Please write your code here.
        answer = Integer.MAX_VALUE;
        selected = new int[m];

        // 각 점 사이 거리 계산해놓기
        dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (i == j) continue;
                int x1, y1, x2, y2;
                x1 = points[i][0];
                y1 = points[i][1];
                x2 = points[j][0];
                y2 = points[j][1];

                dist[i][j] = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
            }
        }

        // System.out.println(Arrays.deepToString(dist));

        dfs(0, 0);
        System.out.println(answer);

    }

    static void dfs(int s, int cnt) {
        if (cnt == m) {
            calc();
            return;
        }

        for (int i = s; i < n; i++) {
            selected[cnt] = i;
            dfs(i + 1, cnt + 1);
        }
    }

    static void calc() {
        // System.out.println(Arrays.toString(selected));
        int tmp = 0;
        for (int i = 0; i < m; i++) {
            int p1 = selected[i];
            for (int j = i + 1; j < m; j++) {
                int p2 = selected[j];
                tmp = Math.max(tmp, dist[p1][p2]);
            }
        }

        answer = Math.min(answer, tmp);
        // System.out.println(answer);
    }

}
