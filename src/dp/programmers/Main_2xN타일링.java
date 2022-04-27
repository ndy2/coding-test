package dp.programmers;

public class Main_2xN타일링 {

    public static void main(String[] args) {

        System.out.println(new Solution().solution(60000));
    }

    static class Solution {

        static final int MOD = 1_000_000_007;
        int[] dp;

        public int solution(int n) {
            dp = new int[n+1];
            dp[0] = 1;
            dp[1] = 1;
            if(n==1) return 1;
            for (int i = 2; i <= n; i++) {
                dp[i] = (dp[i-2]% MOD + dp[i-1]% MOD) % MOD;
            }
            return dp[n];
        }
    }
}
