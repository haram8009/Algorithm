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
            int LEN = 30;

            String str = br.readLine();
            char start = str.charAt(0);
            for (int i = 1; i < LEN; i++) {
                if (start == str.charAt(i)) {
                    if (str.substring(0, i - 1).equals(str.substring(i, i + (i - 1)))) {
                        answer = i;
                        break;
                    }
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