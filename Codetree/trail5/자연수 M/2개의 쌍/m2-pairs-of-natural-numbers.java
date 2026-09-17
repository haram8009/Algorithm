import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][];
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            arr[i] = new int[] { x, y };
        }

        Arrays.sort(arr, (a, b) -> {
            return a[1] - b[1];
        });

        int answer = 0;

        int i = 0, j = n - 1, c;
        while (i <= j && i < n && j >= 0) {
            int[] n1 = arr[i];
            int[] n2 = arr[j];
            c = n1[1] + n2[1];
            int sub = Math.abs(n1[0] - n2[0]);

            if (n1[0] > n2[0]) {
                n1[0] = sub;
                j--;
            } else if (n1[0] < n2[0]) {
                n2[0] = sub;
                i++;
            } else {
                i++;
                j--;
            }

            answer = c > answer ? c : answer;
        }

        System.out.println(answer);
    }
}
