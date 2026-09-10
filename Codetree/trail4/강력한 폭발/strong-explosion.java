import java.io.*;
import java.util.*;

public class Main {
    // 자기 자신 포함 5칸 (세로 / 십자 / X자)
    static final int[][] DR = {
        {0, -2, -1,  1,  2},
        {0, -1,  1,  0,  0},
        {0, -1, -1,  1,  1}
    };
    static final int[][] DC = {
        {0,  0,  0,  0,  0},
        {0,  0,  0, -1,  1},
        {0, -1,  1, -1,  1}
    };

    static int n, m, answer;
    static int[][] cnt;    // 각 칸을 덮고 있는 폭탄 수
    static int[][] bombs;  // 폭탄 좌표

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());

        cnt = new int[n][n];
        bombs = new int[n * n][2];
        m = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    bombs[m][0] = i;
                    bombs[m][1] = j;
                    m++;
                }
            }
        }

        dfs(0, 0);
        System.out.println(answer);
    }

    static void dfs(int depth, int covered) {
        if (depth == m) {
            if (covered > answer) answer = covered;
            return;
        }
        // 남은 폭탄이 전부 새 칸 5개씩 덮어도 갱신 불가면 컷
        if (covered + (m - depth) * 5 <= answer) return;

        for (int k = 0; k < 3; k++) {
            int added = apply(k, depth, 1);
            dfs(depth + 1, covered + added);
            apply(k, depth, -1);
        }
    }

    /** sign=1 설치, -1 해제. 새로 덮인(해제된) 칸 수 반환 */
    static int apply(int k, int idx, int sign) {
        int r = bombs[idx][0], c = bombs[idx][1];
        int delta = 0;
        for (int d = 0; d < 5; d++) {
            int nr = r + DR[k][d];
            int nc = c + DC[k][d];
            if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
            if (sign == 1) { if (cnt[nr][nc]++ == 0) delta++; }
            else           { if (--cnt[nr][nc] == 0) delta--; }
        }
        return delta;
    }
}