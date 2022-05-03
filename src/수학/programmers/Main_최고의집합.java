package 수학.programmers;

import java.util.Arrays;

public class Main_최고의집합 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(new Solution().solution(2, 9)));

    }

    static class Solution {
        public int[] solution(int n, int s) {

            int q = s / n;
            int r = s % n;
            if (q == 0) {
                return new int[] {-1};
            }
            int[] ans = new int[n];
            int numQ = n - r;

            for (int i = 0; i < numQ; i++) {
                ans[i] = q;
            }
            for (int i = numQ; i < n; i++) {
                ans[i] = q+1;
            }

            return ans;
        }
    }
}
