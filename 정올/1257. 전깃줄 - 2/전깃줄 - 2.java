import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        int[][] lines = new int[n][2];
        Map<Integer, Integer> aByB = new HashMap<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lines[i][0] = a;
            lines[i][1] = b;
            aByB.put(b, a);
        }

        Arrays.sort(lines, (a, b) -> Integer.compare(a[0], b[0]));

        int[] prev = new int[500001];
        Arrays.fill(prev, -1);

        // lis[k] is the minimum ending value for an increasing subsequence of length k.
        int[] lis = new int[n + 1];
        Arrays.fill(lis, -1); // lis[0] is the sentinel.
        int length = 0;

        for (int[] line : lines) {
            int b = line[1];
            int position = Arrays.binarySearch(lis, 0, length + 1, b);
            if (position < 0) {
                position = -position - 1;
            }

            prev[b] = lis[position - 1];
            lis[position] = b;
            length = Math.max(length, position);
        }

        Set<Integer> keptB = new HashSet<>();
        int current = lis[length];
        while (current != -1) {
            keptB.add(current);
            current = prev[current];
        }

        int removedCount = n - length;
        int[] removedA = new int[removedCount];
        int index = 0;

        for (Map.Entry<Integer, Integer> entry : aByB.entrySet()) {
            if (!keptB.contains(entry.getKey())) {
                removedA[index++] = entry.getValue();
            }
        }

        Arrays.sort(removedA);
        StringBuilder answer = new StringBuilder();
        answer.append(removedCount).append('\n');
        for (int a : removedA) {
            answer.append(a).append('\n');
        }
        System.out.print(answer);
    }
}
