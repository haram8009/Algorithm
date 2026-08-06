class Solution {
    public String longestCommonPrefix(String[] strs) {
        String prefix = "";
        String tmp = "";

        OUTER: for (int i = 0; i < strs[0].length(); i++) {
            tmp = tmp + strs[0].charAt(i);
            for (String s : strs) {
                if (i >= s.length()) {
                    break OUTER;
                }
                if (s.indexOf(tmp) != 0) {
                    break OUTER;
                }
            }
            prefix = tmp;
        }

        return prefix;
    }
}