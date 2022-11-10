package 자료구조.힙.prgrms;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main_디스크컨트롤러 {

	public static void main(String[] args) {

		System.out.println(new Solution().solution(new int[][] {
				{0, 3},
				{1, 9},
				{2, 6}
		}));
	}

	static class Solution {

		public int solution(int[][] jobs) {
			List<int[]>[] jobAtTime = new ArrayList[1001];
			for (int i = 0; i < 1001; i++) {
				jobAtTime[i] = new ArrayList<>();
			}

			for (int[] job : jobs) {
				jobAtTime[job[0]].add(job);
			}

			PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);

			int answer = 0;
			int prevTime = -1;
			int time = 0;
			int jobDone = 0;
			while (jobDone < jobs.length) {
				for (int t = prevTime + 1; t <= Integer.min(time, 1000); t++) {
					q.addAll(jobAtTime[t]);
				}
				if (q.isEmpty()) {
					time++;
				}
				else {
					int[] job = q.poll();
					answer += time - job[0] + job[1];
					prevTime = time;
					time += job[1];
					jobDone++;
				}
			}

			return answer / jobs.length;
		}
	}
}
