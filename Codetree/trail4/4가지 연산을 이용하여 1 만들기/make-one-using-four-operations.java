import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int MAXN = 1000000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.
        int[] dp = new int[MAXN + 1];
        int idx = 0;
        dp[0] = 1;
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;
        for (int i = 4; i < MAXN + 1; i++) {
            int answer = Integer.MAX_VALUE;
            answer = Math.min(answer, dp[i - 1] + 1);
            if (i % 2 == 0)
                answer = Math.min(answer, dp[i / 2] + 1);
            if (i % 3 == 0)
                answer = Math.min(answer, dp[i / 3] + 1);
            // answer = Math.min(answer, int);
            if (answer < dp[i - 1]) dp[i - 1] = answer + 1;
            dp[i] = answer;
        }
        System.out.println(dp[n]);

        // PriorityQueue<Node> q = new PriorityQueue<>();
        // q.offer(new Node(n, 0));
        // while (!q.isEmpty()) {
        //     Node node = q.poll();
        //     int num = node.num;
        //     int cnt = node.cnt;
        //     if (num == 1) {
        //         System.out.println(cnt);
        //         return;
        //     }

        //     if (num % 3 == 0) {
        //         q.offer(new Node(num / 3, cnt + 1));
        //     }
        //     if (num % 2 == 0) {
        //         q.offer(new Node(num / 2, cnt + 1));
        //     }
        //     if (num > 1) {
        //         q.offer(new Node(num - 1, cnt + 1));
        //     }
        //     q.offer(new Node(num + 1, cnt + 1));
        // }
    }

    // static class Node implements Comparable<Node> {
    //     int num;
    //     int cnt;

    //     Node(int n, int cnt) {
    //         this.num = n;
    //         this.cnt = cnt;
    //     }

    //     @Override
    //     public int compareTo(Node o) {
    //         return Integer.compare(this.cnt, o.cnt);
    //     }
    // }

    // bfs -> 가장 먼저 1에 도달했을 때 횟수를 출력하고 종료
    // 시간초과

    // -> dp?
}
