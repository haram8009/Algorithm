import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc= new Scanner(System.in);

        int answer=0;
        for(int i=0; i<10; i++){
            int val = sc.nextInt();
            answer += val;
        }
        System.out.println(answer);
    }
}