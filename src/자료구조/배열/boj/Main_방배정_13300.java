package 자료구조.배열.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_방배정_13300 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NK = br.readLine().split(" ");
		int N = Integer.parseInt(NK[0]);
		int K = Integer.parseInt(NK[1]);

		int[][] count = new int[6][2];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			count[line.charAt(2) - '1'][line.charAt(0) - '0'] ++;
		}
		int answer = 0;
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 2; c++) {
				answer += Math.ceil( (double) count[r][c] / K);
			}
		}
		System.out.print(answer);
	}
}
