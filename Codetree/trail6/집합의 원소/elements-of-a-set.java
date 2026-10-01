import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[] uf;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        uf = new int[n + 1];
        for (int i = 0; i < uf.length; i++) {
            uf[i] = i;
        }

        for (int i = 0; i < m; i++) {
            int qType = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            // Please write your code here.
            if (qType == 0) {
                union(a, b);
            } else {
                int A = find(a);
                int B = find(b);

                System.out.println(A == B ? 1 : 0);
            }
        }
    }

    public static int find(int x) {
        if (x == uf[x])
            return x;
        return uf[x] = find(uf[x]);
    }

    public static void union(int a, int b) {
        int A = find(a);
        int B = find(b);

        if (A == B) {
            return;
        }

        uf[A] = B;
    }
}