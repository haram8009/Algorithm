import java.util.Scanner;

public class Main {
	static int n;
	static int cnt;
	static boolean[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		cnt = 0;
		map = new boolean[n][n];

		dfs(0);

		System.out.println(cnt);
	}

	static void dfs(int depth) {
		if (depth == n) {
			cnt++;
			return;
		}

		for (int i = 0; i < n; i++) {
			if (available(depth, i)) {
				map[depth][i] = true;
				dfs(depth + 1);
				map[depth][i] = false;
			}
		}
	}

	static int[] dr = new int[] { -1, -1, 0, 1, 1, 1, -1, 0 };
	static int[] dc = new int[] { 0, 1, 1, 1, 0, -1, -1, -1 };

	static boolean available(int r, int c) {
		int nr, nc;
		for (int d = 0; d < dr.length; d++) {
			nr = r + dr[d];
			nc = c + dc[d];
			while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
				if (map[nr][nc] == true)
					return false;
				nr = nr + dr[d];
				nc = nc + dc[d];
			}
		}
		return true;
	}
}
