import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static class Paper {
        int priority, idx;

        public Paper(int priority, int idx) {
            this.priority = priority;
            this.idx = idx;
        }
    }

    static int answer = 0;

    public int solution(int[] priorities, int location) {
        getOrder(priorities, location);

        return answer;
    }

    private void getOrder(int[] priorities, int location) {
        Queue<Paper> q = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            q.add(new Paper(priorities[i], i));
        }

        while (!q.isEmpty()) {
            Paper cur = q.peek();

            if (isPrior(priorities, cur.idx)) {
                q.poll();
                priorities[cur.idx] = 0;
                answer++;

                if (cur.idx == location) {
                    break;
                }
            } else {
                q.poll();
                q.add(cur);
            }
        }
    }

    private boolean isPrior(int[] priorities, int location) {
        for (int i = 0; i < priorities.length; i++) {
            if (priorities[location] < priorities[i]) {
                return false;
            }
        }

        return true;
    }
}
