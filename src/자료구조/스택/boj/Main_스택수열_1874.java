package 자료구조.스택.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_스택수열_1874 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		Stack<Integer> stack = new Stack<>();
		int cur = 1;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			int target = Integer.parseInt(br.readLine());
			if (target >= cur) {
				while (target >= cur) {
					stack.push(cur++);
					sb.append("+\n");
				}
			}

			if(stack.isEmpty() || target != stack.peek()){
				sb.setLength(0);
				sb.append("NO");
				break;
			}else{
				stack.pop();
				sb.append("-\n");

			}
		}

		System.out.print(sb);
	}
}
