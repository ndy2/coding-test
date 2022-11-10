package 다익스트라.prgrms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_부대복귀 {

	public static void main(String[] args) {

		System.out.println(Arrays.toString(new Solution().solution(3, new int[][] {{1, 2}, {2, 3}}, new int[] {2, 3}, 1)));
		System.out.println(Arrays.toString(new Solution().solution(5, new int[][] {{1,2},{1,4},{2,4},{2,5},{4,5}}, new int[] {1, 3,5}, 5)));
	}

	static class Solution {
		public int[] solution(int n, int[][] roads, int[] dest, int start) {

			List<Edge>[] roadsList = new ArrayList[n + 1];
			for (int v = 1; v <= n; v++) {
				roadsList[v] = new ArrayList<>();
			}

			for (int[] road : roads) {
				roadsList[road[0]].add(new Edge(road[1], 1));
				roadsList[road[1]].add(new Edge(road[0], 1));
			}
			int[] cost = new int[n + 1];
			int inf = 1_000_000_000;
			Arrays.fill(cost, inf);
			Queue<Edge> q = new PriorityQueue<>();
			q.add(new Edge(start, 0));
			cost[start] = 0;
			while (!q.isEmpty()) {
				Edge cur = q.poll();

				for (Edge next : roadsList[cur.v]) {
					if (cost[next.v] > cost[cur.v] + next.w) {
						cost[next.v] = cost[cur.v] + next.w;
						q.add(new Edge(next.v, cost[cur.v] + next.w));
					}
				}
			}

			int[] answer = new int[dest.length];
			for (int i = 0; i < dest.length; i++) {
				answer[i] = cost[dest[i]] == inf ? -1 : cost[dest[i]];
			}
			return answer;
		}

		static class Edge implements Comparable<Edge> {
			int v, w;

			Edge(int v, int w) {
				this.v = v;
				this.w = w;
			}

			@Override
			public int compareTo(Edge o) {
				return w - o.w;
			}
		}
	}
}
