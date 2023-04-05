import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
	Map<Integer, Integer> map = new HashMap<>();

	public int solution(int k, int[] tangerine) {
		int answer = 0;

		for (int i = 0; i < tangerine.length; i++) {
			map.put(tangerine[i], map.getOrDefault(tangerine[i], 0) + 1);
		}

		List<Integer> keys = new ArrayList<>(map.keySet());
		Collections.sort(keys, new TangerineComparator());

		for (int i = 0; i < keys.size(); i++) {
			if (k <= 0) {
				break;
			}
			k -= map.get(keys.get(i));
			answer++;
		}

		return answer;
	}

	class TangerineComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return map.get(o2).compareTo(map.get(o1));
		}
	}
}
