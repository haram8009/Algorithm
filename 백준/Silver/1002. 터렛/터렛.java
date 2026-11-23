import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		int x1, y1, r1, x2, y2, r2;
		while (T-- > 0) {
			int answer = 0;
			x1 = sc.nextInt();
			y1 = sc.nextInt();
			r1 = sc.nextInt();
			x2 = sc.nextInt();
			y2 = sc.nextInt();
			r2 = sc.nextInt();

			int d = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
			int sr = (r1 + r2) * (r1 + r2);
			int dr = (r1 - r2) * (r1 - r2);
			if (d == 0 && r1 == r2)
				answer = -1;
			else if (d == sr || (d == dr && d != 0))
				answer = 1;
			else if (dr < d && d < sr)
				answer = 2;

			System.out.println(answer);
		}

	}
}
