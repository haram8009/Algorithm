import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.
        long MOD = 1_000_000_007L;

        // 타일 채우는 경우의 수
        long[] dp = new long[n + 1];

        dp[0] = 1;
        dp[1] = 2;

        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] * 2 + dp[i - 2] * 3) % MOD;
            for (int j = i - 3; j >= 0; j--) { // n-3 ... 0
                dp[i] = (dp[i] + dp[j] * 2) % MOD;
            }
        }

        System.out.println(dp[n]);
    }

}
