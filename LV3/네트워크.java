import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int n, int[][] computers) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (computers[i][j] == 1) {
                    list.get(i).add(j);
                }
            }
        }

        int answer = bfs(n, computers, list);

        return answer;
    }

    private int bfs(int n, int[][] computers, ArrayList<ArrayList<Integer>> list) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[n];
        int size = 0;

        for (int i = 0; i < n; i++) {
            if (vis[i]) {
                continue;
            }

            q.add(i);
            vis[i] = true;

            while (!q.isEmpty()) {
                int cur = q.poll();

                for (int j = 0; j < list.get(cur).size(); j++) {
                    int nxt = list.get(cur).get(j);

                    if (vis[nxt] || computers[cur][nxt] == 0) {
                        continue;
                    }

                    q.add(nxt);
                    vis[nxt] = true;
                }
            }

            size++;
        }

        return size;
    }
}
