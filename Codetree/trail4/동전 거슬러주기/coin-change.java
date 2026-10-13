import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[] dp;
    static int[] coin;
    static int NOT_SOLVABLE = Integer.MAX_VALUE - 1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        coin = new int[n];
        for (int i = 0; i < n; i++)
            coin[i] = sc.nextInt();
        // Please write your code here.

        dp = new int[m + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        f(m);

        // System.out.println(Arrays.toString(dp));

        System.out.println(dp[m] != NOT_SOLVABLE ? dp[m] : -1);
    }

    static int f(int num) {
        if (dp[num] != -1)
            return dp[num];
        // num을 거슬러주는 최소 동전수 구하기
        int mincnt = NOT_SOLVABLE; // 오버플로우방지

        for (int i = 0; i < n; i++) {
            if (num >= coin[i]) {
                mincnt = Math.min(mincnt, f(num - coin[i]) + 1);
            }
        }
        dp[num] = mincnt;
        return dp[num];
    }
}
