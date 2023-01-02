package 자료구조.링크드리스트.programmers;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main_표편집 {

	public static void main(String[] args) {

//		System.out.println(new 이분탐색.boj.Solution().solution(8, 2, new String[] {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"}));
		System.out.println(new Solution().solution(8, 2, new String[] {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"}));
	}

	static class Solution {
		public String solution(int n, int k, String[] cmd) {

			List<Integer> table = IntStream.range(0, n).boxed()
					.collect(Collectors.toCollection(LinkedList::new));

			// data[0] : index, data[1] : value
			Stack<int[]> data = new Stack<>();
			boolean[] removed = new boolean[n];

			int pos = k;
			for (String s : cmd) {
				System.out.println("command = " + s);

				if (s.charAt(0) == 'U') {
					int x = Integer.parseInt(s.substring(2));
					pos -= x;
				}
				else if (s.charAt(0) == 'D') {
					int x = Integer.parseInt(s.substring(2));
					pos += x;
				}
				else if (s.charAt(0) == 'C') {
					data.push(new int[] {pos, table.get(pos)});
					table.remove(pos); // remove(int)
					removed[pos] = true;

					if (pos == table.size()) pos--;
				}
				else if (s.charAt(0) == 'Z') {
					int[] restoreDate = data.pop();
					table.add(restoreDate[0], restoreDate[1]);
					removed[restoreDate[0]] = false;

					// 현재 선택된 행은 변경 x
					if (pos > restoreDate[0]) {
						pos ++;
					}
				}

			}

			StringBuilder answer = new StringBuilder();
			for (boolean b : removed) {
				answer.append(b ? 'X' : 'O');
			}
			return answer.toString();
		}
	}
}
