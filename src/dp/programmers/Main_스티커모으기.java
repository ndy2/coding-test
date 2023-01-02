package dp.programmers;


import static java.lang.Integer.max;

public class Main_스티커모으기 {


    public static void main(String[] args) {
//        System.out.println(new 이분탐색.boj.Solution().solution(new int[] {14, 6, 5, 11, 3, 9, 2, 10}));
//        System.out.println(new 이분탐색.boj.Solution().solution(new int[] {1, 3, 2, 5, 4}));
        System.out.println(new Solution().solution(new int[]{1, 100, 1, 2, 300, 1}));
        System.out.println(new Solution().solution(new int[]{100, 1, 2, 300, 1, 1}));
        System.out.println(new Solution().solution(new int[]{1, 2, 300, 1, 1, 100}));
        System.out.println(new Solution().solution(new int[]{2, 300, 1, 1, 100, 1}));
        System.out.println(new Solution().solution(new int[]{300, 1, 1, 100, 1, 2}));
        System.out.println(new Solution().solution(new int[]{1, 1, 100, 1, 2, 300}));
        System.out.println(new Solution().solution(new int[]{1000}));

    }

    /**
     * dp[0][idx] - 처음것을 사용하고 idx 도 사용한 경우
     * dp[1][idx] - 처음것을 사용하고 idx 는 사용하지 않은 경우
     * dp[2][idx] - 처음것을 사용하지 않고 idx 는 사용한 경우
     * dp[3][idx] - 처음것을 사용하지 않고 idx 도 사용하지 않은 경우
     */
    static class Solution {
        public int solution(int sticker[]) {
            int n = sticker.length;
            if(n==1) return sticker[0];


            int[][] dp = new int[4][n];
            dp[0][0] = sticker[0];
            dp[1][0] = -100;
            dp[2][0] = -100;
            dp[3][0] = 0;


            for (int i = 1; i < n; i++) {
                dp[0][i] = dp[1][i - 1] + sticker[i];
                dp[1][i] = max(dp[0][i - 1], dp[1][i - 1]);
                dp[2][i] = dp[3][i - 1] + sticker[i];
                dp[3][i] = max(dp[2][i - 1], dp[3][i - 1]);
            }

            int a = dp[1][n - 1];
            int b = dp[2][n - 1];
            int c = dp[3][n - 1];

            return max(max(a, b), c);
        }
    }
}
