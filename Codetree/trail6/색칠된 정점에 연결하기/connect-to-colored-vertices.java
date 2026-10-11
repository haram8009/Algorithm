import java.io.UncheckedIOException;
import java.util.*;
public class Main {
    static int[] parents;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();
        int[] coloredVertices = new int[K];
        for (int i = 0; i < K; i++) {
            coloredVertices[i] = sc.nextInt();
        }
        parents = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            parents[i] = i;
        }
        int[][] edges = new int[M][3];
        for (int i = 0; i < M; i++) {
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
            edges[i][2] = sc.nextInt();
        }
        // Please write your code here.
        Arrays.sort(edges, (a, b) -> { return Integer.compare(a[2], b[2]); });

        for (int i = 0; i < K - 1; i++) {
            union(coloredVertices[i], coloredVertices[i + 1]);
        }

        int cnt = K;
        int idx = 0;
        int answer = 0;
        while (cnt < N) {
            if (union(edges[idx][0], edges[idx][1])) {
                answer += edges[idx][2];
                cnt++;
            }
            idx++;
        }

        System.out.println(answer);
    }

    static int find(int x) {
        if (parents[x] == x)
            return x;
        return parents[x] = find(parents[x]);
    }

    static boolean union(int x, int y) {
        int X = find(x);
        int Y = find(y);

        if (X == Y) {
            return false;
        }

        parents[X] = Y;
        return true;
    }
}
