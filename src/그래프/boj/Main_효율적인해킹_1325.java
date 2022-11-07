package 그래프.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * fail! - 단방향 그래프 라는 보장이 없으므로 dp 를 활용 할 수 없다.
 * 그냥 dfs bfs 돌리자!
 */
public class Main_효율적인해킹_1325 {

	static int E, V;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int[] dp;
	static List<Integer>[] edges;
	static int maxDp = 0;

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

		dp = new int[V + 1];
		for (int v = 1; v <= V; v++) {
			setDp(v);
		}
		for (int v = 1; v <= V ; v++) {
			if(dp[v]==maxDp) sb.append(v).append(' ');
		}
		System.out.println(sb);
	}

	private static int setDp(int v) {
		if(dp[v]!=0) return dp[v];

		int cur = 1;
		for (int to : edges[v]) {
			cur += setDp(to);
		}
		dp[v] = cur;
		maxDp = Math.max(maxDp, cur);
		return cur;
	}
}
