import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
        // Please write your code here.

        // ur, ul, dl, dr
        int[] dr = { -1, -1, 1, 1 };
        int[] dc = { 1, -1, -1, 1 };

        int max = 0;
        int this_sum, r, c;

        // k 는 세로길이
        // kk는 가로길이
        for (int k = 1; k < n; k++) {
            for (int kk = 1; kk < n; kk++) {
//                System.out.println("k " + k + " kk " + kk);
                for (int i = k + kk; i < n; i++) {
                    for (int j = k; j < n - kk; j++) {
                        r = i;
                        c = j;
                        this_sum = 0;
//                        System.out.println("start: " + r + c + " ");
                        // 순회
                        for (int d = 0; d < 4; d++) {
//                            System.out.println("d"+d);

                            int l = d % 2 == 0 ? kk : k;
                            for (int m = 0; m < l; m++) {
//                                System.out.println(" l" + l + " m" + m);
                                r += dr[d];
                                c += dc[d];
//                                System.out.println("r" + r + " c" + c );
                                this_sum += grid[r][c];
                            }
                        }

                        max = Math.max(max, this_sum);
//                        if (max == this_sum) {
//                            System.out.println("max: i " + i + " j " + j);
//                        }
                    }
                }

            }
        }

        System.out.println(max);

    }
}
/**
 * 각 방향 당 최소1회 이상순회해야함
 * 
 * 가능한 k = 1 ~ n-1
 * 
 * 순회 가능한 위치: k가 1이면 높이 3=1+1*2 너비 3 (순회시작지점은 맨밑 가운데) k=2 이면 h=5=1+2*2 w=5 k=3 -
 * h=7 w=7
 * 
 * ==> 최소왼쪽에서 k+1 만큼 떨어져야하고 높이 1+2k 보장돼야함
 */