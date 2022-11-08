package 자료구조.큐.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_큐2_18258 {


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());

		LinkedList<Integer> q = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			String[] line = br.readLine().split(" ");
			String op = line[0];
			if (op.equals("push")) {
				int x = Integer.parseInt(line[1]);
				q.offer(x);
			}
			else if (op.equals("pop")) {
				if (!q.isEmpty()) {
					sb.append(q.poll()).append('\n');
				}
				else {
					sb.append(-1).append('\n');
				}
			}
			else if (op.equals("size")) {
				sb.append(q.size()).append('\n');
			}
			else if (op.equals("empty")) {
				if (q.isEmpty()) {
					sb.append(1).append('\n');
				}
				else {
					sb.append(0).append('\n');
				}
			}
			else if (op.equals("front")) {
				if (!q.isEmpty()) {
					sb.append(q.get(0)).append('\n');
				}
				else {
					sb.append(-1).append('\n');
				}
			}
			else if (op.equals("back")) {
				if (!q.isEmpty()) {
					sb.append(q.get(q.size() - 1)).append('\n');
				}
				else {
					sb.append(-1).append('\n');
				}
			}
		}
		System.out.print(sb);
	}

}
