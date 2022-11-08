package 자료구조.큐.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main_AC_5430 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			String command = br.readLine();
			String len = br.readLine();
			String array = br.readLine();
			array = array.substring(1, array.length() - 1);
			LinkedList<String> list = array.isEmpty()
					? new LinkedList<>()
					: Arrays.stream(array.split(","))
					.collect(Collectors.toCollection(LinkedList::new));

			boolean error = false;
			boolean forward = true;
			for (int i = 0; i < command.length(); i++) {
				char op = command.charAt(i);
				if (op == 'R') {
					forward = !forward;
				}
				else {
					if (list.isEmpty()) {
						sb.append("error\n");
						error = true;
						break;
					}
					else {
						if (forward) {
							list.removeFirst();
						}else{
							list.removeLast();
						}
					}
				}
			}

			if (!error) {
				if(forward){
					sb.append(list.stream().collect(Collectors.joining(",", "[", "]\n")));
				}else{
					sb.append(IntStream.range(0, list.size())
							.map(i -> list.size() - i - 1)
							.mapToObj(list::get)
							.collect(Collectors.joining(",", "[", "]\n")));
				}
			}
		}
		System.out.print(sb);
	}
}
