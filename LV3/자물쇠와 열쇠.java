class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int m = key.length;
        int n = lock.length;
        int expandLockSize = 2 * (m - 1) + n;
        int start = key.length - 1;

        for (int i = 0; i < expandLockSize - start; i++) {
            for (int j = 0; j < expandLockSize - start; j++) {
                for (int k = 0; k < 4; k++) {
                    int[][] expandLock = new int[expandLockSize][expandLockSize];

                    for (int a = 0; a < lock.length; a++) {
                        for (int b = 0; b < lock.length; b++) {
                            expandLock[start + a][start + b] = lock[a][b];
                        }
                    }

                    keyRotate(key);

                    for (int c = 0; c < key.length; c++) {
                        for (int d = 0; d < key.length; d++) {
                            expandLock[i + c][j + d] += key[c][d];
                        }
                    }

                    if (isLockFull(expandLock, start, lock.length)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private void keyRotate(int[][] key) {
        int[][] keyTmp = new int[key.length][key.length];
        copyArr(key, keyTmp);

        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                key[i][j] = keyTmp[key.length - 1 - j][i];
            }
        }
    }

    private void copyArr(int[][] from, int[][] to) {
        for (int i = 0; i < from.length; i++) {
            for (int j = 0; j < from.length; j++) {
                to[i][j] = from[i][j];
            }
        }
    }

    private boolean isLockFull(int[][] expandLock, int start, int lockSize) {
        for (int i = 0; i < lockSize; i++) {
            for (int j = 0; j < lockSize; j++) {
                if (expandLock[start + i][start + j] != 1) {
                    return false;
                }
            }
        }

        return true;
    }
}
