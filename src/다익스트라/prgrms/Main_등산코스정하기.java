package 다익스트라.prgrms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class Main_등산코스정하기 {

	public static void main(String[] args) {


		Solution solution = new Solution();

//		System.out.println(Arrays.toString(solution.solution(6, new int[][] {
//						{1, 2, 3},
//						{2, 3, 5},
//						{2, 4, 2},
//						{2, 5, 4},
//						{3, 4, 4},
//						{4, 5, 3},
//						{4, 6, 1},
//						{5, 6, 1}
//				}, new int[] {1, 3}, new int[] {5}
//		)));


		System.out.println(Arrays.toString(solution.solution(7, new int[][] {
						{1, 4, 4},
						{1, 6, 1},
						{1, 7, 3},
						{2, 5, 2},
						{3, 7, 4},
						{5, 6, 6}
				}, new int[] {1}, new int[] {2, 3, 4}
		)));
	}

	static class Solution {

		protected static final int INF = 10_000_000 + 1;

		public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

			// pathMap[i] : i 번째 노드에서 이동 할 수 있는 그래프.MST.Edge 의 목록
			List<Edge>[] pathsMap = new ArrayList[n + 1];
			for (int v = 1; v <= n; v++) {
				pathsMap[v] = new ArrayList<>();
			}

			Set<Integer> gatesSet = Arrays.stream(gates).boxed().collect(Collectors.toSet());
			Set<Integer> summitsSet = Arrays.stream(summits).boxed().collect(Collectors.toSet());

			for (int[] path : paths) {
				int i = path[0];
				int j = path[1];
				int w = path[2];
				if (gatesSet.contains(i) || summitsSet.contains(j)) {
					pathsMap[i].add(new Edge(j, w));
				}
				else if (gatesSet.contains(j) || summitsSet.contains(i)) {
					pathsMap[j].add(new Edge(i, w));
				}
				else {
					pathsMap[i].add(new Edge(j, w));
					pathsMap[j].add(new Edge(i, w));
				}
			}

			int[] cost = new int[n + 1];
			Arrays.fill(cost, INF);
			Queue<Edge> pq = new PriorityQueue<>();

			for (int gate : gates) {
				cost[gate] = 0;
				pq.add(new Edge(gate, 0));
			}


			int ansSubmit = 50_000 + 1;
			int minIntensity = INF;
			while (!pq.isEmpty()) {
				Edge cur = pq.poll();
				if(cur.w > cost[cur.v]) continue;

				if (summitsSet.contains(cur.v)) {
					if (cur.w < minIntensity) {
						ansSubmit = cur.v;
						minIntensity = cur.w;
					}
					else if (cur.w == minIntensity && cur.v < ansSubmit) {
						ansSubmit = cur.v;
					}
					continue;
				}

				for (Edge next : pathsMap[cur.v]) {
					if (cost[next.v] > Integer.max(cost[cur.v], next.w)) {
						cost[next.v] = Integer.max(cost[cur.v], next.w);
						pq.add(new Edge(next.v, Integer.max(cost[cur.v], next.w)));
					}
				}
			}

			return new int[] {ansSubmit, minIntensity};
		}


		static class Edge implements Comparable<Edge> {
			int v;
			int w;

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
