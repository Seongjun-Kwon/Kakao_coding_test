import java.util.HashSet;

class Solution {
    HashSet<String> numSet = new HashSet<>();

    public int solution(String numbers) {
        for (int i = 1; i <= numbers.length(); i++) {
            boolean[] vis = new boolean[numbers.length()];
            solve(numbers, vis, i, "");
        }

        return numSet.size();
    }

    private void solve(String numbers, boolean[] vis, int length, String curNum) {
        if (curNum.length() == length) {
            if (curNum.charAt(0) == '0') {
                return;
            }
            if (isPrimeNum(curNum)) {
                numSet.add(curNum);
            }

            return;
        }

        for (int i = 0; i < numbers.length(); i++) {
            if (vis[i]) {
                continue;
            }

            vis[i] = true;
            solve(numbers, vis, length, curNum + numbers.charAt(i));
            vis[i] = false;
        }
    }

    private boolean isPrimeNum(String curNum) {
        int num = Integer.parseInt(curNum);

        if (num == 0 || num == 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
