package bfsdfs.dfs.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_그림_1926 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N, M;
	static boolean[][] map;
	static boolean[][] visited;

	static int count = 0;
	static int size = 0;
	static int maxSize = 0;

	public static void main(String[] args) throws IOException {
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);

		map = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			String[] line = br.readLine().split(" ");
			for (int c = 0; c < M; c++) {
				map[r][c] = line[c].equals("1");
			}
		}

		visited = new boolean[N][M];


		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (!visited[r][c] && map[r][c]) dfs(r, c);

				if (size > 0) {
					count++;
					maxSize = Math.max(size, maxSize);

					size = 0;
				}
			}
		}

		System.out.printf("%d\n%d", count, maxSize);
	}

	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};

	private static void dfs(int r, int c) {
		visited[r][c] = true;
		size++;

		for (int d = 0; d < 4; d++) {
			int ty = r + dy[d];
			int tx = c + dx[d];

			if (isValid(ty, tx) && !visited[ty][tx] && map[ty][tx]) {
				dfs(ty, tx);
			}
		}
	}

	private static boolean isValid(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

}
