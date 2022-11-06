package 이분탐색.programmers;

public class Main_금과은운반하기 {

	public static void main(String[] args) {

		System.out.println(new Solution().solution(
				10, 10,
				new int[] {100}, new int[] {100}, new int[] {7}, new int[] {10}
		));
	}

	static class Solution {
		public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
			long answer = -1;
			int numCity = g.length;

			long maxRequiredTime = 0;
			for (int i = 0; i < numCity; i++) {
				long mineralAmount = g[i] + s[i];
				long weight = w[i];
				long time = t[i];

				long requiredMoveCnt = (int) Math.ceil((double) mineralAmount / weight);
				long requiredTime = (2 * requiredMoveCnt - 1) * time;

				maxRequiredTime = Math.max(maxRequiredTime, requiredTime);
			}


			long leftTime = 0;
			long rightTime = maxRequiredTime;

			long midTime = -1;

			while (leftTime < rightTime) {
				midTime = (leftTime + rightTime) / 2;

				int total = 0;
				int maxGoldSum = 0;
				int maxSilverSum = 0;

				for (int i = 0; i < numCity; i++) {
					int gold = g[i];
					int silver = s[i];
					int weight = w[i];
					int time = t[i];

					long availableMoveCnt = (midTime / time + 1) / 2;
					long maxMineralAmount = availableMoveCnt * weight;

					total += Math.min(availableMoveCnt * weight, gold + silver);
					maxGoldSum += Math.min(gold, maxMineralAmount);
					maxSilverSum += Math.min(silver, maxMineralAmount);
				}

				boolean check = total >= (a + b) && maxGoldSum >= a && maxSilverSum >= b;

				if(check){
					rightTime = midTime;
				}else{
					leftTime = midTime + 1;
				}
			}
			return midTime;
		}
	}
}
