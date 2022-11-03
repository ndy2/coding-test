package 자료구조.스택.programmers;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main_택배상자 {

	public static void main(String[] args) {

		System.out.println(new Solution().solution(new int[] {4, 3, 1, 2, 5}));
		System.out.println(new Solution().solution(new int[] {5, 4, 3, 2, 1}));
		System.out.println(new Solution().solution(new int[] {1, 2, 3, 4, 5}));
	}

	static class Solution {
		public int solution(int[] order) {

			int len = order.length;
			Queue<Integer> mainBelt = IntStream.range(1, len + 1)
					.boxed()
					.collect(Collectors.toCollection(PriorityQueue::new));
			Stack<Integer> secondBelt = new Stack<>();

			int answer = 0;
			for (int target : order) {
				boolean found = false;
				while (!found) {
					if (!mainBelt.isEmpty() && mainBelt.peek() == target) {
						mainBelt.remove();
						found = true;
						answer++;
					}
					else if (!secondBelt.isEmpty() && secondBelt.peek() == target) {
						secondBelt.pop();
						found = true;
						answer++;
					}
					else if (!mainBelt.isEmpty()) {
						secondBelt.push(mainBelt.remove());
					}
					else {
						return answer;
					}
				}
			}


			return answer;
		}
	}
}
