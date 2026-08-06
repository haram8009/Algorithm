import java.util.*;

class Solution {
    public int majorityElement(int[] nums) {
        int appear = nums.length / 2;
        int tmp;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            tmp = map.getOrDefault(num, 0) + 1;
            map.put(num, tmp);
            if (tmp > appear) {
                return num;
            }
        }
        return -1;
    }
}