import java.util.Scanner;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = sc.nextInt();
        // Please write your code here.
        int[] arr = new int[n];

        // 수열 사전순 출력
        permutation(arr, 0, k, n);
        System.out.println(sb);
    }

    static void permutation(int[] arr, int depth, int k, int n) {
        if (depth == n) {
            for (int i : arr) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }
        
        for (int i = 1; i <= k; i++) {
            arr[depth]=i;
            permutation(arr, depth+1, k, n);
        }
    }
}