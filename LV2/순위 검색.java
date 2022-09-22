import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class Solution {
    static HashMap<String, List<Integer>> map = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        for (int i = 0; i < info.length; i++) {
            String[] infoArr = info[i].split(" ");
            makeAllInfo(infoArr, "", 0);
        }

        for (String key : map.keySet()) {
            Collections.sort(map.get(key));
        }

        for (int i = 0; i < query.length; i++) {
            String[] queryArr = query[i].replaceAll(" and ", "").split(" ");
            int score = Integer.parseInt(queryArr[1]);

            if (!map.containsKey(queryArr[0])) {
                answer[i] = 0;
            } else {
                answer[i] = binarySearch(queryArr[0], score);
            }
        }

        return answer;
    }

    private void makeAllInfo(String[] info, String str, int cnt) {
        if (cnt == 4) {
            if (!map.containsKey(str)) {
                List<Integer> list = new ArrayList<>();
                map.put(str, list);
            }

            map.get(str).add(Integer.parseInt(info[4]));

            return;
        }

        makeAllInfo(info, str + "-", cnt + 1);
        makeAllInfo(info, str + info[cnt], cnt + 1);
    }

    private int binarySearch(String key, int score) {
        int lo = -1;
        int hi = map.get(key).size();

        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;

            if (map.get(key).get(mid) >= score) {
                hi = mid;
            } else {
                lo = mid;
            }
        }

        return map.get(key).size() - hi;
    }
}
