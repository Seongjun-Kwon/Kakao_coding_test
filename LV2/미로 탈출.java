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

	private int[] dr = {0, 1, 0, -1};
	private int[] dc = {1, 0, -1, 0};

	public int solution(String[] maps) {
		int answer = 0;
		boolean canEscape = true;

		for (int i = 0; i < maps.length; i++) {
			for (int j = 0; j < maps[i].length(); j++) {
				if (maps[i].charAt(j) == 'S') {
					int dis = bfs(i, j, 'L', maps);

					if (dis == -1) {
						canEscape = false;
					} else {
						answer += dis;
					}
				}
			}
		}

		for (int i = 0; i < maps.length; i++) {
			for (int j = 0; j < maps[i].length(); j++) {
				if (maps[i].charAt(j) == 'L') {
					int dis = bfs(i, j, 'E', maps);

					if (dis == -1) {
						canEscape = false;
					} else {
						answer += dis;
					}
				}
			}
		}

		if (canEscape) {
			return answer;
		}

		return -1;
	}

	public int bfs(int sr, int sc, char target, String[] maps) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] vis = new boolean[maps.length][maps[0].length()];
		q.add(new Point(sr, sc));
		vis[sr][sc] = true;
		int dis = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			dis++;

			for (int i = 0; i < size; i++) {
				Point cur = q.poll();

				for (int j = 0; j < 4; j++) {
					int nr = cur.r + dr[j];
					int nc = cur.c + dc[j];

					if (nr < 0 || nc < 0 || nr >= maps.length || nc >= maps[0].length() || vis[nr][nc]
							|| maps[nr].charAt(nc) == 'X') {
						continue;
					}
					if (maps[nr].charAt(nc) == target) {
						return dis;
					}

					q.add(new Point(nr, nc));
					vis[nr][nc] = true;
				}
			}
		}

		return -1;
	}
}
