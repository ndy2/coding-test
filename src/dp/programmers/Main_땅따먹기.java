package dp.programmers;

import static java.lang.Integer.max;

public class Main_땅따먹기 {

    public static void main(String[] args) {

        System.out.println(new Solution().solution(new int[][]{
                {1,2,3,5},
                {5,6,7,8},
                {4,3,2,1}
        }));
    }

    static class Solution {
        int solution(int[][] land) {

            for (int row = 1; row < land.length; row++) {
                land[row][0] += max(max(land[row-1][1], land[row-1][2]), land[row-1][3]);
                land[row][1] += max(max(land[row-1][0], land[row-1][2]), land[row-1][3]);
                land[row][2] += max(max(land[row-1][0], land[row-1][1]), land[row-1][3]);
                land[row][3] += max(max(land[row-1][0], land[row-1][1]), land[row-1][2]);
            }

            int height = land.length - 1;
            return max(max(max(land[height][0], land[height][1]), land[height][2]),land[height][3]);
        }
    }
}
