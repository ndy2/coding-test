package 백트래킹.boj;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main_N과M5_15654 {

	static int n, m;
	static List<Integer> numbers;
	static boolean[] visited;
	static StringBuilder answer;
	static int[] line;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		String[] nm = scanner.nextLine().split(" ");
		n = Integer.parseInt(nm[0]);
		m = Integer.parseInt(nm[1]);

		numbers = Arrays.stream(scanner.nextLine().split(" "))
				.map(Integer::parseInt).sorted()
				.collect(Collectors.toList());

		visited = new boolean[n + 1];

		answer = new StringBuilder();
		line = new int[n + 1];

		for (int i = 0; i < n; i++) {
			dfs(i, 0);
		}
		System.out.print(answer);
	}

	static void dfs(int i, int count) {
		visited[i] = true;
		line[count] = numbers.get(i);

		if (count == m - 1) {
			answer.append(Arrays.stream(line).boxed().map(String::valueOf).limit(m).collect(Collectors.joining(" "))).append('\n');
		}
		else {
			for (int j = 0; j < n; j++) {
				if (!visited[j]) dfs(j, count + 1);
			}
		}
		visited[i] = false;
	}
}
