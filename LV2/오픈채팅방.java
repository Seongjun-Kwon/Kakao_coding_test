import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

class Solution {
public String[] solution(String[] record) {
        HashMap<String, String> map = new HashMap<>();
        ArrayList<String> result = new ArrayList<>();

        for (String str : record) {
            StringTokenizer st = new StringTokenizer(str);
            String cmd = st.nextToken();
            String id = st.nextToken();
            String name = "";

            if (!cmd.equals("Leave")) {
                name = st.nextToken();
            }

            if (cmd.equals("Enter")) {
                map.put(id, name);
                result.add(id + "님이 들어왔습니다.");
            } else if (cmd.equals("Leave")) {
                result.add(id + "님이 나갔습니다.");
            } else { // cmd == "Change"
                map.replace(id, name);
            }
        }

        String[] answer = new String[result.size()];
        int answerIdx = 0;

        for (String str : result) {
            int idx = str.indexOf("님");
            String id = str.substring(0, idx);
            String newName = map.get(id);

            answer[answerIdx++] = str.replace(id, newName);
        }

        return answer;
    }
}
