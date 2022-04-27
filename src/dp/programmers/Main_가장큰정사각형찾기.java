package dp.programmers;

public class Main_가장큰정사각형찾기 {

    public static void main(String[] args) {
//        System.out.println(
//                new Solution().solution(new int[][]{
//                        {0, 1, 1, 1},
//                        {1, 1, 1, 1},
//                        {1, 1, 1, 1},
//                        {0, 0, 1, 0}
//                })
//        );

//        System.out.println(
//                new Solution().solution(new int[][]{
//                        {1, 1, 1, 1},
//                        {0, 0, 1, 1}
//                })
//        );


        System.out.println(
                new Solution().solution(new int[][]{
                        {0}
                })
        );
    }

    static class Solution {
        public int solution(int[][] board) {
            int h = board.length;
            int w = board[0].length;

            boolean[][] map = new boolean[h+1][w+1];
            for (int r = 0; r < h; r++) {
                for (int c = 0; c < w; c++) {
                    map[r+1][c+1] = board[r][c] == 1;
                }
            }

            int[][] dp = new int[h+1][w+1];
            int maxLen = 0;
            for (int r = 1; r <= h; r++) {
                for (int c = 1; c <= w; c++) {
                    if(!map[r][c]) continue;

                    int up = dp[r -1][c];
                    int left = dp[r][c - 1];

                    if(left==up){
                        int checkR = r - up;
                        int checkC = c - left;
                        dp[r][c] = map[checkR][checkC] ? left +1 : left;
                    }else{
                        dp[r][c] = Integer.min(left, up ) +1;
                    }
                    maxLen = Integer.max(dp[r][c], maxLen);
                }
            }



            return maxLen * maxLen;
        }
    }
}
