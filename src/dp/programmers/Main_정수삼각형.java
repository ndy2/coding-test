package dp.programmers;

import java.util.Arrays;

import static java.lang.Integer.max;

public class Main_정수삼각형 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[][]{
                {7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}
        }));
    }

    static class Solution {
        public int solution(int[][] triangle) {
            int h = triangle.length;
            if(h==1) return triangle[0][0];

            int[][] dp = new int[h][];
            for (int r = 0; r < h; r++) {
                dp[r] = new int[r+1];
            }
            dp[0][0] = triangle[0][0];

            for (int r = 1; r < h; r++) {
                dp[r][0] = dp[r-1][0] + triangle[r][0];

                for (int c = 1; c < r; c++) {
                    dp[r][c] = max(dp[r-1][c-1], dp[r-1][c]) + triangle[r][c];
                }

                dp[r][r] = dp[r-1][r-1] + triangle[r][r];
            }

            return Arrays.stream(dp[h-1]).max().getAsInt();
        }
    }
}
