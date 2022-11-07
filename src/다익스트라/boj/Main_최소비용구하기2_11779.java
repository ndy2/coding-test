package 다익스트라.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_최소비용구하기2_11779 {


	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int V, E;
	static int start;
	static int end;

	static List<Edge>[] edgeMap;
	static int[] cost;
	static int[] route;
	static boolean[] visited;

	static int INF = 100_000_000;

	public static void main(String[] args) throws IOException {
		init();
		dijkstra(start);
		printAnswer();
	}

	private static void printAnswer() {
		sb.append(cost[end]).append('\n');

		int cur = end;
		List<Integer> routeToEnd = new ArrayList<>();
		while (cur != 0) {
			routeToEnd.add(cur);
			cur = route[cur];
		}
		sb.append(routeToEnd.size()).append('\n');
		for (int i = routeToEnd.size() - 1; i >= 0; i--) {
			sb.append(routeToEnd.get(i)).append(' ');
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
			if (visited[cur]) {
				continue;
			}
			visited[cur] = true;

			for (Edge next : edgeMap[cur]) {
				if (cost[next.v] > cost[cur] + next.w) {
					cost[next.v] = cost[cur] + next.w;
					route[next.v] = cur;
					q.add(new Edge(next.v, cost[cur] + next.w));
				}
			}
		}

	}

	private static void init() throws IOException {
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());

		route = new int[V+1];
		visited = new boolean[V + 1];
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

		String[] startEnd = br.readLine().split(" ");
		start = Integer.parseInt(startEnd[0]);
		end = Integer.parseInt(startEnd[1]);

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
