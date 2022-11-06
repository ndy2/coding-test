package dp.programmers;

public class Main_코딩테스트공부 {

	public static void main(String[] args) {

		System.out.println(new Solution().solution(10, 10, new int[][] {{10, 15, 2, 1, 2}, {20, 20, 3, 3, 4}}));
//		System.out.println(new Solution().solution(0, 0, new int[][] {{0, 0, 2, 1, 2}, {4, 5, 3, 1, 2}, {4, 11, 4, 0, 2}, {10, 4, 0, 4, 2}}));
//		System.out.println(new Solution().solution(19, 19, new int[][] {{20,20,1,1,100},{19,19,200,200,1}}));
//		System.out.println(new Solution().solution(15, 15, new int[][] {{20,20,1,1,100},{19,19,200,4,1}}));
//		System.out.println(new Solution().solution(15, 15, new int[][] {{20,20,1,1,100},{19,19,200,10,1}}));

//		System.out.println(new Solution().solution(15, 15, new int[][] {{20, 20, 1, 1, 100}, {17, 10, 3, 10, 1}}));
//		System.out.println(new Solution().solution(15, 15, new int[][] {{19, 20, 1, 1, 100}, {17, 10, 3, 10, 1}}));
	}

	static class Solution {

		public int solution(int alp, int cop, int[][] problems) {
			int alp_max = 0, cop_max = 0;
			for (int[] problem : problems) {
				alp_max = Integer.max(alp_max, problem[0]);
				cop_max = Integer.max(cop_max, problem[1]);
			}

			int SIZE = 152;
			int[][] dp = new int[SIZE][SIZE];
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					if (i > alp || j > cop) {
						dp[i][j] = 300;
					}
				}
			}

			for (int r = 0; r <= alp_max; r++) {
				for (int c = 0; c <= cop_max; c++) {

					dp[r + 1][c] = Integer.min(dp[r + 1][c], dp[r][c] + 1);
					dp[r][c + 1] = Integer.min(dp[r][c + 1], dp[r][c] + 1);

					for (int[] problem : problems) {
						int alp_req = problem[0];
						int cop_req = problem[1];
						int alp_rwd = problem[2];
						int cop_rwd = problem[3];
						int cost = problem[4];

						if (r >= alp_req && c >= cop_req) {
							int alp_result = Integer.min(r + alp_rwd, alp_max);
							int cop_result = Integer.min(c + cop_rwd, cop_max);

							dp[alp_result][cop_result] = Integer.min(dp[alp_result][cop_result], dp[r][c] + cost);
						}
					}
				}
			}

			return dp[alp_max][cop_max];
		}
	}
}
