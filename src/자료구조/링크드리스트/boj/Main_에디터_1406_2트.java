package 자료구조.링크드리스트.boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Main_에디터_1406_2트 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		String string = br.readLine();
		int len = string.length();

		List<Character> list = new LinkedList<>();
		for (int i = 0; i < len; i++) {
			list.add(string.charAt(i));
		}

		ListIterator<Character> iter = list.listIterator(len);

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			String command = br.readLine();

			char op = command.charAt(0);

			if (op == 'L') {
				if (iter.hasPrevious()) iter.previous();
			}
			else if (op == 'D') {
				if (iter.hasNext()) iter.next();
			}
			else if (op == 'B') {
				if(iter.hasPrevious()){
					iter.previous();
					iter.remove();
				}
			}
			else {
				char c = command.charAt(2);
				iter.add(c);
			}
		}

		for (Character c : list) {
			bw.write(c);
		}
		bw.flush();
		bw.close();
	}

}
