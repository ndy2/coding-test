package 자료구조.링크드리스트.programmers;

import java.util.Stack;

public class Main_표편집2 {

	public static void main(String[] args) {


//		System.out.println(new 이분탐색.boj.Solution().solution(8, 2, new String[] {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"}));
		System.out.println(new Solution().solution(8, 2, new String[] {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"}));

	}

	static class Solution {


		public String solution(int n, int k, String[] cmd) {

			Stack<RowWithCursor> stack = new Stack<>();
			Row[] rows = new Row[n];
			for (int r = 0; r < n; r++) {
				rows[r] = new Row(r - 1, r + 1);
			}


			int cursor = k;
			for (String s : cmd) {
				String[] line = s.split(" ");
				String op = line[0];

				if (op.equals("U")) {
					int x = Integer.parseInt(line[1]);
					for (int i = 0; i < x; i++) {
						cursor = rows[cursor].prev;
					}
				}
				else if (op.equals("D")) {
					int x = Integer.parseInt(line[1]);
					for (int i = 0; i < x; i++) {
						cursor = rows[cursor].next;
					}
				}
				else if (op.equals("C")) {
					Row row = rows[cursor];
					if (row.prev != -1) rows[row.prev].next = row.next;
					if (row.next != n) rows[row.next].prev = row.prev;
					stack.push(new RowWithCursor(row, cursor));

					cursor = rows[cursor].next != n ? rows[cursor].next : rows[cursor].prev;
				}
				else if (op.equals("Z")) {
					RowWithCursor rowWithCursor = stack.pop();
					if (rowWithCursor.row.prev != -1) rows[rowWithCursor.row.prev].next = rowWithCursor.cursor;
					if (rowWithCursor.row.next != n) rows[rowWithCursor.row.next].prev = rowWithCursor.cursor;
				}
			}
			StringBuilder sb = new StringBuilder("O".repeat(n));

			while (!stack.isEmpty()) {
				RowWithCursor pop = stack.pop();
				sb.setCharAt(pop.cursor, 'X');
			}


			return sb.toString();
		}

		static class RowWithCursor {
			Row row;
			int cursor;

			RowWithCursor(Row row, int cursor) {
				this.row = row;
				this.cursor = cursor;
			}
		}

		static class Row {
			int prev;
			int next;

			Row(int prev, int next) {
				this.prev = prev;
				this.next = next;
			}
		}
	}
}
