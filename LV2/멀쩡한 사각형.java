class Solution {
    public static long solution(long w, long h) {
        long answer = 0;

        for (int x = 0; x < w; x++) {
            answer += (h * x) / w;
        }

        answer *= 2;

        return answer;
    }
}
