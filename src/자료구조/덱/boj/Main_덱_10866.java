package 자료구조.덱.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main_덱_10866 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());

		Deque<Integer> deque = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			String[] line = br.readLine().split(" ");
			String op = line[0];
			if (op.equals("push_front")) {
				int x = Integer.parseInt(line[1]);
				deque.addFirst(x);
			}
			else if (op.equals("push_back")) {
				int x = Integer.parseInt(line[1]);
				deque.addLast(x);
			}
			else if (op.equals("pop_front")) {
				if (deque.isEmpty()) {
					sb.append(-1).append('\n');
				}
				else {
					sb.append(deque.pollFirst()).append('\n');
				}
			}
			else if (op.equals("pop_back")) {
				if (deque.isEmpty()) {
					sb.append(-1).append('\n');
				}
				else {
					sb.append(deque.pollLast()).append('\n');
				}
			}
			else if (op.equals("size")) {
				sb.append(deque.size()).append('\n');
			}
			else if (op.equals("empty")) {
				if (deque.isEmpty()) {
					sb.append(1).append('\n');
				}
				else {
					sb.append(0).append('\n');
				}
			}
			else if (op.equals("front")) {
				if (deque.isEmpty()) {
					sb.append(-1).append('\n');
				}
				else {
					sb.append(deque.peekFirst()).append('\n');
				}
			}
			else if (op.equals("back")) {
				if (deque.isEmpty()) {
					sb.append(-1).append('\n');
				}
				else {
					sb.append(deque.peekLast()).append('\n');
				}
			}

		}
		System.out.print(sb);

	}

}
