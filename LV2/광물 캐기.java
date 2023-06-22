import java.util.Arrays;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int groupCount = Math.min((minerals.length / 5) + 1, picks[0] + picks[1] + picks[2]);
        int[][] groupFatigue = new int[groupCount][3]; // 각 그룹을 다이아(0), 철(1), 돌(2) 곡괭이로 캤을 때의 피로도

        for (int i = 0; i < minerals.length; i += 5) {
            if (i / 5 == groupCount) {
                break;
            }

            for (int j = i; j < i + 5; j++) {
                if (minerals[j].equals("diamond")) {
                    groupFatigue[i / 5][0] += 1;
                    groupFatigue[i / 5][1] += 5;
                    groupFatigue[i / 5][2] += 25;
                } else if (minerals[j].equals("iron")) {
                    groupFatigue[i / 5][0] += 1;
                    groupFatigue[i / 5][1] += 1;
                    groupFatigue[i / 5][2] += 5;
                } else {
                    groupFatigue[i / 5][0] += 1;
                    groupFatigue[i / 5][1] += 1;
                    groupFatigue[i / 5][2] += 1;
                }

                if (j == minerals.length - 1) {
                    break;
                }
            }
        }

        Arrays.sort(groupFatigue, (o1, o2) -> o2[2] - o1[2]); // 그룹을 돌로 캤을때 피로도가 높은 순으로 정렬

        // 돌로 캤을 때 피로도가 높은 그룹 순서대로 다이아, 철, 돌 순으로 캐야 피로도가 최소가 됨
        for (int i = 0; i < groupCount; i++) {
            if (picks[0] != 0) {
                answer += groupFatigue[i][0];
                picks[0]--;
            } else if (picks[1] != 0) {
                answer += groupFatigue[i][1];
                picks[1]--;
            } else if (picks[2] != 0) {
                answer += groupFatigue[i][2];
                picks[2]--;
            }
        }

        return answer;
    }
}
