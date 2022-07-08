import java.util.*;

class Solution {
    public int[] solution(String s) {
        HashMap<Integer, Integer> map = new HashMap<>();

        String str = s.replaceAll("[{}]", "");

        StringTokenizer st = new StringTokenizer(str, ",");
        while (st.hasMoreTokens()) {
            int numKey = Integer.parseInt(st.nextToken());

            if (map.containsKey(numKey)) {
                map.put(numKey, map.get(numKey) + 1);
            } else {
                map.put(numKey, 1);
            }
        }

        ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (a, b) -> a.getValue() >= b.getValue() ? -1 : 1);

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            int key = list.get(i).getKey();
            answer[i] = key;
        }

        return answer;
    }
}
