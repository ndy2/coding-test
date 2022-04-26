package 재귀.programmers;

import java.util.Arrays;

public class Main_쿼드압축후개수세기 {

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(new Solution().solution(new int[][]{
                        {1, 1, 1, 1, 1, 1, 1, 1},
                        {0, 1, 1, 1, 1, 1, 1, 1},
                        {0, 0, 0, 0, 1, 1, 1, 1},
                        {0, 1, 0, 0, 1, 1, 1, 1},
                        {0, 0, 0, 0, 0, 0, 1, 1},
                        {0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 1, 0, 0, 1},
                        {0, 0, 0, 0, 1, 1, 1, 1}
                }))
        );
    }

    static class Solution {
        int[] answer = new int[2];
        int[][] board;

        public int[] solution(int[][] arr) {
            int n = arr.length;
            dfs(arr, 0, 0, n, n);

            return answer;
        }

        private void dfs(int[][] arr, int r1, int c1, int r2, int c2) {

            if (iscompressable(arr, r1, c1, r2, c2)) {
                System.out.println("compressed\n");
                answer[arr[r1][c1]] += 1;
            } else {
                dfs(arr, r1, c1, (r1 + r2) / 2, (c1 + c2) / 2);
                dfs(arr, r1, (c1 + c2) / 2, (r1 + r2) / 2, c2);
                dfs(arr, (r1 + r2) / 2, c1, r2, (c1 + c2) / 2);
                dfs(arr, (r1 + r2) / 2, (c1 + c2) / 2, r2, c2);
            }

        }

        private boolean iscompressable(int[][] arr, int r1, int c1, int r2, int c2) {
            int target = arr[r1][c1];

            for (int r = r1; r < r2; r++) {
                for (int c = c1; c < c2; c++) {
                    if (target != arr[r][c]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
