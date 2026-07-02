import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr= new int[10];
        int cnt=0;
        double sum = 0;
        for (int i = 0; i < 10; i++) {
            arr[i] = sc.nextInt();
        }

        for(int n:arr){
            if(n==0) break;
            sum+=n;
            cnt++;
        }

        System.out.printf("%d %.1f", (int)sum, sum/cnt);
    }
}
