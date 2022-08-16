import java.util.Stack;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Stack<Character> sta = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);

            if (cur == '(') {
                sta.push(cur);
            } else {
                if (!sta.isEmpty() && sta.peek() == '(') {
                    sta.pop();
                } else {
                    return false;
                }
            }
        }

        if (sta.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
