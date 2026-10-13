import java.util.*;

public class Main {

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static int n;
    static int m;
    static int[][] grid;

    static List<int[]>[] islands;
    static boolean[][] visited;
    static int cnt;

    static List<Edge>[] graph;
//    static int[][] graph; // i 에서 j 로 가는 최소경로
    static boolean[] selected;

    static class Edge implements Comparable<Edge> {
        int n;
        int w;

        public Edge(int n, int w) {
            this.n = n;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                grid[i][j] = sc.nextInt();
        // Please write your code here.

        islands = new List[n * m]; // 섬과 섬을 구성하는 포인트들 저장
        visited = new boolean[n][m];

        cnt = 0; // 섬 개수 최대 n*m 가 안됨
        // 0. 섬 별로 번호 매기기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j])
                    continue;
                if (grid[i][j] == 0) {
                    visited[i][j] = true;
                    continue;
                }
                cnt++;
                islands[cnt] = new ArrayList<>();
                cntIsland(i, j);
            }
        }

//        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(grid[i]));
//        }

        // 섬개수 맞게 그래프 초기화
        graph = new List[cnt + 1];
        for (int i = 1; i <= cnt; i++) {
            graph[i] = new ArrayList<Edge>();
        }

//        graph = new int[cnt + 1][cnt + 1];
//        for (int i = 1; i < graph.length; i++)
//            Arrays.fill(graph[i], Integer.MAX_VALUE);

        // 1. 각 섬에서 다른 섬까지 걸리는 최소거리 구하기
        for (int i = 1; i <= cnt; i++) {
            for (int j = 1; j <= cnt; j++) {
                if (i == j)
                    continue;
                // i 섬의 모든 점에서 도달 가능한 j 섬의 모든 점에 시도
                for (int[] from : islands[i]) {
                    for (int[] to : islands[j]) {
                        if (from[0] != to[0] && from[1] != to[1])
                            continue;
//                        System.out.println(Arrays.toString(from) + Arrays.toString(to));

                        // 상, 하, 좌, 우 그냥 다 직선 해보기
                        // 중간에 다른 섬 없이 j로 도달 가능한지 판별
                        for (int d = 0; d < 4; d++) {
//                            System.out.println("d: " + d);
                            int nr = from[0] + dr[d];
                            int nc = from[1] + dc[d];

                            int dist = 0;
                            boolean found = false;
                            while (isInRange(nr, nc, n, m)) {
                                if (grid[nr][nc] == 0) {
                                    dist++;
                                } else {
                                    if (grid[nr][nc] == j) {
                                        if (nr == to[0] && nc == to[1]) {
                                            found = true;
//                                            System.out.println("found " + nr + " " + nc);
                                            break;
                                        }else {
                                            break;
                                        }
                                    } else {
                                        break; // 0도 아니고 j도 아니면 도로 놓을 수 없음
                                    }
                                }
                                nr += dr[d];
                                nc += dc[d];
                            }
                            if (found) {
//                                graph[i][j] = Math.min(graph[i][j], dist);
                                graph[i].add(new Edge(j, dist));
                            }
                        }
                    }
                }
            }
        }

        // 그래프대로 엣지 pq에 추가
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));

        int selectedCnt = 0;
        int total = 0;
        selected = new boolean[cnt + 1];
        while (selectedCnt < cnt && !pq.isEmpty()) {
            Edge e = pq.poll();
            if (selected[e.n])
                continue;
            selected[e.n] = true;
            selectedCnt++;
            total += e.w;

            for (Edge ne : graph[e.n]) {
                if (!selected[ne.n]) {
                    pq.add(ne);
                }
            }

        }

        if (selectedCnt != cnt) {
            total = -1;
        }

        System.out.println(total);
    }

    static void cntIsland(int r, int c) {
        visited[r][c] = true;

        grid[r][c] = cnt;
        islands[cnt].add(new int[] { r, c });

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isInRange(nr, nc, n, m) && !visited[nr][nc] && grid[nr][nc] == 1) {
                cntIsland(nr, nc);
            }
        }
    }

    static boolean isInRange(int nr, int nc, int n, int m) {
        return nr >= 0 && nc >= 0 && nr < n && nc < m;
    }

}
