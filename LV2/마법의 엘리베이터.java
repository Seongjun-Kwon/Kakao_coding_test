class Solution {
    public int solution(int storey) {

        int answer = 0;

        while (storey > 0) {
            int remainer = storey % 10;

            if (remainer < 5) {
                answer += remainer;
            } else if (remainer == 5) {
                if ((storey / 10) % 10 >= 5) {
                    storey += 5;
                }
                answer += 5;
            } else { // >= 6
                answer += (10 - remainer);
                storey += (10 - remainer);
            }

            storey /= 10;
        }

        return answer;
    }
}
