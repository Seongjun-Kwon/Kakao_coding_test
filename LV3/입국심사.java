import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        long answer = Long.MAX_VALUE;

        Arrays.sort(times);

        long lo = times[0] - 1;
        long hi = (long) times[times.length - 1] * (long) n;

        while (lo + 1 < hi) {
            long mid = (lo + hi) / 2;
            long sum = 0;

            for (int judgeTime : times) {
                sum += mid / judgeTime;
            }

            if (sum >= n) {
                if (answer > mid) {
                    answer = mid;
                }

                hi = mid;
            } else {
                lo = mid;
            }
        }

        return answer;
    }
}
