package 자료구조.스택.programmers;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main_괄호회전하기 {

	public static void main(String[] args) {

		System.out.println(new Solution().solution("[](){}"));
		System.out.println(new Solution().solution("}]()[{"));
		System.out.println(new Solution().solution("[)(]"));
//		System.out.println(new Solution().solution("(){[()]}]["));

	}

	static class Solution {

		int len;

		public int solution(String s) {
			// find first correct one by rotating s
			len = s.length();
			List<Character> list = s.chars().mapToObj(c -> (char) c)
					.collect(Collectors.toCollection(LinkedList::new));

			int rotateCnt = 0;
			while (!isCorrect(list) && rotateCnt < len) {
				rotate(list);
				rotateCnt++;
			}

			if (rotateCnt == len) {
				return 0;
			}

			// find number of splittable correct one in it
			return getAnswer(list);
		}

		private int getAnswer(List<Character> list) {
			int answer = 0;
			Stack<Character> stack = new Stack<>();
			for (Character c : list) {
				if (c == '[' || c == '{' || c == '(') {
					stack.push(c);
				}
				else {
					if (c == ']' && stack.peek() == '[' || c == '}' && stack.peek() == '{' || c == ')' && stack.peek() == '(') {
						stack.pop();
						answer += stack.isEmpty() ? 1 : 0;
					}
				}
			}
			return answer;
		}

		private void rotate(List<Character> list) {
			Character remove = list.remove(0);
			list.add(len - 1, remove);
		}

		private boolean isCorrect(List<Character> s) {
			Stack<Character> stack = new Stack<>();
			for (Character c : s) {
				if (c == '{' || c == '[' || c == '(') {
					if (stack.isEmpty() || (stack.peek() == '{' || stack.peek() == '[' || stack.peek() == '(')) {
						stack.push(c);
					}
					else {
						return false;
					}
				}
				else if (c == '}' || c == ']' || c == ')') {
					if (stack.isEmpty()) {
						return false;
					}
					else if ((c == '}' && stack.peek() != '{') || (c == ']' && stack.peek() != '[') || (c == ')' && stack.peek() != '(')) {
						return false;
					}
					stack.pop();
				}
			}
			return stack.isEmpty();
		}
	}
}
