import java.util.*;

public class Main {
    static Map<Integer, ArrayList<Integer>> graph;
    static int[] parents;
    static int[] againsts;
    static int answer; // 모순이면 0

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        // Please write your code here.

        parents = new int[n + 1];
        againsts = new int[n + 1];
        answer = 1;

        for (int i = 0; i < n + 1; i++) {
            parents[i] = i;
        }

        int a, b, idx;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();

            beta(a, b);
        }

        System.out.println(parents[1] = answer);
    }

    static int find(int x) {
        if (parents[x] == x)
            return x;
        return parents[x] = find(parents[x]);
    }

    static void union(int a, int b) {
        int A = find(a);
        int B = find(b);
        parents[A] = B;
    }

    static void beta(int x, int y) {
        int X = find(x);
        int Y = find(y);

        if (X == Y) {
            answer = 0;
            return;
        }

        if (againsts[X] != 0) {
            union(againsts[X], Y);
        }

        if (againsts[Y] != 0) {
            union(againsts[Y], X);
        }

        int XX = find(X);
        int YY = find(Y);
        
        againsts[XX] = Y;
        againsts[YY] = X;

    }
}