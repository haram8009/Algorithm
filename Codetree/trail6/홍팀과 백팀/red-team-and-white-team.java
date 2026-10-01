import java.util.*;

public class Main {
    static Map<Integer, ArrayList<Integer>> graph;
    static int[] parents;
    static int answer;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        // Please write your code here.

        parents = new int[n + 1];
        graph = new HashMap<>();

        int a, b, idx;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();

            graph.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }

        answer = 1;

        // 1 vs -1

        // 먼저 마킹
        // 미방문 노드이면 1로 시작
        for (int node : graph.keySet()) {
            if (parents[node] == 0) {
                mark(node, 1);
            }
        }
        System.out.println(answer);
    }

    static void mark(int node, int flag) {
//        System.out.println("" + node + " " + flag);

        // 입력, 인접 노드 방문
        parents[node] = flag;
        // System.out.println(parents[node]);
        int opp = flag * (-1);
        for (int next : graph.get(node)) {
            if (parents[next] == 0)
                mark(next, opp);
            else if (parents[next]==flag)
            {
                answer=0;
                return;
            }
        }
    }

}