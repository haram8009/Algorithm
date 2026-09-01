import java.util.Scanner;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        LinkedList<Integer> ll = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String command = sc.next();

            int num;
            // num = sc.nextInt();
            // Please write your code here.
            if (command.equals("push_front")) {
                num = sc.nextInt();
                // System.out.println("add" + num);
                ll.addFirst(num);
            } else if (command.equals("push_back")) {
                num = sc.nextInt();
                // System.out.println("add" + num);
                ll.addLast(num);
            } else if (command.equals("pop_front")) {
                System.out.println(ll.pollFirst());
            } else if (command.equals("pop_back")) {
                System.out.println(ll.pollLast());
            } else if (command.equals("size")) {
                System.out.println(ll.size());
            } else if (command.equals("empty")) {
                if (ll.isEmpty()) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            } else if (command.equals("front")) {
                System.out.println(ll.peekFirst());
            } else if (command.equals("back")) {
                System.out.println(ll.peekLast());
            }
        }
    }
}