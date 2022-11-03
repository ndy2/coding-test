package 자료구조.스택.programmers;

import java.util.Stack;

public class Main_햄버거만들기 {

	public static void main(String[] args) {

		System.out.println(new Solution().solution(new int[] {2, 1, 1, 2, 3, 1, 2, 3, 1}));
	}

	static class Solution {
		public int solution(int[] ingredient) {
			int answer = 0;

			Stack<Integer> stack = new Stack<>();
			stack.push(-1); // add dummy

			for (int i : ingredient) {
				int peek = stack.peek();
				if (i == 1) {
					if (peek == 13) {
						answer++;
						stack.pop(); // pop 13고기
						stack.pop(); // pop 12야채
						stack.pop(); // pop 1 빵
					}else{
						stack.push(1);
					}
				}
				else if (i == 2) {
					if(peek == 1){
						stack.push(12); //push 12 야채
					}else{
						stack.push(2); //push 2 야채 - will never pop
					}
				}
				else if (i == 3) {
					if (peek == 12){
						stack.push(13); //push 13 고기
					}else{
						stack.push(3); //push 3 고기 - will never pop
					}
				}
			}

			return answer;
		}
	}
}
