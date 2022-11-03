package 자료구조.배열.programmers;

import java.util.HashMap;
import java.util.Map;

public class Main_롤케이크자르기 {

	public static void main(String[] args) {

		System.out.println(new Solution().solution(new int[] {1, 2, 1, 3, 1, 4, 1, 2}));
		System.out.println(new Solution().solution(new int[] {1, 2, 3, 1, 4}));
	}

	static class Solution {

		public int solution(int[] topping) {
			int answer = 0;
			int len = topping.length;

			Map<Integer, Integer> left = new HashMap<>();
			Map<Integer, Integer> right = new HashMap<>();
			for (int i = 0; i < len; i++) {
				int cur = topping[i];
				right.put(cur, right.getOrDefault(cur, 0) + 1);
			}

			for (int cur : topping) {
				//move right to left
				right.put(cur, right.get(cur) - 1);
				if (right.get(cur) == 0) {
					right.remove(cur);
				}

				left.put(cur, left.getOrDefault(cur, 0) + 1);

				if (right.size() == left.size()) {
					answer++;
				}
			}
			return answer;
		}
	}
}
