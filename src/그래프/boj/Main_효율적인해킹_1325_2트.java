package 그래프.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * fail! - 단방향 그래프 라는 보장이 없으므로 dp 를 활용 할 수 없다.
 * 그냥 dfs bfs 돌리자!
 */
public class Main_효율적인해킹_1325_2트 {

	static int E, V;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static List<Integer>[] edges;
	static int[] size;
	static int maxSize = 0;

	public static void main(String[] args) throws IOException {

		String[] NM = br.readLine().split(" ");

		V = Integer.parseInt(NM[0]);
		E = Integer.parseInt(NM[1]);

		edges = new ArrayList[V + 1];
		for (int v = 1; v <= V; v++) {
			edges[v] = new ArrayList<>();
		}

		for (int e = 0; e < E; e++) {
			String[] edge = br.readLine().split(" ");
			int from = Integer.parseInt(edge[1]);
			int to = Integer.parseInt(edge[0]);

			edges[from].add(to);
		}

		size = new int[V + 1];

		for (int v = 1; v <= V; v++) {
			int curSize = dfs(v);
			size[v] = curSize;
			maxSize = Math.max(maxSize, curSize);
		}
		for (int v = 1; v <= V; v++) {
			if (size[v] == maxSize) sb.append(v).append(' ');
		}
		System.out.println(sb);
	}

	private static int dfs(int v) {

		int ret = 0;
		boolean[] visited = new boolean[V + 1];

		Queue<Integer> q = new LinkedList<>();
		q.add(v);
		visited[v] = true;

		while (!q.isEmpty()) {
			int pos = q.poll();
			for (int to : edges[pos]) {
				if (!visited[to]) {
					ret++;
					visited[to] = true;
					q.add(to);
				}
			}
		}
		return ret;
	}
}
