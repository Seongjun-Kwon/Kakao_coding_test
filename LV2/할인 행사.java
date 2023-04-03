import java.util.HashMap;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
		int answer = 0;
		HashMap<String, Integer> wantMap = new HashMap<>();
		HashMap<String, Integer> discountMap = new HashMap<>();

		for (int i = 0; i < want.length; i++) {
			wantMap.put(want[i], number[i]);
		}

		for (int i = 0; i < discount.length; i++) {
			if (i + 10 > discount.length) {
				break;
			}

			discountMap.clear();

			for (int j = i; j < i + 10; j++) {
				discountMap.put(discount[j], discountMap.getOrDefault(discount[j], 0) + 1);
			}

			if (check(wantMap, discountMap)) {
				answer++;
			}
		}

		return answer;
	}

	private boolean check(HashMap<String, Integer> wantMap, HashMap<String, Integer> discountMap) {
		for (String key : discountMap.keySet()) {
			if (!discountMap.keySet().equals(wantMap.keySet())) {
				return false;
			}
			if (discountMap.get(key) < wantMap.get(key)) {
				return false;
			}
		}
		return true;
	}
}
