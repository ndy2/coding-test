package bfsdfs.bfs.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_알고스팟_1261 {


	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] HW = br.readLine().split(" ");
		int W = Integer.parseInt(HW[0]);
		int H = Integer.parseInt(HW[1]);

		boolean[][] visited = new boolean[H][W];
		boolean[][] map = new boolean[H][W];
		for (int r = 0; r < H; r++) {
			String line = br.readLine();
			for (int c = 0; c < W; c++) {
				map[r][c] = line.charAt(c) == '1';
			}
		}

		int[] dy = {0, 0, 1, -1};
		int[] dx = {1, -1, 0, 0};

		Queue<Info> q = new PriorityQueue<>();
		q.offer(new Info(0, 0, 0));
		visited[0][0] = true;

		int answer = -1;
		while (!q.isEmpty()) {
			Info pos = q.poll();

			if(pos.r == H-1 && pos.c == W-1){
				answer = pos.cnt;
				break;
			}

			for (int d = 0; d < 4; d++) {
				int ty = pos.r + dy[d];
				int tx = pos.c + dx[d];

				if (ty < 0 || ty >= H || tx < 0 || tx >= W) continue;
				if(visited[ty][tx]) continue;

				visited[ty][tx] = true;
				q.offer(new Info(ty, tx, pos.cnt + (map[ty][tx] ? 1 : 0)));
			}
		}
		System.out.print(answer);
	}

	static class Info implements Comparable<Info> {
		int r, c, cnt;

		Info(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Info o) {
			return Integer.compare(cnt, o.cnt);
		}
	}
}
