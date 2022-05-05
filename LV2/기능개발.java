import java.util.ArrayList;

class Solution {
    ArrayList<Integer> ans = new ArrayList<>();
    int sum = 0;

    public int[] solution(int[] progresses, int[] speeds) {
        boolean[] complete = new boolean[progresses.length];

        progress(progresses, speeds, complete);

        int[] answer = new int[ans.size()];

        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }

        return answer;
    }

    public void progress(int[] progresses, int[] speeds, boolean[] complete) {
        while (sum < progresses.length) {
            int cnt = 0;

            for (int i = 0; i < progresses.length; i++) {
                if (complete[i]) {
                    continue;
                }

                progresses[i] += speeds[i];

                if (progresses[i] >= 100) {
                    boolean possible = true;

                    for (int j = 0; j < i; j++) {
                        if (progresses[j] < 100) {
                            possible = false;
                            break;
                        }
                    }

                    if (possible) {
                        complete[i] = true;
                        cnt++;
                    }
                }
            }

            if (cnt != 0) {
                ans.add(cnt);
                sum += cnt;
            }
        }
    }
}
