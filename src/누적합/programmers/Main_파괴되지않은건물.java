package 누적합.programmers;

public class Main_파괴되지않은건물 {

    public static void main(String[] args) {

        System.out.println(
                new Solution().solution(
                        new int[][]{
                                {5, 5, 5, 5, 5},
                                {5, 5, 5, 5, 5},
                                {5, 5, 5, 5, 5},
                                {5, 5, 5, 5, 5}
                        }, new int[][]{
                                {1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}
                        }
                )
        );
    }

    static class Solution {
        public int solution(int[][] board, int[][] skill) {
            int answer = 0;
            int h = board.length;
            int w = board[0].length;
            int[][] sum = new int[h + 2][w + 2];

            for (int[] ints : skill) {
                int type = ints[0];
                int r1 = ints[1] +1;
                int c1 = ints[2] +1;
                int r2 = ints[3] +1;
                int c2 = ints[4] +1;
                int degree = type == 1 ? -ints[5] : ints[5];

                sum[r1][c1] += degree;
                sum[r2 + 1][c1] -= degree;
                sum[r1][c2 + 1] -= degree;
                sum[r2 + 1][c2 + 1] += degree;

            }

            for (int r = 1; r <= h; r++) {
                for (int c = 1; c <= w; c++) {
                    sum[r][c] += sum[r - 1][c] + sum[r][c - 1] - sum[r - 1][c - 1];
                    answer += board[r-1][c-1] + sum[r][c] >=1 ? 1: 0;
                }
            }

            return answer;
        }
    }
}
