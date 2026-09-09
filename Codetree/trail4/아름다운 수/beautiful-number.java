import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int cnt;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.
        int[] arr = new int[n];
        cnt = 0;

        // 중복순열
        permutation(arr, 0, 4, n);
        System.out.println(cnt);
    }

    static void permutation(int[] arr, int depth, int k, int n) {
        if (depth == n) {
            if (isBeutiful(arr)) {
//                System.out.println(Arrays.toString(arr));

                cnt++;
            }
            return;
        }

        for (int i = 1; i <= k; i++) {
            arr[depth] = i;
            permutation(arr, depth + 1, k, n);
        }
    }

    static boolean isBeutiful(int[] arr) {
//        System.out.println(Arrays.toString(arr));
        int i = 0;
        while (i < arr.length) {
            int target = arr[i];
            int j = i;
            for (int k = 0; k < target; k++, j++) {
                if (j >= arr.length)
                    return false;
                else if (arr[j] != target)
                    return false;
            }
            i += target;
        }
        return true;
    }
}
