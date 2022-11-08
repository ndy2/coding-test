package 자료구조.큐.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main_회전하는큐_1021 {


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);

		LinkedList<Integer> deque = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			deque.add(i);
		}

		int answer = 0;
		String[] line2 = br.readLine().split(" ");
		for (int i = 0; i < m; i++) {
			int x = Integer.parseInt(line2[i]) - 1;

			int size = deque.size();
			int idx = deque.indexOf(x);

			if (idx <= size / 2) {
				for (int j = 0; j < idx; j++) {
					answer++;
					deque.addLast(deque.pollFirst());
				}
			}
			else {
				int amount = size - idx;
				for (int j = 0; j < amount; j++) {
					answer++;
					deque.addFirst(deque.pollLast());
				}
			}

			deque.removeFirst();
		}
		System.out.print(answer);
	}
}
