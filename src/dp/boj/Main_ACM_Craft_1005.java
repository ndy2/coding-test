package dp.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_ACM_Craft_1005 {

    public static void main(String[] args) throws IOException {

        Solution solution = new Solution();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {

            String[] NK = br.readLine().split(" ");
            int N = Integer.parseInt(NK[0]);
            int K = Integer.parseInt(NK[1]);

            int[] costs = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            List<Integer>[] map = new ArrayList[N + 1];
            for (int n = 1; n <= N; n++) {
                map[n] = new ArrayList<>();
            }

            for (int k = 0; k < K; k++) {
                String[] XY = br.readLine().split(" ");
                int X = Integer.parseInt(XY[0]);
                int Y = Integer.parseInt(XY[1]);

                map[Y].add(X);
            }
            int W = Integer.parseInt(br.readLine());

            solution.solve(N, W, costs, map);
        }
    }

    static class Solution {

        static final int INF = 100_000_000;
        int[] costs;
        List<Integer>[] map;
        int[] dp;

        void solve(int n, int w, int[] costs, List<Integer>[] map) {
            this.costs = costs;
            this.map = map;
            this.dp = initDp(n);

            dfs(w);
            System.out.println(dp[w]);
        }

        int dfs(int y) {
            if (dp[y] != INF) return dp[y];

            int base = costs[y - 1];

            int prevMax = 0;
            for (int x : map[y]) {
                prevMax = Integer.max(prevMax, dfs(x));
            }

            int result = base + prevMax;
            dp[y] = result;
            return result;
        }

        private int[] initDp(int n) {
            int[] dp = new int[n + 1];
            Arrays.fill(dp, 1, n + 1, INF);
            return dp;
        }

    }


}
