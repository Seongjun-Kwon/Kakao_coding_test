import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    class SequenceIndex implements Comparable<SequenceIndex> {
        int start, end;

        public SequenceIndex(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(SequenceIndex o) {
            if ((this.end - this.start) == (o.end - o.start)) {
                return this.start - o.start;
            } else {
                return (this.end - this.start) - (o.end - o.start);
            }
        }
    }

    private List<SequenceIndex> kSumSequences = new ArrayList<>();

    public int[] solution(int[] sequence, int k) {
        getPartialSequence(sequence, k);
        Collections.sort(kSumSequences);

        SequenceIndex answerIndex = kSumSequences.get(0);

        return new int[] {answerIndex.start, answerIndex.end};
    }

    private void getPartialSequence(int[] sequence, int k) {
        int left = 0;
        int right = 0;
        int sum = sequence[0];

        while (left < sequence.length && right < sequence.length) {
            if (sum == k) {
                kSumSequences.add(new SequenceIndex(left, right));
            }

            if (sum <= k) {
                right++;
                if (right < sequence.length) {
                    sum += sequence[right];
                }
            } else if (sum > k) {
                if (left < sequence.length) {
                    sum -= sequence[left];
                }
                left++;
            }
        }
    }
}
