import java.io.*;
import java.util.*;

class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String args[]) throws Exception {
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int answer = 0;
            int x;
            int y;
            int N;
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            while (x <= N && y <= N) {
                if (x > y)
                    y += x;
                else
                    x += y;
                answer++;
            }

            sb.append(answer).append('\n');
        }
        System.out.print(sb);
    }
}