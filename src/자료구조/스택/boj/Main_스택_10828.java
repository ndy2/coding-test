package 자료구조.스택.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_스택_10828 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		Stack<Integer> stack = new Stack<>();
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			String[] command = br.readLine().split(" ");
			String op = command[0];
			if (op.equals("push")) {
				int x = Integer.parseInt(command[1]);
				stack.push(x);
			}
			else if (op.equals("pop")) {
				sb.append(stack.isEmpty() ? -1 : stack.pop()).append('\n');
			}
			else if (op.equals("size")) {
				sb.append(stack.size()).append('\n');
			}
			else if (op.equals("empty")) {
				sb.append(stack.isEmpty()?1:0).append('\n');
			}
			else if (op.equals("top")) {
				sb.append(stack.isEmpty()?-1:stack.peek()).append('\n');
			}
		}
		System.out.print(sb);
	}
}
