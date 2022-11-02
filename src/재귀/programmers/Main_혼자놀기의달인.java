package 재귀.programmers;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_혼자놀기의달인 {

	public static void main(String[] args) {

		System.out.println(new Solution().solution(new int[] {8, 6, 3, 7, 2, 5, 1, 4}));
		System.out.println(new Solution().solution(new int[] {1, 2, 3, 4}));
		System.out.println(new Solution().solution(new int[] {2, 1, 4, 3}));
		System.out.println(new Solution().solution(new int[] {2, 3, 4, 1}));
		System.out.println(new Solution().solution(new int[] {2, 3, 4, 1}));
	}

	static class Solution {

		public int solution(int[] cards) {

			int size = cards.length;
			boolean[] opened = new boolean[size];
			Queue<Integer> boxSizeQueue = new PriorityQueue<>(Comparator.reverseOrder());

			for (int i = 0; i < size; i++) {
				int openIdx = i;
				int openBoxCount = 0;
				while (!opened[openIdx]) {
					opened[openIdx] = true;
					openBoxCount++;
					openIdx = cards[openIdx] - 1;
				}


				if (openBoxCount != 0) {
					if(openBoxCount == size){
						return 0;
					}
					boxSizeQueue.add(openBoxCount);
				}
			}
			return boxSizeQueue.poll() * boxSizeQueue.poll();
		}
	}
}
