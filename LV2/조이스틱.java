import java.util.LinkedList;
import java.util.Queue;

class Solution {
    class Name {
        String str;
        int idx;
        int cnt;

        public Name(String str, int idx, int cnt) {
            this.str = str;
            this.idx = idx;
            this.cnt = cnt;
        }
    }

    int[] dr = {1, -1};

    public int solution(String name) {
        String repeatA = "A".repeat(name.length());

        return bfs(name, repeatA);
    }

    private int bfs(String name, String curName) {
        Queue<Name> q = new LinkedList<>();
        q.add(new Name(curName, 0, 0));

        while (!q.isEmpty()) {
            Name cur = q.poll();

            if (cur.str.charAt(cur.idx) != name.charAt(cur.idx)) {
                cur.cnt += getUpDownCount(name.charAt(cur.idx));
            }

            cur.str = cur.str.substring(0, cur.idx) + name.charAt(cur.idx) + cur.str.substring(cur.idx + 1);

            if (cur.str.equals(name)) {
                return cur.cnt;
            }

            for (int i = 0; i < 2; i++) {
                int nxtIdx = cur.idx + dr[i];

                if (nxtIdx >= name.length()) {
                    nxtIdx = 0;
                }
                if (nxtIdx < 0) {
                    nxtIdx = name.length() - 1;
                }

                q.add(new Name(cur.str, nxtIdx, cur.cnt + 1));
            }
        }

        return 0;
    }

    private int getUpDownCount(char c) {
        return Math.min(c - 'A', 'Z' - c + 1);
    }
}
