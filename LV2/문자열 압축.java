class Solution {
    public int solution(String s) {
        int answer = s.length();

        for (int i = 1; i <= s.length() / 2; i++) { // 압축 단위 길이를 늘인다
            String zipStr = s.substring(0, i); // 압축할 문자열
            int zipCnt = 1; // 현재 압축된 zipStr 의 갯수
            StringBuilder sb = new StringBuilder();

            for (int j = i; j <= s.length(); j += i) {
                String nxt = s.substring(j, j + i > s.length() ? s.length() : j + i);

                if (zipStr.equals(nxt)) { // 다음 문자 nxt가 zipStr 이 같으면 zipCnt++ 해준다
                    zipCnt++;
                } else { // 다음 문자 nxt가 현재 문자와 다른 경우
                    sb.append(zipCnt == 1 ? "" : zipCnt).append(zipStr);
                    zipStr = nxt;
                    zipCnt = 1;
                }
            }

            sb.append(zipStr);

            if (answer > sb.length()) {
                answer = sb.length();
            }
        }

        return answer;
    }
}
