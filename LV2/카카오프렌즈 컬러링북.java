import java.util.LinkedList;
import java.util.Queue;

class Solution {
    class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        boolean[][] vis = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 0 || vis[i][j]) {
                    continue;
                }

                Queue<Point> q = new LinkedList<>();
                q.add(new Point(i, j));
                vis[i][j] = true;
                int cnt = 1;

                while (!q.isEmpty()) {
                    Point cur = q.poll();

                    for (int k = 0; k < 4; k++) {
                        int nr = cur.r + dr[k];
                        int nc = cur.c + dc[k];

                        if (nr < 0 || nc < 0 || nr >= m || nc >= n || picture[nr][nc] != picture[cur.r][cur.c] || vis[nr][nc]) {
                            continue;
                        }

                        q.add(new Point(nr, nc));
                        vis[nr][nc] = true;
                        cnt++;
                    }
                }

                if (cnt > maxSizeOfOneArea) {
                    maxSizeOfOneArea = cnt;
                }

                numberOfArea++;
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;

        return answer;
    }
}
