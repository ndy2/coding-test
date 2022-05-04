package 수학.programmers;

import java.util.Arrays;

public class Main_삼각달팽이 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(4)));
    }

    static class Solution {

        int[] ans;

        public int[] solution(int n) {
            int len = n * (n + 1) / 2;
            ans = new int[len];

            int numTriangle = (int) Math.ceil((float) n / 3);
            int prevEnd = 0;
            for (int ri = 0; ri < numTriangle; ri++) {
                int start = prevEnd + 1;
                prevEnd = fill(start, 2 * ri, ri, n);
                n -= 3;
            }

            return ans;
        }

        int fill(int start, int r, int c, int n) {

            //왼쪽 벽
            for (int i = 0; i < n; i++) {
                int num = start + i;
                int row = r + i;
                int col = c;

                int idx = row * (row + 1) / 2 + col;
                ans[idx] = num;
            }

            //아래 벽
            for (int i = 0; i < n; i++) {
                int num = start + n - 1 + i;
                int row = r + n - 1;
                int col = c + i;

                int idx = row * (row + 1) / 2 + col;
                ans[idx] = num;
            }

            //오른쪽 벽
            for (int i = 0; i < n - 1; i++) {
                int num = start + (n - 1) * 2 + i;
                int row = r + n - 1 - i;
                int col = c + (n - 1) - i;

                int idx = row * (row + 1) / 2 + col;
                ans[idx] = num;
            }

            return start + (n - 1) * 3 - 1;
        }
    }
}
