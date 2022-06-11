import java.util.Stack;

class Solution
{
    public int solution(String s) {
        Stack<Character> sta = new Stack<>();
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            if (!sta.isEmpty() && s.charAt(i) == sta.peek()) {
                sta.pop();
            } else {
                sta.push(s.charAt(i));
            }
        }

        if (sta.size() == 0) {
            answer = 1;
        }

        return answer;

//        StringBuffer str = new StringBuffer(s);
//        boolean check = true;
//        int answer = 0;
//
//        while (check) {
//            check = false;
//
//            for (int i = 0; i < str.length() - 1; i++) {
//                if (str.charAt(i) == str.charAt(i + 1)) {
//                    str.delete(i, i + 2);
//                    check = true;
//                }
//            }
//        }
//
//        if (str.length() == 0) {
//            answer = 1;
//        }
    }
}
