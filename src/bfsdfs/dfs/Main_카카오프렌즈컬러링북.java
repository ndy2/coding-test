package bfsdfs.dfs;

import java.util.Arrays;

public class Main_카카오프렌즈컬러링북 {

    public static void main(String[] args) {

        System.out.println(
                Arrays.toString(new Solution().solution(6, 4, new int[][]{
                        {1, 1, 1, 0},
                        {1, 2, 2, 0},
                        {1, 0, 0, 1},
                        {0, 0, 0, 1},
                        {0, 0, 0, 3},
                        {0, 0, 0, 3}
                }))
        );
    }

    static class Solution {
        int numRegion;
        int maxSize;

        int size;

        int m, n;
        int[][] board;
        boolean[][] visited;

        public int[] solution(int m, int n, int[][] picture) {
            this.m = m;
            this.n = n;
            board = picture;
            visited = new boolean[m][n];
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if(board[r][c] != 0){
                        dfs(r, c, true, board[r][c]);
                    }
                }
            }

            return new int[]{numRegion, maxSize};
        }

        int[] dy = new int[]{0, 0, 1, -1};
        int[] dx = new int[]{1, -1, 0, 0};

        void dfs(int r, int c, boolean out, int color) {
            if (visited[r][c]) {
                return;
            }
            visited[r][c] = true;
            size ++;

            for (int d = 0; d < 4; d++) {
                int ty = r + dy[d];
                int tx = c + dx[d];

                if (isValid(ty, tx) && !visited[ty][tx] && board[ty][tx] == color) {
                    dfs(ty, tx, false, color);
                }
            }

            if (out) {
                numRegion++;
                maxSize = Integer.max(maxSize, size);
                size = 0;
            }
        }

        boolean isValid(int r, int c) {
            return r >= 0 && r < m && c >= 0 && c < n;
        }
    }
}
