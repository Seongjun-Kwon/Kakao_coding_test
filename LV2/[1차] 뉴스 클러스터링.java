import java.util.HashMap;
import java.util.Map;

class Solution {
    static Map<String, Integer> str1Map = new HashMap<>();
    static Map<String, Integer> str2Map = new HashMap<>();

    public int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        divideString(str1, str2);

        return getJaccard();
    }

    public void divideString(String str1, String str2) {
        for (int i = 0; i < str1.length() - 1; i++) {
            String divided = str1.substring(i, i + 2);

            if (!divided.matches("[a-z]{2,2}")) {
                continue;
            }

            if (str1Map.containsKey(divided)) {
                str1Map.put(divided, str1Map.get(divided) + 1);
            } else {
                str1Map.put(divided, 1);
            }
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            String divided = str2.substring(i, i + 2);

            if (!divided.matches("[a-z]{2,2}")) {
                continue;
            }

            if (str2Map.containsKey(divided)) {
                str2Map.put(divided, str2Map.get(divided) + 1);
            } else {
                str2Map.put(divided, 1);
            }
        }
    }

    public int getJaccard() {
        double intersection = 0;
        double union = 0;

        for (String key : str1Map.keySet()) {
            if (str2Map.containsKey(key)) {
                intersection += Math.min(str1Map.get(key), str2Map.get(key));
                union += Math.max(str1Map.get(key), str2Map.get(key));

                str2Map.put(key, 0);
            } else {
                union += str1Map.get(key);
            }
        }

        for (String key : str2Map.keySet()) {
            union += str2Map.get(key);
        }

        if (intersection == 0 && union == 0) {
            return 65536;
        }

        double jaccardSimilarity = intersection / union;
        int answer = (int) (jaccardSimilarity * 65536);

        return answer;
    }
}
