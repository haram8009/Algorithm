import java.io.*;
import java.util.*;

class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String args[]) throws Exception {
        int T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine()); // 덤프횟수
            int answer = 0;

            st = new StringTokenizer(br.readLine());
            int[] arr = new int[100];
            for (int i = 0; i < 100; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int maxIdx = 0;
            int minIdx = 0;
            for (int i = 0; i < N; i++) {
                maxIdx = 0;
                minIdx = 0;
                for (int j = 1; j < 100; j++) {
                    if (arr[j] > arr[maxIdx])
                        maxIdx = j;
                    if (arr[j] < arr[minIdx])
                        minIdx = j;
                }
                
                if (arr[maxIdx] - arr[minIdx] <=1) break;
                
                arr[maxIdx]--;
                arr[minIdx]++;
            }
            
            maxIdx = 0;
            minIdx = 0;
            for (int j = 1; j < 100; j++) {
                if (arr[j] > arr[maxIdx])
                    maxIdx = j;
                if (arr[j] < arr[minIdx])
                    minIdx = j;
            }
            answer = arr[maxIdx] - arr[minIdx];

            sb.append("#")
                    .append(test_case)
                    .append(" ")
                    .append(answer)
                    .append('\n');
        }
        System.out.print(sb);
    }
}