import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    private class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	private int[] dr = {0, 1, 0, -1};
	private int[] dc = {1, 0, -1, 0};

	public int[] solution(String[] maps) {
		boolean[][] vis = new boolean[maps.length][maps[0].length()];
		List<Integer> days = new ArrayList<>();

		for (int i = 0; i < maps.length; i++) {
			for (int j = 0; j < maps[0].length(); j++) {
				if (maps[i].charAt(j) == 'X' || vis[i][j]) {
					continue;
				}

				days.add(bfs(maps, vis, i, j));
			}
		}

		if (days.isEmpty()) {
			return new int[] {-1};
		}
		
		Collections.sort(days);
		
		int[] answer = new int[days.size()];
		for (int i = 0; i < days.size(); i++) {
			answer[i] = days.get(i);
		}

		return answer;
	}

	private int bfs(String[] maps, boolean[][] vis, int startR, int startC) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(startR, startC));
		vis[startR][startC] = true;
		int food = maps[startR].charAt(startC) - '0';

		while (!q.isEmpty()) {
			Point cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				if (nr < 0 || nc < 0 || nr >= maps.length || nc >= maps[0].length() || vis[nr][nc]
						|| maps[nr].charAt(nc) == 'X') {
					continue;
				}

				q.add(new Point(nr, nc));
				vis[nr][nc] = true;
				food += maps[nr].charAt(nc) - '0';
			}
		}

		return food;
	}
}
