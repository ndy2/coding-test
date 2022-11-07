package 다익스트라.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_파티_1238 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int V, E;
	static int start;

	static List<Edge>[] origEdgeMap;
	static List<Edge>[] reverseEdgeMap;
	static int INF = 100_000_000;

	public static void main(String[] args) throws IOException {
		init();
		int[] toParty = dijkstra(start, reverseEdgeMap);
		int[] fromParty = dijkstra(start, origEdgeMap);

		int max = 0;
		for (int v = 1; v <= V; v++) {
			max = Integer.max(max, toParty[v] + fromParty[v]);
		}
		System.out.print(max);
	}


	static int[] dijkstra(int start, List<Edge>[] edgeMap) {
		boolean[] visited = new boolean[V + 1];

		int[] cost = new int[V + 1];
		Arrays.fill(cost, INF);
		cost[start] = 0;

		Queue<Edge> q = new PriorityQueue<>();
		q.add(new Edge(start, 0));

		while (!q.isEmpty()) {
			Edge pos = q.poll();
			int cur = pos.v;

			if (visited[cur]) continue;
			visited[cur] = true;

			for (Edge next : edgeMap[cur]) {
				if (cost[next.v] > cost[cur] + next.w) {
					cost[next.v] = cost[cur] + next.w;
					q.add(new Edge(next.v, cost[cur] + next.w));
				}
			}
		}

		return cost;
	}

	private static void init() throws IOException {
		String[] line = br.readLine().split(" ");
		V = Integer.parseInt(line[0]);
		E = Integer.parseInt(line[1]);
		start = Integer.parseInt(line[2]);

		origEdgeMap = new ArrayList[V + 1];
		reverseEdgeMap = new ArrayList[V + 1];
		for (int v = 1; v <= V; v++) {
			origEdgeMap[v] = new ArrayList<>();
			reverseEdgeMap[v] = new ArrayList<>();
		}

		for (int e = 0; e < E; e++) {
			String[] uvw = br.readLine().split(" ");
			int u = Integer.parseInt(uvw[0]);
			int v = Integer.parseInt(uvw[1]);
			int w = Integer.parseInt(uvw[2]);

			origEdgeMap[u].add(new Edge(v, w));
			reverseEdgeMap[v].add(new Edge(u, w));
		}
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
