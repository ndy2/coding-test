package 백트래킹.programmers;

import java.util.Arrays;

public class Main_NQueen {


    public static void main(String[] args) {
        System.out.println(new Solution().solution(8));
    }

    static class Solution {

        int[][] board;
        int N;
        int answer = 0;

        public int solution(int n) {
            N = n;
            board = new int[n][n];

            for (int c = 0; c < n; c++) {
                dfs(0, c);
            }

            return answer;
        }

        int[] dy = {1, 1, 1};
        int[] dx = {-1, 0, 1};

        void dfs(int r, int c) {
            //check is done;
            if (r == N - 1) answer++;
            else {
                addAttackCount(r, c);
                int tr = r + 1;
                for (int tc = 0; tc < N; tc++) {
                    if (board[tr][tc] == 0) {
                        dfs(tr, tc);
                    }
                }
                subAttackCount(r, c);
            }
        }

        void addAttackCount(int r, int c){
            for (int d = 0; d < 3; d++) {
                board[r][c]++;
                int len = 1;
                while (true) {
                    int ty = r + dy[d] * len;
                    int tx = c + dx[d] * len;
                    if(!isValid(ty, tx)) break;
                    board[ty][tx]++;
                    len++;
                }
            }
        }

        void subAttackCount(int r, int c){
            for (int d = 0; d < 3; d++) {
                board[r][c]--;
                int len = 1;
                while (true) {
                    int ty = r + dy[d] * len;
                    int tx = c + dx[d] * len;
                    if(!isValid(ty, tx)) break;
                    board[ty][tx]--;
                    len++;
                }
            }
        }

        boolean isValid(int r, int c) {
            return r >= 0 && r < N && c >= 0 && c < N;
        }
    }
}
