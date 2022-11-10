package 수학.programmers;

import java.util.ArrayList;
import java.util.List;

public class Main_교점에별만들기 {

	public static void main(String[] args) {

		String[] solution = new Solution().solution(new int[][] {
//				{2, -1, 4},
//				{-2, -1, 4},
//				{0, -1, 1},
//				{5, -8, -12},
//				{5, 8, 12}

				{0, 1, -1},
				{1, 0, -1},
				{1, 0, 1}

		});

		for (String line : solution) {
			System.out.println(line);
		}

	}

	static class Solution {
		public String[] solution(int[][] line) {

			List<long[]> points = new ArrayList<>();

			long minY = Long.MAX_VALUE;
			long minX = Long.MAX_VALUE;

			long maxY = Long.MIN_VALUE;
			long maxX = Long.MIN_VALUE;

			int len = line.length;
			for (int i = 0; i < len; i++) {
				for (int j = i + 1; j < len; j++) {
					long a = line[i][0];
					long b = line[i][1];
					long e = line[i][2];

					long c = line[j][0];
					long d = line[j][1];
					long f = line[j][2];

					long xBunja = b * f - e * d;
					long xBunmo = a * d - b * c;

					long yBunja = e * c - a * f;
					long yBunmo = a * d - b * c;

					if (xBunmo != 0 && yBunmo != 0 && xBunja % xBunmo == 0 && yBunja % yBunmo == 0) {
						long x = xBunja / xBunmo;
						long y = yBunja / yBunmo;

						minY = Long.min(minY, y);
						minX = Long.min(minX, x);

						maxY = Long.max(maxY, y);
						maxX = Long.max(maxX, x);

						points.add(new long[] {y, x});
					}
				}
			}

			long height = maxY - minY + 1;
			long width = maxX - minX + 1;

			StringBuilder[] map = new StringBuilder[(int)height];
			for (int r = 0; r < height; r++) {
				map[r] = new StringBuilder(".".repeat((int)width));
			}

			for (long[] point : points) {
				long y = point[0];
				long x = point[1];

				long row = maxY - y;
				long col = x - minX;

				map[(int)row].setCharAt((int)col, '*');
			}

			String[] answer = new String[(int)height];
			for (int r = 0; r < height; r++) {
				answer[r] = map[r].toString();
			}

			return answer;
		}
	}
}
