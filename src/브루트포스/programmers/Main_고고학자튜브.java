package 브루트포스.programmers;


import java.util.Arrays;

public class Main_고고학자튜브 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[][]{
                {1, 1, 0},
                {0, 0, 1},
                {0, 0, 0}
        }, new int[][]{
                {1, 1, 1}, {1, 1, 0}, {1, 0, 1}
        }));
    }

    static class Solution {
        int N, M;

        int[][] key;

        public boolean solution(int[][] keyIn, int[][] lockIn) {
            N = keyIn.length;
            M = lockIn.length;
            key = keyIn;

            for (int d = 0; d < 4; d++) {

                for (int r = -(N - 1); r < M; r++) {
                    for (int c = -(N - 1); c < M; c++) {

                        int[][] tempLock = copy(lockIn);

                        for (int kr = 0; kr < N; kr++) {
                            for (int kc = 0; kc < N; kc++) {
                                int lr = r + kr;
                                int lc = c + kc;
                                if (!isValid(lr, lc)) {
                                    continue;
                                }

                                if (key[kr][kc] == 1) {
                                    if (tempLock[lr][lc] == 0) {
                                        //열쇠가 빈 공간을 채움
                                        tempLock[lr][lc] = 1;
                                    } else {
                                        //돌기 겹침 -> 루프 탈줄
                                        kr = N;
                                        kc = N;
                                    }
                                }
                            }
                        }

                        if (isDone(tempLock)) {
                            return true;
                        }
                    }
                }

                rotateKey();
            }

            return false;
        }

        boolean isValid(int r, int c) {
            return r >= 0 && r < M && c >= 0 && c < M;
        }

        boolean isDone(int[][] tempLock) {
            for (int r = 0; r < M; r++) {
                for (int c = 0; c < M; c++) {
                    if (tempLock[r][c] == 0) {
                        return false;
                    }
                }
            }
            return true;
        }

        int[][] copy(int[][] lockIn) {
            int[][] ret = new int[M][M];
            for (int r = 0; r < M; r++) {
                for (int c = 0; c < M; c++) {
                    ret[r][c] = lockIn[r][c];
                }
            }
            return ret;
        }

        void rotateKey() {
            int[][] temp = new int[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    temp[c][N - r - 1] = key[r][c];
                }
            }

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    key[r][c] = temp[r][c];
                }
            }
        }
    }
}
