package 다익스트라.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_최단경로_1753 {


	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int V, E;
	static int start;

	static List<Edge>[] edgeMap;
	static int[] cost;
	static boolean[] visited;

	static int INF = 100_000_000;

	public static void main(String[] args) throws IOException {
		init();
		dijkstra(start);
		printCost();
	}

	private static void printCost() {
		for (int v = 1; v <= V; v++) {
			if (cost[v] == INF) sb.append("INF").append('\n');
			else sb.append(cost[v]).append('\n');
		}
		System.out.print(sb);
	}

	static void dijkstra(int start) {
		cost[start] = 0;
		Queue<Edge> q = new PriorityQueue<>();
		q.add(new Edge(start, 0));

		while (!q.isEmpty()) {
			Edge pos = q.poll();
			int cur = pos.v;

			if(visited[cur]) continue;
			visited[cur] = true;

			for (Edge next : edgeMap[cur]) {
				if (cost[next.v] > cost[cur] + next.w) {
					cost[next.v] = cost[cur] + next.w;
					q.add(new Edge(next.v, cost[cur] + next.w));
				}
			}
		}

	}

	private static void init() throws IOException {
		String[] VE = br.readLine().split(" ");
		V = Integer.parseInt(VE[0]);
		E = Integer.parseInt(VE[1]);
		start = Integer.parseInt(br.readLine());

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
		}

		visited = new boolean [V+1];
		cost = new int[V + 1];
		Arrays.fill(cost, INF);
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
