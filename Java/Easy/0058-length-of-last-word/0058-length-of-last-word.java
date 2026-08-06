class Solution {
    public int lengthOfLastWord(String s) {
        s = s.trim();
        char[] chrs = s.toCharArray();
        int cnt=0;
        for (int i=chrs.length-1; i>=0; i--){
            if (chrs[i]==' '){
                break;
            } else{
                cnt++;
            }
        }

        return cnt;
    }
}