package 다익스트라.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_특정한_최단_경로 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int V, E;
	static int a, b;
	static List<Edge>[] edgeMap;
	static int INF = 100_000_000;

	public static void main(String[] args) throws IOException {

		init();
		int[] costOne = dijkstra(1);
		int[] costA = dijkstra(a);
		int[] costB = dijkstra(b);

		int min_1toa = costOne[a];
		int min_atoV = costA[V];
		int min_atob = costA[b];
		int min_1tob = costOne[b];
		int min_btoV = costB[V];

		int min_1toV_via_a_and_b = min_atob + Integer.min(min_1toa + min_btoV, min_1tob + min_atoV);
		int answer = min_1toV_via_a_and_b >= INF ? -1 : min_1toV_via_a_and_b;
		System.out.print(answer);
	}

	static int[] dijkstra(int start) {
		int[] cost = new int[V + 1];
		Arrays.fill(cost, INF);
		cost[start] = 0;
		boolean[] visited = new boolean[V + 1];

		Queue<Edge> q = new PriorityQueue<>();
		q.add(new Edge(start, 0));

		while (!q.isEmpty()) {
			Edge cur = q.poll();

			if (visited[cur.v]) continue;
			visited[cur.v] = true;

			for (Edge edge : edgeMap[cur.v]) {
				if (cost[edge.v] > cost[cur.v] + edge.w) {
					cost[edge.v] = cost[cur.v] + edge.w;
					q.add(new Edge(edge.v, cost[cur.v] + edge.w));
				}
			}

		}

		return cost;
	}

	static void init() throws IOException {
		String[] line = br.readLine().split(" ");
		V = Integer.parseInt(line[0]);
		E = Integer.parseInt(line[1]);

		edgeMap = new ArrayList[V + 1];
		for (int v = 1; v <= V; v++) {
			edgeMap[v] = new ArrayList<>();
		}

		for (int e = 0; e < E; e++) {
			String[] uvw = br.readLine().split(" ");
			int u = Integer.parseInt(uvw[0]);
			int v = Integer.parseInt(uvw[1]);
			int w = Integer.parseInt(uvw[2]);

			edgeMap[u].add(new Edge(v, w));
			edgeMap[v].add(new Edge(u, w));
		}

		String[] ab = br.readLine().split(" ");
		a = Integer.parseInt(ab[0]);
		b = Integer.parseInt(ab[1]);
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
