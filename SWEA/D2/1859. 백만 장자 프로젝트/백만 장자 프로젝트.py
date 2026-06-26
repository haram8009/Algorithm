import java.io.*;
import java.util.*;

class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String args[]) throws Exception {
        int T = Integer.parseInt(br.readLine()); // 10 <= T <= 1000

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            long answer = 0; // 가격이 최대 10000, 날짜 수가 1,000,000
            int[] arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int max = arr[N - 1];
            for (int i = N - 2; i >= 0; i--) {
                if (arr[i] > max) {
                    max = arr[i];
                } else {
                    answer += (max - arr[i]);
                }
            }

            sb.append("#")
                    .append(test_case)
                    .append(" ")
                    .append(answer)
                    .append('\n');
        }
        System.out.print(sb);
    }
}