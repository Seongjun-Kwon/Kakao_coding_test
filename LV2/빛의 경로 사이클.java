import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    char[][] map;
    boolean[][][] vis;
    int[] dr = {0, 1, 0, -1};
    int[] dc = {1, 0, -1, 0};
    int routeLength = 0;

    public int[] solution(String[] grid) {
        map = new char[grid.length][grid[0].length()];
        vis = new boolean[grid.length][grid[0].length()][4];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                map[i][j] = grid[i].charAt(j);
            }
        }

        ArrayList<Integer> route = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length(); j++) {
                for (int k = 0; k < 4; k++) {
                    if (vis[i][j][k]) {
                        continue;
                    }

                    routeLength = 0;
                    solve(i, j, k);
                    route.add(routeLength);
                }
            }
        }

        int[] answer = new int[route.size()];
        for (int i = 0; i < route.size(); i++) {
            answer[i] = route.get(i);
        }

        Arrays.sort(answer);

        return answer;
    }

    private void solve(int r, int c, int dir) {
        while (true) {
            if (vis[r][c][dir]) {
                break;
            }

            vis[r][c][dir] = true;
            routeLength++;

            if (map[r][c] == 'L') {
                dir = (dir + 3) % 4;
            } else if (map[r][c] == 'R') {
                dir = (dir + 1) % 4;
            }

            r = r + dr[dir];
            c = c + dc[dir];

            if (r < 0 || c < 0 || r >= map.length || c >= map[0].length) {
                r = (r + map.length) % map.length;
                c = (c + map[0].length) % map[0].length;
            }
        }
    }
}
