import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=n-1; i>=0; i--){
            arr[i] = sc.nextInt();
        }

        for(int a: arr){
            if(a%2==0) System.out.print(a+" ");
        }
        
    }
}