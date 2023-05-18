import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {

    private static List<Integer> coordinates = new ArrayList<>();

    public long solution(int k, int d) {

        for (int i = 0; i <= d / k; i++) {
            coordinates.add(i * k);
        }

        Collections.sort(coordinates);

        long answer = 0;

        for (int i = 0; i < coordinates.size(); i++) {
            answer += getCoordinatesCnt(coordinates.get(i), d);
        }

        return answer;
    }

    private int getCoordinatesCnt(int x, int d) {
        int lo = -1;
        int hi = coordinates.size();

        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;
            int y = coordinates.get(mid);

            if (getDistance(x, y) > d) {
                hi = mid;
            } else {
                lo = mid;
            }
        }

        return hi;
    }

    private double getDistance(int r, int c) {
        return Math.sqrt(Math.pow(r, 2) + Math.pow(c, 2));
    }
}
