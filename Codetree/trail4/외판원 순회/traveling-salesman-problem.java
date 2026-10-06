import java.util.Scanner;

public class Main {
    static int n;
    static int[] arr;
    static int answer;
    static int[][] cost;

    static void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();
            }
        }
        arr = new int[n + 1]; // 마지막에 다시 0(1번) 노드로 가도록
        answer = Integer.MAX_VALUE;
        // Please write your code here.
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        permutation(1); // 첫번째는 무조건 0(1번)노드인채로 두기

        if (answer == Integer.MAX_VALUE) {
            answer = 0;
        }
        System.out.println(answer);
    }

    static void permutation(int depth) {
        if (depth == n) {
            int sum = 0;
            int from = 0;
            for (int i = 1; i < arr.length; i++) {
                int to = arr[i];
                if (cost[from][to] == 0) {
                    return;
                }
                sum += cost[from][to];
                from = to;
                if (sum > answer) {
                    return; // 이미 최소가 아니면 나가기
                }
            }
            answer = Math.min(answer, sum);
            return;
        }

        for (int i = depth; i < n; i++) {
            swap(depth, i);
            permutation(depth + 1);
            swap(depth, i);
        }
    }
}
