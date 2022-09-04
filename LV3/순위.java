class Solution { 
    public int solution(int n, int[][] results) {
        boolean[][] game = new boolean[n][n];
        int answer = 0;

        for (int i = 0; i < results.length; i++) {
            int winner = results[i][0] - 1;
            int loser = results[i][1] - 1;
            game[winner][loser] = true;
        }

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                for (int k = 0; k < n; k++) {
                    if (game[i][j] && game[j][k]) {
                        game[i][k] = true;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            int winOrLose = 0;

            for (int j = 0; j < n; j++) {
                if (game[i][j] || game[j][i]) {
                    winOrLose++;
                }
            }

            if (winOrLose == n - 1) {
                answer++;
            }
        }

        return answer;
    }
}
