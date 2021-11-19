import java.util.LinkedList;
import java.util.Queue;

class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

class Solution {
    public boolean BFS(int r, int c, String[] str) {
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r, c));

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr < 0 || nc < 0 || nr >= 5 || nc >= 5 || (nr == r && nc == c))
                    continue;

                int d = Math.abs(nr - r) + Math.abs(nc - c);

                if (str[nr].charAt(nc) == 'P' && d <= 2)
                    return false;
                else if (str[nr].charAt(nc) == 'O' && d < 2)
                    q.add(new Point(nr, nc));
            }
        }

        return true;
    }

    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        for (int i = 0; i < 5; i++) {
            String[] str = places[i];
            boolean check = true;

            for (int j = 0; j < 5 && check; j++) {
                for (int k = 0; k < 5 && check; k++) {
                    if (str[j].charAt(k) == 'P')
                        if (!BFS(j, k, str))
                            check = false;
                }
            }

            if (check)
                answer[i] = 1;
            else
                answer[i] = 0;
        }

        return answer;
    }
}
