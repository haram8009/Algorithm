import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
	static int n;
	static double e;

	static int[] x, y;

	static List<Node>[] graph;

	static class Node implements Comparable<Node> {
		int to;
		long w;

		public Node(int to, long w) {
			super();
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.w, o.w);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());

			graph = new List[n];

			x = new int[n];
			y = new int[n];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < n; i++) {
				graph[i] = new ArrayList<>();
				for (int j = 0; j < n; j++) {
					long dx = x[i] - x[j];
					long dy = y[i] - y[j];
					long distanceSquared = dx * dx + dy * dy;
					graph[i].add(new Node(j, distanceSquared));
				}
			}

			st = new StringTokenizer(br.readLine());
			e = Double.parseDouble(st.nextToken());

			boolean[] selected = new boolean[n];
			PriorityQueue<Node> pq = new PriorityQueue<>();

			long amount = 0;
			int cnt = 0;

			pq.add(new Node(0, 0));
			while (cnt < n) {
				Node node = pq.poll();
				if (selected[node.to])
					continue;

				selected[node.to] = true;
				amount += node.w;
				cnt++;

				for (Node nnode : graph[node.to]) {
					if (!selected[nnode.to]) {
						pq.offer(nnode);
					}
				}
			}

			// answer = 환경 부담 세율(E)과 각 해저터널 길이(L)의 제곱의 곱(E * L^2)
			System.out.println("#" + tc + " " + Math.round(e * amount));
		}
	}
}