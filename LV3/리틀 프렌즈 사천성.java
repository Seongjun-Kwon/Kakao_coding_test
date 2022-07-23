import java.util.*;

class Solution {
    static class Point {
        int r, c;
        int dir;
        int cnt;
        char ch;

        public Point(int r, int c, char ch) {
            this.r = r;
            this.c = c;
            this.ch = ch;
        }

        public Point(int r, int c, int dir, int cnt) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    static char[][] tile;
    static HashMap<Character, Point> map = new HashMap<>();
    static int[][] turnCnt;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public String solution(int m, int n, String[] board) {
        String answer = "";
        List<Character> keyList = init(m, n, board);

        answer = remove(answer, keyList);

        return answer;
    }

    private List<Character> init(int m, int n, String[] board) {
        tile = new char[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tile[i][j] = board[i].charAt(j);

                if (board[i].charAt(j) >= 'A' && board[i].charAt(j) <= 'Z') {
                    map.put(tile[i][j], new Point(i, j, tile[i][j]));
                }
            }
        }

        List<Character> keyList = new ArrayList<>(map.keySet());
        Collections.sort(keyList);

        return keyList;
    }

    private String remove(String answer, List<Character> keyList) {
        while (true) {
            boolean isPossible = false;

            for (Character key : keyList) {
                if (!map.containsKey(key)) {
                    continue;
                }

                Point cur = map.get(key);
                Point curMatch = canRemove(cur);

                if (curMatch.r != -1 && curMatch.c != -1) {
                    tile[cur.r][cur.c] = '.';
                    tile[curMatch.r][curMatch.c] = '.';
                    map.remove(key);
                    isPossible = true;
                    answer += cur.ch;
                    break;
                }
            }

            if (isPossible) {
                continue;
            }
            if (map.isEmpty()) {
                return answer;
            } else {
                return "IMPOSSIBLE";
            }
        }
    }

    private Point canRemove(Point start) {
        Queue<Point> q = new LinkedList<>();
        turnCnt = new int[tile.length][tile[0].length];
        start.dir = -1;
        q.add(start);

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                int nDir = i;
                int nCnt = cur.cnt;

                if (nr < 0 || nc < 0 || nr >= tile.length || nc >= tile[0].length) {
                    continue;
                }
                if (nr == start.r && nc == start.c) {
                    continue;
                }
                if (cur.dir != -1 && cur.dir != nDir) {
                    nCnt = cur.cnt + 1;
                }
                if (nCnt >= 2) {
                    continue;
                }
                if (tile[nr][nc] != '.' && tile[nr][nc] != start.ch) {
                    continue;
                }
                if (tile[nr][nc] == start.ch) {
                    return new Point(nr, nc, tile[nr][nc]);
                }

                q.add(new Point(nr, nc, nDir, nCnt));
            }
        }

        return new Point(-1, -1, ' ');
    }
}
