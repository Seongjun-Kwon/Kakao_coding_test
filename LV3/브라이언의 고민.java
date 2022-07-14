import java.util.ArrayList;

class Solution {
    public String solution(String sentence) {
        String answer = "";
        String word = "";
        ArrayList<String> wordList = new ArrayList<>();
        boolean[] used = new boolean[26];
        boolean isFirstRule = false;
        boolean isSecondRule = false;
        char firstRuleChar = ' ';
        char secondRuleChar = ' ';

        for (int i = 0; i < sentence.length(); i++) {
            char cur = sentence.charAt(i);

            if (isFirstRule && isSecondRule) { // 단어에 규칙1과 규칙2가 모두 적용되어 있는 경우
                if (isUpper(cur)) { // 현재 문자가 대문자
                    word += cur;

                    if (i + 1 == sentence.length()) { // 다음 문자가 없는 경우 -> 즉, 현재 문자가 마지막인 경우면 잘못된 케이스
                        return "invalid";
                    } else if (isUpper(sentence.charAt(i + 1))) { // 다음 문자가 대문자면 잘못된 케이스
                        return "invalid";
                    } else if (sentence.charAt(i + 1) != firstRuleChar && sentence.charAt(i + 1) != secondRuleChar) { // 다음 문자가 소문자인데, 규칙1과 규칙2의 문자가 아니라면 잘못된 케이스
                        return "invalid";
                    }
                } else { // 현재 문자가 소문자면
                    if (cur == secondRuleChar) { // 규칙2에서 사용된 문자와 같으면 단어를 종료
                        isFirstRule = false;
                        isSecondRule = false;
                        used[firstRuleChar - 'a'] = true;
                        used[secondRuleChar - 'a'] = true;
                        firstRuleChar = ' ';
                        secondRuleChar = ' ';
                        wordList.add(word);
                        word = "";
                    } else if (i + 1 == sentence.length()) { // 현재 문자가 규칙2의 문자가 아니고, 다음 문자가 없는 경우
                        return "invalid";
                    } else if (isLower(sentence.charAt(i + 1))) { // 다음 문자도 소문자인 경우, 연속으로 소문자가 나오므로 잘못된 케이스
                        return "invalid";
                    }
                }
            } else if (isFirstRule) { // 단어에 규칙1만 적용되어 있는 경우
                if (isUpper(cur)) { // 현재 문자가 대문자인 경우
                    word += cur;

                    if (i + 1 == sentence.length()) { // 다음 문자가 없으면, 단어를 종료한다.
                        isFirstRule = false;
                        used[firstRuleChar - 'a'] = true;
                        firstRuleChar = ' ';
                        wordList.add(word);
                        word = "";
                    } else if (isUpper(sentence.charAt(i + 1))) { // 다음 문자가 대문자면, 단어를 종료한다.
                        isFirstRule = false;
                        used[firstRuleChar - 'a'] = true;
                        firstRuleChar = ' ';
                        wordList.add(word);
                        word = "";
                    } else if (sentence.charAt(i + 1) != firstRuleChar) { // 다음 문자가 소문자인데, 규칙1에서 사용된 문자가 아닌 경우 단어를 종료한다.
                        isFirstRule = false;
                        used[firstRuleChar - 'a'] = true;
                        firstRuleChar = ' ';
                        wordList.add(word);
                        word = "";
                    }
                } else { // 현재 문자가 소문자인 경우
                    if (i + 1 == sentence.length()) {
                        return "invalid";
                    } else if (isLower(sentence.charAt(i + 1))) {
                        return "invalid";
                    }
                }
            } else if (isSecondRule) { // 단어에 규칙2만 적용되어 있는 경우
                if (isUpper(cur)) { // 현재 문자가 대문자인 경우
                    word += cur;

                    if (i + 1 == sentence.length()) { // 다음 문자가 없으면 잘못된 케이스
                        return "invalid";
                    } else if (isLower(sentence.charAt(i + 1)) && sentence.charAt(i + 1) != secondRuleChar) { // 다음 문자가 소문자인데, 규칙2에 사용된 소문자가 아닌 경우
                        if (sentence.charAt(i - 1) == secondRuleChar) { // 현재 문자 이전 문자가 규칙1의 문자이면, 규칙1과 규칙2가 함께 적용되는 경우
                            if (used[sentence.charAt(i + 1) - 'a']) { // 다음 문자가 이미 사용된 문자이면 잘못된 경우
                                return "invalid";
                            }

                            isFirstRule = true;
                            firstRuleChar = sentence.charAt(i + 1);
                        } else {
                            return "invalid";
                        }
                    }
                } else { // 현재 문자가 소문자인 경우
                    isSecondRule = false;
                    used[secondRuleChar - 'a'] = true;
                    secondRuleChar = ' ';
                    wordList.add(word);
                    word = "";
                }
            } else { // 단어에 규칙1, 규칙2가 모두 적용되어있지 않을 경우 -> 새로운 단어가 시작될 때
                if (isUpper(cur)) { // 현재 문자가 대문자인 경우
                    isFirstRule = true;
                    word += cur;

                    if (i + 1 == sentence.length()) { // 다음 문자가 없을 경우 -> 현재 단어는 한개의 문자임
                        isFirstRule = false;
                        wordList.add(word);
                        word = "";
                    } else if (isUpper(sentence.charAt(i + 1))) { // 다음 문자가 대문자인 경우 -> 현재 단어는 한개의 문자임
                        isFirstRule = false;
                        wordList.add(word);
                        word = "";
                    } else if (isLower(sentence.charAt(i + 1))) { // 다음 문자가 소문자인 경우
                        if (used[sentence.charAt(i + 1) - 'a']) { // 이미 사용된 적 있으면 잘못된 경우
                            return "invalid";
                        }

                        firstRuleChar = sentence.charAt(i + 1);
                        ArrayList<Integer> numNxtCharList = new ArrayList<>(); // 다음 소문자가 규칙1의 소문자일 때, 인덱스를 담아주는 리스트

                        for (int j = i + 1; j < sentence.length(); j++) {
                            if (sentence.charAt(j) == firstRuleChar) {
                                numNxtCharList.add(j);
                            }
                        }

                        if (numNxtCharList.size() == 1) { // 다음 소문자가 규칙1의 소문자인 경우
                            continue;
                        } else if (numNxtCharList.size() >= 3) { // 다음 소문자가 규칙1의 소문자인 경우
                            boolean check = true;

                            for (int j = 0; j < numNxtCharList.size() - 1; j++) {
                                if (numNxtCharList.get(j + 1) - numNxtCharList.get(j) != 2) { // 기호의 인덱스 간격이 2가 아니면 규칙1이 아님
                                    check = false;
                                    break;
                                }
                            }

                            if (check) {
                                continue;
                            }
                        } else { // 다음 소문자가 규칙2 혹은 규칙1+규칙2의 경우임
                            isFirstRule = false;
                            wordList.add(word);
                            word = "";
                        }
                    }
                } else { // 현재 문자가 소문자인 경우 -> 규칙2 시작
                    if (used[cur - 'a']) { // 이미 사용된 적 있으면 잘못된 케이스
                        return "invalid";
                    } else if (i + 1 == sentence.length()) { // 규칙2가 시작되어야하는데 다음 문자가 없으면 잘못된 케이스
                        return "invalid";
                    } else if (isLower(sentence.charAt(i + 1))) { // 규칙2가 시작되어야하는데 다음 문자가 소문자이면 잘못된 케이스
                        return "invalid";
                    } else {
                        isSecondRule = true;
                        secondRuleChar = cur;
                    }
                }
            }
        }

        for (String str : wordList) {
            answer += str + " ";
        }

        answer = answer.substring(0, answer.length() - 1);

        return answer;
    }

    public boolean isLower(char c) {
        if (c >= 'a' && c <= 'z') {
            return true;
        } else {
            return false;
        }
    }

    public boolean isUpper(char c) {
        if (c >= 'A' && c <= 'Z') {
            return true;
        } else {
            return false;
        }
    }
}
