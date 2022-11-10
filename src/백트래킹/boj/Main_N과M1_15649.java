package 백트래킹.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_N과M1_15649 {

	static int n;
	static int m;
	static StringBuilder line;
	static StringBuilder answer;
	static boolean[] visited;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] nm = br.readLine().split(" ");
		n = Integer.parseInt(nm[0]);
		m = Integer.parseInt(nm[1]);
		visited = new boolean[n + 1];

		line = new StringBuilder();
		answer = new StringBuilder();
		dfs(0, 0);
		System.out.print(answer);
	}

	static void dfs(int idx, int count) {
		if (idx != 0) {
			visited[idx] = true;
			line.append(idx).append(' ');
		}

		if (count != m) {
			for (int i = 1; i <= n; i++) {
				if (!visited[i]) {
					dfs(i, count + 1);
				}
			}
		}
		else {
			answer.append(line).append('\n');
		}
		if (idx != 0) {
			line.deleteCharAt(count * 2 - 1);
			line.deleteCharAt(count * 2 - 2);
			visited[idx] = false;
		}
	}
}
