import java.util.Scanner;
public class Main {
    static int[] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.
        dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        System.out.println(fibo(n));
    }

    static int fibo(int n) {
        if (n <= 1) return dp[n];
        if (dp[n] > 0) return dp[n];
        dp[n] = fibo(n - 1) + fibo(n - 2);
        return dp[n];
    }
}
