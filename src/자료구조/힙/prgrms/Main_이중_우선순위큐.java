package 자료구조.힙.prgrms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_이중_우선순위큐 {

	public static void main(String[] args) {

		System.out.println(Arrays.toString(new Solution().solution(
				new String[] {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"}
		)));

	}

	static class Solution {
		public int[] solution(String[] operations) {

			Queue<Integer> minHeap = new PriorityQueue<>();
			Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

			for (String op : operations) {
				String[] line = op.split(" ");
				if (line[0].equals("I")) {
					int x = Integer.parseInt(line[1]);
					minHeap.add(x);
					maxHeap.add(x);
				}
				else if (line[0].equals("D")) {
					if (line[1].equals("1") && !maxHeap.isEmpty()) {
						minHeap.remove(maxHeap.poll());
					}
					else if (line[1].equals("-1") && !minHeap.isEmpty()) {
						maxHeap.remove(minHeap.poll());
					}
				}
			}

			int a = maxHeap.isEmpty() ? 0 : maxHeap.peek();
			int b = minHeap.isEmpty() ? 0 : minHeap.peek();

			return new int[] {a, b};
		}
	}

}
