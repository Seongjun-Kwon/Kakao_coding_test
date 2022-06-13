import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        int[][] map = new int[rows + 1][columns + 1];
        setMap(rows, columns, map);

        rotate(map, queries, answer);

        return answer;
    }

    public void setMap(int rows, int columns, int[][] map) {
        int startNum = 1;

        for (int i = 1; i < rows + 1; i++) {
            for (int j = 1; j < columns + 1; j++) {
                map[i][j] = startNum++;
            }
        }
    }

    public void rotate(int[][] map, int[][] queries, int[] answer) {
        int idx = 0;

        for (int i = 0; i < queries.length; i++) {
            int x1 = queries[i][0];
            int y1 = queries[i][1];
            int x2 = queries[i][2];
            int y2 = queries[i][3];

            Deque<Integer> deq = new ArrayDeque<>();

            for (int j = y1; j < y2 + 1; j++) {
                deq.add(map[x1][j]);
            }

            for (int k = x1 + 1; k < x2 + 1; k++) {
                deq.add(map[k][y2]);
            }

            for (int m = y2 - 1; m > y1 - 1; m--) {
                deq.add(map[x2][m]);
            }

            for (int n = x2 - 1; n > x1; n--) {
                deq.add(map[n][y1]);
            }

            int last = deq.pollLast();
            deq.addFirst(last);

            findMinValue(deq, answer, idx++);

            updateMap(x1, y1, x2, y2, map, deq);
        }
    }

    public void updateMap(int x1, int y1, int x2, int y2, int[][] map, Deque<Integer> deq) {
        for (int j = y1; j < y2 + 1; j++) {
            map[x1][j] = deq.pollFirst();
        }

        for (int k = x1 + 1; k < x2 + 1; k++) {
            map[k][y2] = deq.pollFirst();
        }

        for (int m = y2 - 1; m > y1 - 1; m--) {
            map[x2][m] = deq.pollFirst();
        }

        for (int n = x2 - 1; n > x1; n--) {
            map[n][y1] = deq.pollFirst();
        }
    }

    public void findMinValue(Deque<Integer> deq, int[] answer, int idx) {
        int min = Integer.MAX_VALUE;

        for (int val : deq) {
            if (val < min) {
                min = val;
            }
        }

        answer[idx] = min;
    }
}
