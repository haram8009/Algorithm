import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[10];
        for (int i=0; i<10; i++){
            arr[i] = sc.nextInt();
        }

        int sum=0;
        int cnt=0;
        for(int n:arr){
            if (n>=250){
                break;
            }
            sum+=n;
            cnt++;
        }

        System.out.print(sum+" ");
        System.out.print(Math.round(((double)sum/cnt)*10)/10.0);
    }
}