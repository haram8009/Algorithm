import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String s = br.readLine();

        LinkedList<Character> l = new LinkedList<>();
        for (char c : s.toCharArray()) {
            l.add(c);
        }
        ListIterator<Character> it = l.listIterator(l.size());

        for (int i = 0; i < m; i++) {
            String command = br.readLine();
            switch (command.charAt(0)) {
            case 'L':
                if (it.hasPrevious())
                    it.previous();
                break;
            case 'P':
                it.add(command.charAt(2));
                break;
            case 'R':
                if (it.hasNext())
                    it.next();
                break;
            case 'D':
                if (it.hasNext()) {
                    it.next();
                    it.remove();
                }
                break;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (char c : l) {
            sb.append(c);
        }
        System.out.println(sb);
    }
}