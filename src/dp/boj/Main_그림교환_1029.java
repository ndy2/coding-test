package dp.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_그림교환_1029 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        short[][] map = new short[n][n];
        for (int r = 0; r < n; r++) {
            String line = br.readLine();
            for (int c = 0; c < n; c++) {
                map[r][c] = (short) (line.charAt(c) - '0');
            }
        }

        Solution solution = new Solution(n, map);
        solution.dfs(0, (short) 0, 1, 1);
        System.out.println(solution.answer);
    }

    static class Solution {

        int n;
        short[][] map;
        short[][] dp; // dp[i][bit] - i 번 아티스트가 bought 상태일때 구매한 최소 가격

        int answer;

        Solution(int n, short[][] map) {
            this.n = n;
            this.map = map;

            int len = (int) Math.pow(2, n);
            dp = new short[n][len];
            for (int r = 0; r < n; r++) {
                Arrays.fill(dp[r], Short.MAX_VALUE);
            }
        }

        void dfs(int idx, short price, int count, int bit) {
            dp[idx][bit] = price;
            answer = Integer.max(answer, count);
            for (int nextIdx = 0; nextIdx < n; nextIdx++) {
                if ((bit & (1 << nextIdx)) == 1 << nextIdx) continue;
                int nextBit = bit | (1 << nextIdx);
                if (dp[nextIdx][nextBit] <= price) continue;
                if (map[idx][nextIdx] < price) continue;

                dfs(nextIdx, map[idx][nextIdx], count + 1, nextBit);
            }
        }

    }
}
