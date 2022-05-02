package dp.programmers;

public class Main_거스름돈 {

    public static void main(String[] args) {

        System.out.println(new Solution().solution(5, new int[]{1, 2, 3}));
    }


    static class Solution {
        static int MOD = 1_000_000_007;

        public int solution(int n, int[] money) {
            int N = money.length;
            int[][] dp = new int[N + 1][n + 1];
            dp[0][0] = 1;

            for (int r = 1; r <= N; r++) {
                for (int c = 0; c <= n; c++) {
                    int val = money[r - 1];
                    dp[r][c] = c < val ? dp[r - 1][c] : (dp[r - 1][c] + dp[r][c - val]) % MOD;
                }
            }

            return dp[N][n];
        }
    }
}
