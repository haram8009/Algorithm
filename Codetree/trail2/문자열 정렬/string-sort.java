import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        // Please write your code here.
        char[] chars = s.toCharArray();

        Arrays.sort(chars);

        for(char c:chars){
            System.out.print(c);
        }
    }
}