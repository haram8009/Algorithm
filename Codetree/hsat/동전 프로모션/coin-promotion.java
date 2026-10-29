import java.util.*;
public class Main {
    static int n;
    static int m;
    static char[] cType;
    static int[] coin;
    static int answer;
    static int MAX_VALUE = 10001;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        cType = new char[n + 1];
        coin = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            cType[i] = sc.next().charAt(0);
            coin[i] = sc.nextInt();
        }
        // Please write your code here.
        int[][] dp = new int[n + 1][m + 1]; // 동전개수
        Arrays.fill(dp[0], MAX_VALUE);
        dp[0][0] = 0;

        int answer = MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            int w = coin[i];
            for (int j = 1; j <= m; j++) {
                if (w > j) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }
                if (cType[i] == 'A') {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - w] + 1);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - w] + 1);
                }
            }
            answer = Math.min(answer, dp[i][m]);
        }

        // System.out.println(Arrays.deepToString(dp));
        System.out.println(answer == MAX_VALUE ? -1 : answer);
    }
}
