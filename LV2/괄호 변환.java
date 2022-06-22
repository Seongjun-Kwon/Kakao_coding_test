import java.util.Stack;

class Solution {
    public String solution(String p) {
        if (p.equals("")) {
            return p;
        }

        String u = "";
        String v = "";

        for (int i = 0; i < p.length(); i++) {
            if (isBalanced(p.substring(0, i + 1)) && isBalanced(p.substring(i + 1, p.length()))) {
                u = p.substring(0, i + 1);
                v = p.substring(i + 1, p.length());

                break;
            }
        }

        if (isCorrect(u)) {
            return u + solution(v);
        } else {
            u = u.substring(1, u.length() - 1);
            u = u.replace("(", "+");
            u = u.replace(")", "(");
            u = u.replace("+", ")");

            return "(" + solution(v) + ")" + u;
        }
    }

    public boolean isBalanced(String p) {
        int leftCnt = 0;
        int rightCnt = 0;

        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                leftCnt++;
            } else {
                rightCnt++;
            }
        }

        if (leftCnt == rightCnt) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCorrect(String p) {
        Stack<Character> sta = new Stack<>();

        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);

            if (sta.isEmpty()) {
                sta.push(c);
            } else if (c == '(') {
                sta.push(c);
            } else if (c == ')') {
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
