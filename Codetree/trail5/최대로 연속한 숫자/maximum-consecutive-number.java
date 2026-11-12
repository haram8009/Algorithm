import java.util.*;

public class Main {
    static TreeSet<Integer> s;
    static TreeMap<Integer, Integer> tm;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] removals = new int[m];
        for (int i = 0; i < m; i++) {
            removals[i] = sc.nextInt();
        }
        // Please write your code here.
        s = new TreeSet<>();
        s.add(-1);
        s.add(n + 1);
        tm = new TreeMap<>();
        tm.put(n + 1, 1);

        for (int num : removals) {
            s.add(num);
            int h = s.higher(num);
            int l = s.lower(num);
            int before = h - l - 1;
            tm.put(before, tm.get(before) - 1);
            if (tm.get(before) <= 0) tm.remove(before);

            int left = num - l - 1;
            tm.put(left, tm.getOrDefault(left, 0) + 1);
            int right = h - num - 1;
            tm.put(right, tm.getOrDefault(right, 0) + 1);

            System.out.println(tm.lastKey());
        }

    }
}
