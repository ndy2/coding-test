package 다익스트라.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_면접보는승법이네_17835 {


	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");

		int V = Integer.parseInt(line[0]);
		int E = Integer.parseInt(line[1]);
		int K = Integer.parseInt(line[2]);

		List<List<Edge>> graph = new ArrayList<>();
		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			graph.get(v).add(new Edge(u, dist));
		}


		st = new StringTokenizer(br.readLine());
		long[] dist = new long[V+1];
		Arrays.fill(dist, 100_000_000_000L);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 0; i < K; i++) {
			int exam = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(exam, 0));
			dist[exam] = 0;
		}

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			if (dist[cur.v] < cur.w) continue;

			for (Edge edge : graph.get(cur.v)) {
				if (dist[edge.v] > dist[cur.v] + edge.w) {
					dist[edge.v] = dist[cur.v] + edge.w;
					pq.add(new Edge(edge.v, dist[cur.v] + edge.w));
				}
			}
		}

		long ans = 0;
		long ansLoc = 0;
		for (int v = 1; v <= V; v++) {
			if (ans < dist[v]) {
				ans = dist[v];
				ansLoc = v;
			}
		}
		System.out.println(ansLoc);
		System.out.println(ans);
	}

	static class Edge implements Comparable<Edge> {
		int v;
		long w;

		Edge(int v, long w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.w, o.w);
		}
	}
}
