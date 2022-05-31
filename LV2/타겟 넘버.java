class Solution {
    static int cnt = 0;

    public int solution(int[] numbers, int target) {
        dfs(0, numbers[0], numbers, target);
        dfs(0, -1 * numbers[0], numbers, target);

        return cnt;
    }

    public void dfs(int idx, int val, int[] numbers, int target) {
        if (idx == numbers.length - 1) {
            if (val == target) {
                cnt++;
            }

            return;
        }

        dfs(idx + 1, val + numbers[idx + 1], numbers, target);
        dfs(idx + 1, val - numbers[idx + 1], numbers, target);
    }
}
