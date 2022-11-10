package 백트래킹.boj;

import java.util.Scanner;

public class Main_NQueen_9663 {

	static int n;
	static int[][] board;
	static int numQueen;
	static int answer;

	public static void main(String[] args) {

		n = Integer.parseInt(new Scanner(System.in).nextLine());
		board = new int[n][n];

		for (int c = 0; c < n; c++) {
			dfs(0, c);
		}

		System.out.print(answer);
	}

	static void dfs(int r, int c) {
		if (board[r][c] == 0) {
			setQueen(r, c);
			if (numQueen == n) {
				answer++;
			}
			else {
				for (int nc = 0; nc < n; nc++) {
					dfs(r + 1, nc);
				}
			}
			removeQueen(r, c);
		}
	}


	static int[] dy = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx = new int[] {0, 1, 1, 1, 0, -1, -1, -1};

	static boolean isValid(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < n;
	}

	static void setQueen(int r, int c) {
		board[r][c]++;
		numQueen++;

		int ty, tx;
		for (int d = 0; d < 8; d++) {
			ty = r;
			tx = c;
			while (true) {
				ty += dy[d];
				tx += dx[d];
				if (!isValid(ty, tx)) {
					break;
				}
				board[ty][tx]++;
			}
		}
	}

	static void removeQueen(int r, int c) {
		board[r][c]--;
		numQueen--;

		int ty, tx;
		for (int d = 0; d < 8; d++) {
			ty = r;
			tx = c;
			while (true) {
				ty += dy[d];
				tx += dx[d];
				if (!isValid(ty, tx)) {
					break;
				}
				board[ty][tx]--;
			}
		}
	}
}
