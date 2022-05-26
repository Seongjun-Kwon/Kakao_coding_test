class Solution {
    int answer = Integer.MAX_VALUE;

    public int solution(int N, int number) {
        dfs(0, 0, N, number);

        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }

        return answer;
    }

    public void dfs(int cnt, int result, int N, int number) {
        if (cnt > 8) {
            return;
        }

        if (result == number) {
            if (cnt < answer) {
                answer = cnt;
            }

            return;
        }

        int tmp = 0;

        for (int i = 1; i <= 8 - cnt; i++) {
            tmp = 10 * tmp + N;

            dfs(cnt + i, result + tmp, N, number);
            dfs(cnt + i, result - tmp, N, number);
            dfs(cnt + i, result * tmp, N, number);
            dfs(cnt + i, result / tmp, N, number);
        }
    }
}
