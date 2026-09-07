import java.util.Stack;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        Stack<Integer> stack = new Stack<>();
        
        StringBuilder sb = new StringBuilder();

        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
            case "push":
                stack.push(Integer.parseInt(st.nextToken()));
                break;
            case "size":
                sb.append(stack.size()).append("\n");
                break;
            case "empty":
                sb.append(stack.isEmpty()?1:0).append("\n");
                break;
            case "pop":
                sb.append(stack.pop()).append("\n");
                break;
            case "top":
                sb.append(stack.peek()).append("\n");
                break;
            }
        }

        System.out.println(sb);
    }
}