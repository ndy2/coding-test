package 자료구조.배열.programmers;

public class Main_야간전술보행 {

	public static void main(String[] args) {

		System.out.println(new Solution().solution(10, new int[][] {{3, 4}, {5, 8}}, new int[][] {{2, 5}, {4, 3}}));
	}

	static class Solution {

		int[][] scopeMap;

		public int solution(int distance, int[][] scope, int[][] times) {
			initScopeMap(distance, scope, times);
			for (int pos = 0; pos < distance; pos++) {
				if (scopeMap[pos + 1][0] != 0) {
					int workDays = scopeMap[pos + 1][0];
					int restDays = scopeMap[pos + 1][1];

					if (isWorkAtDay(pos + 1, workDays, restDays)) {
						return pos + 1;
					}
				}
			}

			return distance;
		}

		private boolean isWorkAtDay(int day, int workDays, int restDays) {
			int mod = day % (workDays + restDays);
			return mod <= workDays && mod > 0;
		}

		private void initScopeMap(int distance, int[][] scope, int[][] times) {
			scopeMap = new int[distance + 1][2];
			for (int i = 0; i < scope.length; i++) {
				int a = Integer.min(scope[i][0], scope[i][1]);
				int b = Integer.max(scope[i][0], scope[i][1]);

				for (int j = a; j <= b; j++) {
					scopeMap[j][0] = times[i][0];
					scopeMap[j][1] = times[i][1];
				}

			}
		}
	}
}
