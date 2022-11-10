package 자료구조.큐.prgrms;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 이분탐색...
 */

public class Main_선입선출스케줄링 {

	public static void main(String[] args) {

		System.out.println(new Solution().solution(6, new int[] {1, 2, 3}));
	}

	static class Solution {

		static class Task implements Comparable<Task> {
			int idx;
			int time;
			int endTime;

			Task(int idx, int time, int endTime) {
				this.idx = idx;
				this.time = time;
				this.endTime = endTime;
			}

			@Override
			public int compareTo(Task o) {
				return endTime == o.endTime ? idx - o.idx : endTime - o.endTime;
			}

			Task next() {
				return new Task(idx, time, endTime + time);
			}
		}

		public int solution(int n, int[] cores) {

			Queue<Task> q = new PriorityQueue<>();
			for (int i = 0; i < cores.length; i++) {
				q.add(new Task(i, cores[i], 0));
			}

			int idx = 0 ;
			for (int i = 0; i < n; i++) {
				Task task = q.poll();
				idx = task.idx;
				q.add(task.next());
			}

			return idx + 1;
		}
	}
}
