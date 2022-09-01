import java.util.Arrays;
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

    int[] dr = {0, 1, 0, -1};
    int[] dc = {1, 0, -1, 0};
    int answer = Integer.MAX_VALUE;

    public int solution(int[][] maps) {
        int[][] dis = new int[maps.length][maps[0].length];
        for (int i = 0; i < maps.length; i++) {
            Arrays.fill(dis[i], -1);
        }

        answer = bfs(maps, dis, 0);

        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }

        return answer;
    }

    private int bfs(int[][] maps, int[][] dis, int cnt) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        dis[0][0] = 0;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            cnt++;

            if (cur.r == maps.length - 1 && cur.c == maps[0].length - 1) {
                return dis[cur.r][cur.c] + 1;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr < 0 || nc < 0 || nr >= maps.length || nc >= maps[0].length || maps[nr][nc] == 0 || dis[nr][nc] >= 0) {
                    continue;
                }

                q.add(new Point(nr, nc));
                dis[nr][nc] = dis[cur.r][cur.c] + 1;
            }
        }

        return Integer.MAX_VALUE;
    }
}
