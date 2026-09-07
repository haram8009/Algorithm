import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        // Please write your code here.
        Stack<Character> s = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '(') {
                s.push(c);
            } else if (c == ')') {
                // 비어있으면 No
                if (s.isEmpty()) {
//                    System.out.println("No");
                    s.push('n');
                    break;
                }
                // 안비어있으면 pop
                else {
                    s.pop();
                }
            }
        }
        // 안비어있으면 No
        System.out.println(s.isEmpty() ? "Yes" : "No");
    }
}