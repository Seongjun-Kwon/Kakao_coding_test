import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> sums = new HashSet<>();

        for (int i = 0; i < elements.length; i++) {
            int sum = 0;

            for (int j = 0; j < elements.length; j++) {
                sum += elements[(i + j) % elements.length];
                sums.add(sum);
            }
        }

        return sums.size();
    }
}
