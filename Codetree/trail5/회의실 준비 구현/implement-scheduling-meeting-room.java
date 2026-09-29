import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int [][] arr = new int[n][2];
        int[] s = new int[n];
        int[] e = new int[n];

        for (int i = 0; i < n; i++) {
            s[i] = sc.nextInt();
            e[i] = sc.nextInt();

            arr[i][0] = s[i];
            arr[i][1] = e[i];
        }
        // Please write your code here.

        Arrays.sort(arr, (a,b)->{
            if (a[1] == b[1])
                return b[0] - a[0];

            return a[1] - b[1];
            });

        int lastTime=0;
        int answer=0;
        for(int i=0; i <n; i++){
            // System.out.println(Arrays.toString(arr[i]));
            if(arr[i][0]>=lastTime){
                answer++;
                lastTime = arr[i][1];
            }
        }

        System.out.println(answer);
    }
}