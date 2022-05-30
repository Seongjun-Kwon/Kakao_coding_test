import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private static Queue<Integer> q = new LinkedList<>();

    public int solution(int n, int[][] edge) {
        setGraph(n, edge);

        return bfs(n);
    }

    public void setGraph(int n, int[][] edge) {
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] graphInfo : edge) {
            graph.get(graphInfo[0]).add(graphInfo[1]);
            graph.get(graphInfo[1]).add(graphInfo[0]);
        }
    }

    public int bfs(int n) {
        boolean[] vis = new boolean[n + 1];
        vis[1] = true;
        q.add(1);

        int size = 0;

        while (!q.isEmpty()) {
            size = q.size();

            for (int i = 0; i < size; i++) {
                int cur = q.poll();

                for (int node : graph.get(cur)) {
                    if (vis[node]) {
                        continue;
                    }

                    vis[node] = true;
                    q.add(node);
                }
            }
        }

        return size;
    }
}
