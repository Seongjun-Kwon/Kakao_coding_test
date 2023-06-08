import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int solution(int[] cards) {
        List<Integer> groups = new ArrayList<>();
        boolean[] isOpen = new boolean[cards.length];

        for (int i = 0; i < cards.length; i++) {
            if (isOpen[i]) {
                continue;
            }

            int curCard = i;
            int openBoxCount = 0;

            while (!isOpen[curCard]) {
                isOpen[curCard] = true;
                openBoxCount++;
                curCard = cards[curCard] - 1;
            }

            groups.add(openBoxCount);
        }

        if (groups.size() == 1) {
            return 0;
        }

        groups.sort(Collections.reverseOrder());

        return groups.get(0) * groups.get(1);
    }
}
