import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] < o2[1] ? -1 : 1;
                } else {
                    return o1[0] < o2[0] ? -1 : 1;
                }
            }
        });

        int answer = solve(jobs);

        return answer;
    }

    private int solve(int[][] jobs) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] <= o2[1] ? -1 : 1;
            }
        });

        int endTime = 0;
        int idx = 0;
        int sum = 0;
        int cnt = 0;

        while (cnt < jobs.length) {
            while (idx < jobs.length && jobs[idx][0] <= endTime) {
                pq.add(jobs[idx++]);
            }

            if (pq.isEmpty()) {
                endTime = jobs[idx][0];
            } else {
                int[] cur = pq.poll();
                endTime += cur[1];
                sum += endTime - cur[0];
                cnt++;
            }
        }

        return sum / jobs.length;
    }
}
