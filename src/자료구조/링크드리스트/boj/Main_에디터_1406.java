package 자료구조.링크드리스트.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main_에디터_1406 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		String string = br.readLine();
		int len = string.length();

		List<Character> list = new LinkedList<>();
		for (int i = 0; i < len; i++) {
			list.add(string.charAt(i));
		}

		int M = Integer.parseInt(br.readLine());
		int cursor = len - 1;
		for (int i = 0; i < M; i++) {
			String command = br.readLine();
			char op = command.charAt(0);

			if (op == 'L') {
				if (cursor >= 0) cursor--;
			}
			else if (op == 'D') {
				if (cursor < len - 1) cursor++;
			}
			else if (op == 'B') {
				if(cursor > -1){
					list.remove(cursor);
					len --;
					cursor--;
				}
			}
			else {
				char c = command.charAt(2);
				cursor++;
				len++;
				list.add(cursor, c);
			}
		}

		printList(list);
	}

	private static void printList(List<Character> list) {
		StringBuilder sb = new StringBuilder();
		list.forEach(sb::append);

		System.out.print(sb);
		System.out.println();
	}

}
