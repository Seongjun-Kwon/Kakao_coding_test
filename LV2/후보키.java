import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

class Solution {
    boolean[] vis;
    List<String> key = new LinkedList<>(); // 후보키를 담는다

    public int solution(String[][] relation) {
        for (int i = 1; i <= relation[0].length; i++) {
            vis = new boolean[relation[0].length];
            dfs(relation, i, 0, 0);
        }

        return key.size();
    }

    private void dfs(String[][] relation, int maxCnt, int cnt, int idx) { // maxCnt는 Column에서 선택할 항목의 갯수, cnt는 현재 선택한 항목의 갯수, idx는 현재 보고있는 인덱스
        if (cnt == maxCnt) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < relation[0].length; i++) {
                if (vis[i]) {
                    sb.append(i);
                }
            }

            if (notMinimal(sb.toString())) { // 최소성 검증
                return;
            }

            if (notUnique(relation, sb.toString())) { // 유일성 검증
                return;
            }

            key.add(sb.toString());
            return;
        }

        for (int i = idx; i < relation[0].length; i++) {
            vis[i] = true;
            dfs(relation, maxCnt, cnt + 1, i + 1);
            vis[i] = false;
        }
    }

    private boolean notMinimal(String idx) {
        for (String keyTmp : key) {
            int count = 0;

            for (int i = 0; i < keyTmp.length(); i++) {
                if (idx.contains(String.valueOf(keyTmp.charAt(i)))) {
                    count++;
                }
            }

            if (count == keyTmp.length()) {
                return true;
            }
        }

        return false;
    }

    private boolean notUnique(String[][] relation, String idx) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < relation.length; i++) {
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < idx.length(); j++) {
                sb.append(relation[i][Integer.parseInt(String.valueOf(idx.charAt(j)))]);
            }

            if (set.contains(sb.toString())) {
                return true;
            } else {
                set.add(sb.toString());
            }
        }

        return false;
    }
}
