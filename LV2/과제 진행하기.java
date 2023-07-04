import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Solution {
    private class Work implements Comparable<Work> {
        String name;
        int start;
        int playtime;

        public Work(String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }

        @Override
        public int compareTo(Work o) {
            return this.start - o.start;
        }
    }

    public String[] solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        Work[] works = new Work[plans.length];

        for (int i = 0; i < plans.length; i++) {
            String name = plans[i][0];
            LocalTime start = LocalTime.parse(plans[i][1], DateTimeFormatter.ofPattern("HH:mm"));
            int playtime = Integer.parseInt(plans[i][2]);

            works[i] = new Work(name, start.getHour() * 60 + start.getMinute(), playtime);
        }

        Arrays.sort(works);

        Stack<Work> stoppedWorks = new Stack<>();

        for (int i = 0; i < works.length - 1; i++) {
            int now = works[i].start;
            int afterWorkTime = now + works[i].playtime;

            if (afterWorkTime <= works[i + 1].start) {
                answer.add(works[i].name);
                now = afterWorkTime;

                while (!stoppedWorks.isEmpty()) {
                    Work stoppedWork = stoppedWorks.pop();
                    int afterStoppedWorkTime = now + stoppedWork.playtime;

                    if (afterStoppedWorkTime <= works[i + 1].start) {
                        answer.add(stoppedWork.name);
                        now = afterStoppedWorkTime;
                    } else {
                        int restPlaytime = afterStoppedWorkTime - works[i + 1].start;
                        stoppedWorks.push(new Work(stoppedWork.name, stoppedWork.start, restPlaytime));
                        break;
                    }
                }
            } else {
                int restPlaytime = afterWorkTime - works[i + 1].start;
                stoppedWorks.push(new Work(works[i].name, works[i].start, restPlaytime));
            }
        }

        answer.add(works[works.length - 1].name);

        while (!stoppedWorks.isEmpty()) {
            answer.add(stoppedWorks.pop().name);
        }

        return answer.toArray(new String[answer.size()]);
    }
}
