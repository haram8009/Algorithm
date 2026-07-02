import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        double[] arr = new double[N];

        for(int i=0; i<N; i++){
            arr[i] = sc.nextDouble();
        }

        double sum = 0;
        for(int i=0; i<N; i++){
            sum += arr[i];
        }

        double m = sum/N;
        System.out.println(Math.round(m*10)/10.0);

        if(m>=4.0){
            System.out.println("Perfect");            
        } else if(m>=3.0){
            System.out.println("Good");
        } else {
            System.out.println("Poor");
        }
    }
}