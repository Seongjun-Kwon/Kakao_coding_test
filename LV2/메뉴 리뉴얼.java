import java.util.*;

class Solution {
    static Map<String, Integer> map = new HashMap<>();
    static List<String> list = new ArrayList<>();

    public String[] solution(String[] orders, int[] course) {
        for (int i = 0; i < orders.length; i++) {
            char[] arr = orders[i].toCharArray();
            Arrays.sort(arr);
            orders[i] = String.valueOf(arr);
        }

        for (int courseNum : course) {
            for (String order : orders) {
                combination(order, "", 0, courseNum);
            }

            if (!map.isEmpty()) {
                List<Integer> cntList = new ArrayList<>(map.values());
                int max = Collections.max(cntList);

                if (max >= 2) {
                    for (String key : map.keySet()) {
                        if (map.get(key) == max) {
                            list.add(key);
                        }
                    }
                }

                map.clear();
            }
        }

        Collections.sort(list);

        String[] answer = new String[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    public void combination(String order, String result, int idx, int courseNum) {
        if (result.length() == courseNum) {
            if (map.containsKey(result)) {
                map.put(result, map.get(result) + 1);
            } else {
                map.put(result, 1);
            }

            return;
        }

        for (int i = idx; i < order.length(); i++) {
            combination(order, result + order.charAt(i), i + 1, courseNum);
        }
    }
}
