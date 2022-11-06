package 자료구조.링크드리스트.boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Main_키로거_5397 {


	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {

		int n = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < n; tc++) {
			String commands = br.readLine();
			int len = commands.length();

			List<Character> list = new LinkedList<>();
			ListIterator<Character> iter = list.listIterator();

			for (int i = 0; i < len; i++) {
				char op = commands.charAt(i);

				if(op == '<'){
					if(iter.hasPrevious()) iter.previous();
				}else if(op == '>'){
					if(iter.hasNext()) iter.next();
				}else if(op == '-'){
					if(iter.hasPrevious()){
						iter.previous();
						iter.remove();
					}
				}else{
					iter.add(op);
				}
			}

			for (Character c : list) {
				bw.write(c);
			}
			bw.write('\n');
			bw.flush();
		}

		br.close();
		bw.close();
	}
}
