import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int solution(int[][] scores) {

        int[] wanho = scores[0];
        int maxSecondScore = 0;
        List<Integer> scoreSum = new ArrayList<>();

        Arrays.sort(scores, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            } else {
                return o2[0] - o1[0];
            }
        });

        for (int i = 0; i < scores.length; i++) {
            if (scores[i][1] < maxSecondScore) {
                if (Arrays.equals(scores[i], wanho)) {
                    return -1;
                }
            } else {
                scoreSum.add(scores[i][0] + scores[i][1]);

                if (scores[i][1] > maxSecondScore) {
                    maxSecondScore = scores[i][1];
                }
            }
        }

        scoreSum.sort(Comparator.reverseOrder());

        return scoreSum.indexOf(wanho[0] + wanho[1]) + 1;
    }
}
