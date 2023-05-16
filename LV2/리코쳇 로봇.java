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

    public int solution(String[] board) {
        Point start = null;
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int[][] dis = new int[board.length][board[0].length()];
        boolean findStart = false;

        for (int i = 0; i < board.length; i++) {
            if (findStart) {
                break;
            }

            for (int j = 0; j < board[0].length(); j++) {
                if (board[i].charAt(j) == 'R') {
                    start = new Point(i, j);
                    findStart = true;
                    break;
                }
            }
        }

        Queue<Point> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r;
                int nc = cur.c;
                int multiplyUnit = 1;

                while (true) {
                    nr = cur.r + dr[i] * multiplyUnit;
                    nc = cur.c + dc[i] * multiplyUnit;

                    if ((nr < 0 || nc < 0 || nr >= board.length || nc >= board[0].length()) || board[nr].charAt(nc) == 'D') {
                        nr -= dr[i];
                        nc -= dc[i];
                        break;
                    }

                    multiplyUnit++;
                }

                if (dis[nr][nc] > 0 || board[nr].charAt(nc) == 'R') {
                    continue;
                }

                q.add(new Point(nr, nc));
                dis[nr][nc] = dis[cur.r][cur.c] + 1;

                if (board[nr].charAt(nc) == 'G') {
                    return dis[nr][nc];
                }
            }
        }

        return -1;
    }
}
