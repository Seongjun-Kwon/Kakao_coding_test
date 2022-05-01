class Solution {
    public char[] arr = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    public boolean[] vis = new boolean[8];
    public String friends = "";
    public int answer = 0;

    public int solution(int n, String[] data) {
        makeLine(0, friends, data);

        return answer;
    }

    public void makeLine(int idx, String friends, String[] data) {
        if (idx == 8) {
            if (checkRule(data, friends)) {
                answer++;
            }

            return;
        }

        for (int i = 0; i < 8; i++) {
            if (vis[i]) {
                continue;
            }

            vis[i] = true;
            makeLine(idx + 1, friends + arr[i], data);
            vis[i] = false;
        }
    }

    public boolean checkRule(String[] data, String friends) {
        for (String rule : data) {
            int ruleGiver = friends.indexOf(String.valueOf(rule.charAt(0)));
            int opponent = friends.indexOf(String.valueOf(rule.charAt(2)));
            char cmd = rule.charAt(3);
            int ruleDistance = Integer.parseInt(String.valueOf(rule.charAt(4)));
            int realDistance = Math.abs(opponent - ruleGiver) - 1;

            if (cmd == '=' && realDistance != ruleDistance) {
                return false;
            } else if (cmd == '>' && realDistance <= ruleDistance) {
                return false;
            } else if (cmd == '<' && realDistance >= ruleDistance) {
                return false;
            }
        }

        return true;
    }
}
