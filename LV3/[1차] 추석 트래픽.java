import java.text.SimpleDateFormat;
import java.util.Date;

class Solution {
    public int solution(String[] lines) throws Exception {
        int answer = 0;
        long[] startArr = new long[lines.length];
        long[] endArr = new long[lines.length];

        setTimeArray(lines, startArr, endArr);

        for (int i = 0; i < lines.length; i++) {
            int cnt = 0;
            long startTime = endArr[i];
            long endTime = startTime + 1000;

            for (int j = 0; j < lines.length; j++) {
                if (endArr[j] >= startTime && endArr[j] < endTime) {
                    cnt++;
                } else if (startArr[j] >= startTime && startArr[j] < endTime) {
                    cnt++;
                } else if (startArr[j] <= startTime && endArr[j] >= endTime) {
                    cnt++;
                }
            }

            if (cnt > answer) {
                answer = cnt;
            }
        }

        return answer;
    }

    private void setTimeArray(String[] lines, long[] startArr, long[] endArr) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");

        for (int i = 0; i < lines.length; i++) {
            String[] log = lines[i].split(" ");
            Date response = format.parse(log[1]);
            double processTime = Double.parseDouble(log[2].substring(0, log[2].length() - 1));
            long request = (long) (response.getTime() - processTime * 1000 + 1);

            startArr[i] = request;
            endArr[i] = response.getTime();
        }
    }
}
