package 자료구조.배열.programmers;

import java.util.HashSet;
import java.util.Set;

public class Main_연속부분수열합의개수 {

	public static void main(String[] args) {

		System.out.println(new Solution().solution(new int[] {7, 9, 1, 1, 4}));
//		System.out.println(new Solution().solution(new int[] {1, 2, 3}));
	}

	static class Solution {
		public int solution(int[] elements) {

			int len = elements.length;
			int[] circle = new int[2 * len];

			for (int i = 0; i < len; i++) {
				circle[i] = elements[i];
				circle[i + len] = elements[i];
			}

			Set<Integer> sums = new HashSet<>();
			for (int start = 0; start < len; start++) {
				int sum = 0;
				for (int dist = 0; dist < len; dist++) {
					sum += circle[start + dist];
					sums.add(sum);
				}
			}
			return sums.size();
		}
	}
}
