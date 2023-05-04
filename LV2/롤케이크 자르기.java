import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        Map<Integer, Integer> A = new HashMap<>();
        Map<Integer, Integer> B = new HashMap();

        for (int i = 0; i < topping.length; i++) {
            B.put(topping[i], B.getOrDefault(topping[i], 0) + 1);
        }

        for (int i = 0; i < topping.length; i++) {
            A.put(topping[i], A.getOrDefault(topping[i], 0) + 1);

            if (B.containsKey(topping[i])) {
                B.put(topping[i], B.get(topping[i]) - 1);

                if (B.get(topping[i]) == 0) {
                    B.remove(topping[i]);
                }
            }

            if (A.size() == B.size()) {
                answer++;
            }
        }

        return answer;
    }
}
