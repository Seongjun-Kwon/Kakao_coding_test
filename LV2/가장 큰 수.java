import java.util.Arrays;

class Solution {
    public String solution(int[] numbers) {
        String[] numStr = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            numStr[i] = Integer.toString(numbers[i]);
        }

        Arrays.sort(numStr, (a, b) -> {
            return (a + b).compareTo(b + a);
        });

        String answer = "";
        for (int i = numStr.length - 1; i >= 0; i--) {
            answer += numStr[i];
        }

        if (answer.charAt(0) == '0') {
            return "0";
        }

        return answer;
    }
}
