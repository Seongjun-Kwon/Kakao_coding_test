import java.util.PriorityQueue;

class Solution {
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static int cnt = 0;

    public int solution(int[] scoville, int K) {
        setQueue(scoville);

        while (pq.size() >= 2 && !moreThanK(K)) {
            findAndMixValue();
        }

        if (pq.peek() < K) {
            cnt = -1;
        }

        return cnt;
    }

    public void setQueue(int[] scoville) {
        for (int scovilleFactor : scoville) {
            pq.add(scovilleFactor);
        }
    }

    public void findAndMixValue() {
        int firstVal = pq.poll();
        int secondVal = pq.poll();
        int mixVal = firstVal + (secondVal * 2);
        pq.add(mixVal);
        cnt++;
    }

    public boolean moreThanK(int K) {
        for (int scovilleFactor : pq) {
            if (scovilleFactor < K) {
                return false;
            }
        }

        return true;
    }
}
