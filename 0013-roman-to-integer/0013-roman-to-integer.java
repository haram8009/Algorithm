import java.util.*;

class Solution {
    public int romanToInt(String s) {
        char[] arr = s.toCharArray();

        Map<Character, Integer> map = new HashMap<>() {
            {
                put('I', 1);
                put('V', 5);
                put('X', 10);
                put('L', 50);
                put('C', 100);
                put('D', 500);
                put('M', 1000);
            }
        };

        int answer = 0;
        char this_chr = arr[0];
        int this_val = map.get(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            int tmp = map.get(arr[i]);
            if (this_chr == arr[i]) {
                this_val += tmp;
            } else {
                this_chr = arr[i];
                if (tmp > this_val) {
                    answer -= this_val;
                } else {
                    answer += this_val;
                }
                this_val = tmp;
            }
        }
        answer += this_val;
        return answer;
    }
}