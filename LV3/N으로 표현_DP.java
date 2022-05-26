import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int solution(int N, int number) {
        List<Set<Integer>> dp = new ArrayList<>(); // i번째 Set<Integer> 는 i개의 N으로 만들 수 있는 모든 수를 담고 있다.

        for (int i = 0; i < 9; i++) {
            dp.add(new HashSet<>());
        }

        dp.get(1).add(N);

        if (N == number) {
            return 1;
        }

        for (int i = 2; i < 9; i++) {
            for (int j = 1; j < i / 2 + 1; j++) {
                unionSet(dp.get(i), dp.get(j), dp.get(i - j));
                unionSet(dp.get(i), dp.get(i - j), dp.get(j));
            }

            String StrN = Integer.toString(N);
            dp.get(i).add(Integer.parseInt(StrN.repeat(i)));

            for (int dpNum : dp.get(i)) {
                if (dpNum == number) {
                    return i;
                }
            }
        }

        return -1;
    }

    public void unionSet(Set<Integer> union, Set<Integer> a, Set<Integer> b) {
        for (int aNum : a) {
            for (int bNum : b) {
                union.add(aNum + bNum);
                union.add(aNum - bNum);
                union.add(aNum * bNum);

                if (bNum != 0) {
                    union.add(aNum / bNum);
                }
            }
        }
    }
}
