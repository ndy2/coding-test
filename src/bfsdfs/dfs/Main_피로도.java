package bfsdfs.dfs;

public class Main_피로도 {

    public static void main(String[] args) {
        System.out.println("이분탐색.boj.getAnswer : " +
                new Solution().solution(80, new int[][]{
                        {80, 20}, {50, 40}, {30, 1}
                }));
    }


    static class Solution {

        int maxDepth = -1;
        int numDungeon;
        boolean[] visited;

        public int solution(int k, int[][] dungeons) {
            numDungeon = dungeons.length;
            visited = new boolean[numDungeon];

            dfs(0, -1, k, dungeons);
            return maxDepth;
        }

        void dfs(int depth, int idx, int k, int[][] dungeons) {
            maxDepth = Integer.max(depth, maxDepth);
            for (int nextIdx = 0; nextIdx < numDungeon; nextIdx++) {
                if (!visited[nextIdx] && k >= dungeons[nextIdx][0]) {
                    visited[nextIdx] = true;
                    dfs(depth + 1, nextIdx, k - dungeons[nextIdx][1], dungeons);
                    visited[nextIdx] = false;
                }
            }
        }
    }


}
