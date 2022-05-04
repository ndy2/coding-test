package 이분탐색.programmers;

import java.util.Arrays;

public class Main_징검다리건너기 {

    public static void main(String[] args) {

        System.out.println(new Solution().solution(new int[]
                {2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));
    }

    static class Solution {
        public int solution(int[] stones, int k) {
            int l = Arrays.stream(stones).min().getAsInt();
            int r = Arrays.stream(stones).max().getAsInt();

            int maxTarget = -1;

            while (l <= r) {
                int m = (l + r) / 2;
                if (isAllowed(stones, k, m)) {
                    maxTarget = Integer.max(m, maxTarget);
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }

            return maxTarget;
        }

        //연속된 target 미만의 자연수가 최대 k-1 개야함
        boolean isAllowed(int[] stones, int k, int target) {
            if(target == 4) {
                System.out.println();
            }
            int combo = 0;

            for (int stone : stones) {
                if (stone < target) {
                    combo++;
                    if (combo == k) return false;
                } else {
                    combo = 0;
                }
            }
            return true;
        }
    }
}
