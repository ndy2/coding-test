package bfsdfs.dfs.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_유기농배추_1012 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N, M, K;
	static boolean[][] map;
	static boolean[][] visited;

	static int count = 0;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			count = 0;
			String[] NMK = br.readLine().split(" ");

			M = Integer.parseInt(NMK[0]);
			N = Integer.parseInt(NMK[1]);
			K = Integer.parseInt(NMK[2]);
			map = new boolean[N][M];
			for (int k = 0; k < K; k++) {
				String[] XY = br.readLine().split(" ");
				int X = Integer.parseInt((XY[0]));
				int Y = Integer.parseInt((XY[1]));
				map[Y][X] = true;
			}

			visited = new boolean[N][M];

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (!visited[r][c] && map[r][c]) {
						dfs(r, c);
						count++;
					}
				}
			}
			sb.append(count).append('\n');
		}
		System.out.println(sb);
	}

	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};

	private static void dfs(int r, int c) {
		visited[r][c] = true;

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
