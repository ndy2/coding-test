package dp.programmers;

public class Main_피보나치수 {

    public static void main(String[] args) {

    }

    static class Solution {
        public int solution(int n) {

            int MOD = 1_234_567;
            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 1;

            for (int i = 2; i <= n; i++) {
                dp[i] = (dp[i - 2] + dp[i - 1]) % MOD;
            }

            return dp[n];
        }
    }
}
