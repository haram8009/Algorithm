import java.util.*;
import java.util.Map.Entry;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }
        // Please write your code here.
        TreeMap<Integer, Integer> tm = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            int num = numbers[i];
            if (tm.containsKey(num)) continue;
            tm.put(num, i + 1);
        }

        Iterator<Entry<Integer, Integer>> it = tm.entrySet().iterator();
        while (it.hasNext()) {
            Entry<Integer, Integer> entry = it.next();
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }
}
