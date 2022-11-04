package dp.programmers;

import java.util.Arrays;

public class Main_카운트다운 {

	public static void main(String[] args) {

//		System.out.println(Arrays.toString(new Solution().solution(21)));
//		System.out.println(Arrays.toString(new Solution().solution(58)));
//		System.out.println(Arrays.toString(new Solution().solution(120)));
//		System.out.println(Arrays.toString(new Solution().solution(115)));
		System.out.println(Arrays.toString(new Solution().solution(100000)));
//		System.out.println(Arrays.toString(new Solution().solution(2022)));


	}

	static class Solution {

		int dummy = 100_000_000;
		int mod;
		int[][] dp;

		public int[] solution(int target) {
			initDp();
			int q = target / mod;
			int r = target % mod;

			int[] dpAt = getDpAt(r);
			dpAt[0] += q * 10;
			return dpAt;
		}

		private int[] getDpAt(int target) {
			int minDart = Integer.MAX_VALUE;
			int maxSob = Integer.MIN_VALUE;

			if (dp[target][0] != dummy) {
				return dp[target];
			}

			for (int i = 1; i <= target / 2; i++) {
				int a = i;
				int b = target - i;

				int[] aDartAndSob = getDpAt(a);
				int[] bDartAndSob = getDpAt(b);

				int curDart = aDartAndSob[0] + bDartAndSob[0];
				int curSob = aDartAndSob[1] + bDartAndSob[1];

				if (curDart < minDart) {
					minDart = curDart;
					maxSob = curSob;
				}
				else if (curDart == minDart) {
					if (curSob > maxSob) {
						maxSob = curSob;
					}
				}
			}

			dp[target][0] = minDart;
			dp[target][1] = maxSob;

			return dp[target];
		}

		private void initDp() {
			mod = 600;
			dp = new int[mod + 1][2];

			// fill
			for (int i = 1; i <= mod; i++) {
				dp[i][0] = dummy;
			}

			// single
			for (int i = 1; i <= 20; i++) {
				dp[i][0] = 1;
				dp[i][1] = 1;
			}

			//double
			for (int i = 1; i <= 20; i++) {
				if (dp[2 * i][0] == dummy) {
					dp[2 * i][0] = 1;
					dp[2 * i][1] = 0;
				}
			}

			//triple
			for (int i = 1; i <= 20; i++) {
				if (dp[3 * i][0] == dummy) {
					dp[3 * i][0] = 1;
					dp[3 * i][1] = 0;
				}
			}

			//bool
			dp[50][0] = 1;
			dp[50][1] = 1;
		}
	}
}
