import java.util.Arrays;

class Solution {
    public long solution(int[] sequence) {
        long answer = Integer.MIN_VALUE;
        int one = 1;
        int minusOne = -1;

        int[] seqMultipliedMinusPulse = sequence.clone();
        long[] dpMinus = Arrays.stream(sequence.clone()).mapToLong(i -> (long)i).toArray();

        for (int i = 0; i < sequence.length; i++) {
            if (i == 0) {
                seqMultipliedMinusPulse[i] *= minusOne;
                dpMinus[i] *= minusOne;
                minusOne *= minusOne;
                answer = Math.max(answer, dpMinus[i]);
                continue;
            }

            seqMultipliedMinusPulse[i] *= minusOne;
            dpMinus[i] = Math.max(seqMultipliedMinusPulse[i] + dpMinus[i - 1], seqMultipliedMinusPulse[i]);
            answer = Math.max(answer, dpMinus[i]);
            minusOne *= -1;
        }

        int[] seqMultipliedPlusPulse = sequence.clone();
        long[] dpPlus = Arrays.stream(sequence.clone()).mapToLong(i -> (long)i).toArray();

        for (int i = 0; i < sequence.length; i++) {
            if (i == 0) {
                seqMultipliedPlusPulse[i] *= one;
                dpPlus[i] *= one;
                one *= minusOne;
                answer = Math.max(answer, dpPlus[i]);
                continue;
            }

            seqMultipliedPlusPulse[i] *= one;
            dpPlus[i] = Math.max(seqMultipliedPlusPulse[i] + dpPlus[i - 1], seqMultipliedPlusPulse[i]);
            answer = Math.max(answer, dpPlus[i]);
            one *= -1;
        }

        return answer;
    }
}
