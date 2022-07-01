import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    static Set<Character> operSet;
    static List<Character> operList;
    static List<Character> operListNoRepeat;
    static List<Long> numList;
    static List<Character> order;
    static boolean[] vis;
    static long answer = 0;

    public long solution(String expression) {
        getList(expression);

        order = new ArrayList<>();
        vis = new boolean[operListNoRepeat.size()];

        getPriority();

        return answer;
    }

    public void getList(String expression) {
        operSet = new HashSet<>();
        operList = new ArrayList<>();
        numList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == '+' || c == '-' || c == '*') {
                operSet.add(c);
                operList.add(c);
                numList.add(Long.parseLong(sb.toString()));
                sb = new StringBuilder();
            } else {
                sb.append(c);
            }
        }

        numList.add(Long.parseLong(sb.toString()));
        operListNoRepeat = new ArrayList<>(operSet);
    }

    public void getPriority() {
        if (order.size() == operListNoRepeat.size()) {
            long tmp = calculateByPriority();

            if (answer < tmp) {
                answer = tmp;
            }

            return;
        }

        for (int i = 0; i < operListNoRepeat.size(); i++) {
            if (vis[i]) {
                continue;
            }

            order.add(operListNoRepeat.get(i));
            vis[i] = true;
            getPriority();
            order.remove(order.size() - 1);
            vis[i] = false;
        }
    }

    public long calculateByPriority() {
        List<Long> copyNum = new ArrayList<>(numList);
        List<Character> copyOper = new ArrayList<>(operList);

        for (int i = 0; i < order.size(); i++) {
            for (int j = 0; j < copyOper.size(); j++) {
                if (order.get(i) == copyOper.get(j)) {
                    long calculateResult = calculate(copyNum.get(j), copyNum.get(j + 1), copyOper.get(j));
                    copyNum.set(j, calculateResult);
                    copyNum.remove(j + 1);
                    copyOper.remove(j--);
                }
            }
        }

        return Math.abs(copyNum.get(0));
    }

    public long calculate(long a, long b, char operator) {
        if (operator == '+') {
            return a + b;
        } else if (operator == '-') {
            return a - b;
        } else {
            return a * b;
        }
    }
}
