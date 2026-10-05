import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int n;
    static int[] numbers;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        
        numbers = new int[n];
        visited = new boolean[n];
        
        permutation(0);
    }
    
    static void permutation(int depth) {
        if(depth==n) {
            for (int i : numbers) {
                System.out.print(i+" ");
            }
            System.out.println();
        }
        
        for(int i=n-1; i>=0; i--) {
            if(visited[i]) continue;
            
            numbers[depth]=i+1; // i+1로 저장
            visited[i]=true;
            permutation(depth+1);
            visited[i]=false;
        }
    }
}
