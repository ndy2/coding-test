package 백트래킹.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main_로또_15654 {

	static int n, m;
	static List<Integer> numbers;
	static boolean[] visited;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder answer;
	static int[] line;

	public static void main(String[] args) throws IOException {

		m = 6;

		while (true) {
			String tc = br.readLine();
			if (tc.equals("0")) break;

			String[] tcSplit = tc.split(" ");
			n = Integer.parseInt(tcSplit[0]);

			numbers = Arrays.stream(tcSplit)
					.skip(1)
					.map(Integer::parseInt)
					.collect(Collectors.toList());

			visited = new boolean[n + 1];

			answer = new StringBuilder();
			line = new int[n + 1];

			for (int i = 0; i < n; i++) {
				dfs(i, 0);
			}
			System.out.print(answer.append('\n'));
		}
	}

	static void dfs(int i, int count) {
		visited[i] = true;
		line[count] = numbers.get(i);

		if (count == m - 1) {
			answer.append(Arrays.stream(line).boxed().map(String::valueOf).limit(m).collect(Collectors.joining(" ")))
					.append('\n');
		}
		else {
			for (int j = i; j < n; j++) {
				if (!visited[j]) dfs(j, count + 1);
			}
		}
		visited[i] = false;
	}
}
