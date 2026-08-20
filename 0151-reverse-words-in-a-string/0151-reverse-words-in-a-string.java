import java.util.*;

class Solution {
    void rev(char[] arr, int start, int end) {
        while (start < end) {
            char tmp = arr[start];
            arr[start++] = arr[end];
            arr[end--] = tmp;
        }
    }

    public String reverseWords(String s) {
        char[] arr = s.toCharArray();

        int w = 0, start;

        int i = 0;
        while (i < arr.length) {
            if (arr[i] != ' ') {
                if (w > 0) {
                    arr[w] = ' ';
                    w++;
                }
                start = w;
                while (i < arr.length && arr[i] != ' ') {
                    arr[w++] = arr[i++];
                }
                rev(arr, start, w - 1);
            } else {
                i++;
            }
        }
        rev(arr, 0, w - 1);
        return new String(Arrays.copyOfRange(arr, 0, w));
    }
}