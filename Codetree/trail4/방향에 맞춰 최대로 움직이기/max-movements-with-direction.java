import java.util.*;

public class Main {
    static int[] dr = new int[] {0, -1, -1, 0, 1, 1, 1, 0, -1}; // 0: 움직x, 1:상 ~ 8: 좌상
    static int[] dc = new int[] {0, 0, 1, 1, 1, 0, -1, -1, -1};

    static class Node {
        int id;
        int r;
        int c;
        int w;
        Node(int id, int r, int c, int w) {
            this.id = id;
            this.r = r;
            this.c = c;
            this.w = w;
        }
    }

    static int answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] num = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                num[i][j] = sc.nextInt();
            }
        }
        int[][] moveDir = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                moveDir[i][j] = sc.nextInt();
            }
        }
        int r = sc.nextInt() - 1;
        int c = sc.nextInt() - 1;
        // Please write your code here.
        answer = 0;

        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(num[r][c], r, c, 0));
        while (!q.isEmpty()) {
            Node node = q.poll();

            int dir = moveDir[node.r][node.c];
            int nr = node.r + dr[dir];
            int nc = node.c + dc[dir];
            while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                if (num[nr][nc] > node.id) {
                    int newDist = node.w + 1;
                    q.offer(new Node(num[nr][nc], nr, nc, newDist));
                    if (newDist > answer) answer = newDist;
                }
                nr = nr + dr[dir];
                nc = nc + dc[dir];
            }
        }

        System.out.println(answer);
    }
}
