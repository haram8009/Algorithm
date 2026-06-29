class Solution {
    public double solution(int[] numbers) {
        double answer = 0;
        int cnt = 0;
        for (int num: numbers){
            answer += num;
            cnt++;
        }
        return answer/cnt;
    }
}