package 자료구조.배열.programmers;

import java.util.HashMap;
import java.util.Map;

public class Main_할인행사 {

	public static void main(String[] args) {

		System.out.println(new Solution().solution(
				new String[] {"banana", "apple", "rice", "pork", "pot"},
				new int[] {3, 2, 2, 2, 1},
				new String[] {
						"chicken", "apple", "apple", "banana", "rice",
						"apple", "pork", "banana", "pork", "rice",
						"pot", "banana", "apple", "banana"
				}
		));

	}

	static class Solution {

		private static final int WINDOW_SIZE = 10;
		private Map<String, Integer> wantMap;
		private int answer = 0;


		public int solution(String[] want, int[] number, String[] discount) {

			int nWindow = discount.length - WINDOW_SIZE;
			initWantMap(want, number);

			int idxWindow = 0;
			for (int i = idxWindow; i < WINDOW_SIZE; i++) {
				String cur = discount[i];
				if (wantMap.containsKey(cur)) {
					wantMap.put(cur, wantMap.get(cur) - 1);
				}
			}

			for (int i = idxWindow; i < nWindow; i++) {
				proc();
				String productToAddIfPresent = discount[i];
				if (wantMap.containsKey(productToAddIfPresent)) {
					wantMap.put(productToAddIfPresent, wantMap.get(productToAddIfPresent) + 1);
				}

				String productToReduceIfPresent = discount[i + WINDOW_SIZE];
				if (wantMap.containsKey(productToReduceIfPresent)) {
					wantMap.put(productToReduceIfPresent, wantMap.get(productToReduceIfPresent) - 1);
				}
			}
			proc();

			return answer;
		}

		private void proc() {
			if (canBuyAllWantProducts()) {
				answer++;
			}
		}

		private boolean canBuyAllWantProducts() {
			return wantMap.values().stream().noneMatch(count -> count != 0);
		}

		private void initWantMap(String[] want, int[] number) {
			wantMap = new HashMap<>();
			for (int i = 0; i < want.length; i++) {
				wantMap.put(want[i], number[i]);
			}
		}
	}
}
