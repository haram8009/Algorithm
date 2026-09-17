import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static class Node {
        int num;
        int cnt;

        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Node[] arr = new Node[n];
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            arr[i] = new Node(y, x);
        }

        Arrays.sort(arr, (a, b) -> {
            return a.num - b.num;
        });

        int answer = 0;

        int i = 0, j = n - 1, c;
        while (i <= j && i < n && j >= 0) {
            Node n1 = arr[i];
            Node n2 = arr[j];
            c = n1.num + n2.num;
            int sub = Math.abs(n1.cnt - n2.cnt);

            if (n1.cnt > n2.cnt) {
                n1.cnt = sub;
                j--;
            } else if (n1.cnt < n2.cnt) {
                n2.cnt = sub;
                i++;
            } else {
                i++;
                j--;
            }

            answer = c > answer ? c : answer;
        }
//        if (i == j) {
//            if (arr[i].cnt > 1) {
//                answer = (arr[i].num * 2) > answer ? (arr[i].num * 2) : answer;
//
//            }
//        }
        System.out.println(answer);
    }
}
