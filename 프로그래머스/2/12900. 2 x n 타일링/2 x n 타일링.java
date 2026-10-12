class Solution {
	long[] dp;

	public int solution(int n) {
		long answer = 0;

		// f(n) = f(n-1) + f(n-2)
		// f(n-1) : 한칸을 세로한칸 차지하게 함 (세로 두칸도 궁극적으로 여기 포함됨)
		// f(n-2) : 두칸을 가로두칸으로 차지하게 함

		dp = new long[n + 1];
		dp[0] = 1;
		dp[1] = 1;

		for (int i = 2; i <= n; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
		}

		return (int) dp[n];
	}
}