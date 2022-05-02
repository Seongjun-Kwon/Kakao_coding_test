class Solution {
    public String solution(int n) {
        String answer = "";
        String[] remainder = {"4", "1", "2"};

        while (n > 0) {
            int r = n % 3;
            n /= 3;

            if (r == 0) {
                n--;
            }

            answer = remainder[r] + answer;
        }

        return answer;
    }
}
