import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
	static int n;
	static double e;
	static int[] parents;

	static int[] x, y;
	static Edge[] edges;
	static int edgeSize;

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		long weight;

		public Edge(int from, int to, long weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());

			parents = new int[n];
			edges = new Edge[n * n]; // (1≤N≤1,000)
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
				parents[i] = -1; // size로 초기화
			}
			
			st = new StringTokenizer(br.readLine());
			e = Double.parseDouble(st.nextToken());

			edgeSize = 0;
			// make edges
			// i노드와 j노드 사이 거리 리스트 구하기
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i == j)
						continue;

					long dx = x[i] - x[j];
					long dy = y[i] - y[j];
					long distanceSquared = dx * dx + dy * dy;

					edges[edgeSize++] = new Edge(i, j, distanceSquared);
				}
			}

			edges = Arrays.copyOfRange(edges, 0, edgeSize);
//			System.out.println(Arrays.toString(edges));
			Arrays.sort(edges);

			long amount = 0;
			int cnt = 0;
			for (int i = 0; i < edgeSize; i++) {
				if (cnt == n - 1)
					break;

				Edge edge = edges[i];
				if (union(edge.from, edge.to)) {
					cnt++;
					amount += edge.weight;
				}
			}

			// answer = 환경 부담 세율(E)과 각 해저터널 길이(L)의 제곱의 곱(E * L^2)
			System.out.println("#" + tc + " " + Math.round( e * amount));
		}
	}

	static int find(int x) {
		if (parents[x] < 0) {
			return x;
		}
		return x = find(parents[x]);
	}

	static boolean union(int a, int b) {
		int A = find(a);
		int B = find(b);

		if (A == B)
			return false;

		if (parents[A] < parents[B]) {
			// A size가 더 큼
			parents[A] += parents[B];
			parents[B] = A;
		} else {
			parents[B] += parents[A];
			parents[A] = B;
		}
		return true;
	}
}