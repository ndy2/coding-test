package 자료구조.덱.boj;

import java.util.Arrays;
import java.util.LinkedList;

public class Main_행렬과연산 {

	public static void main(String[] args) {

		Solution solution = new Solution();

		System.out.println(Arrays.deepToString(solution.solution(new int[][] {
						{1, 2},
						{3, 4}
				}, new String[] {
						"Rotate"
				}
		)));

	}

	static class Solution {
		public int[][] solution(int[][] rc, String[] operations) {

			LinkedList<Integer> left = new LinkedList<>();
			LinkedList<Integer> right = new LinkedList<>();
			LinkedList<LinkedList<Integer>> mid = new LinkedList<>();
			int h = rc.length;
			int w = rc[0].length;
			for (int r = 0; r < h; r++) {
				left.add(rc[r][0]);
				LinkedList<Integer> row = new LinkedList<>();
				mid.add(row);
				for (int c = 1; c < w - 1; c++) {
					row.add(rc[r][c]);
				}
				right.add(rc[h - r - 1][w - 1]);
			}

			for (String op : operations) {

				if (op.equals("Rotate")) {
					LinkedList<Integer> top = mid.getFirst();
					LinkedList<Integer> bottom = mid.getLast();

					if (top.isEmpty()) {
						right.addLast(left.pollFirst());
						left.addLast(right.pollFirst());
					}
					else {
						right.addLast(top.pollLast());
						bottom.addLast(right.pollFirst());
						left.addLast(bottom.pollFirst());
						top.addFirst(left.pollFirst());
					}
				}

				else if (op.equals("ShiftRow")) {
					mid.addFirst(mid.pollLast());
					left.addFirst(left.pollLast());
					right.addLast(right.pollFirst());
				}
			}


			int[][] answer = new int[h][w];
			for (int r = 0; r < h; r++) {
				answer[r][0] = left.pollFirst();
				LinkedList<Integer> row = mid.pollFirst();
				for (int c = 1; c < w - 1; c++) {
					answer[r][c] = row.pollFirst();
				}
				answer[r][w - 1] = right.pollLast();
			}
			return answer;
		}
	}
}
