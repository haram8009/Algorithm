import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[10];
        int i=0;
        for(;i<10;i++){
            int n = sc.nextInt();
            if(n==0){break;}
            arr[i]=n;
        }

        for(int j=i-1; j>=0;j--){
            System.out.print(arr[j]+" ");
        }

    }
}