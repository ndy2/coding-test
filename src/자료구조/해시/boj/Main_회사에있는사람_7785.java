package 자료구조.해시.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Main_회사에있는사람_7785 {


	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		int n = Integer.parseInt(br.readLine());
		Set<String> people = new HashSet<>();

		for (int i = 0; i < n; i++) {
			String[] line = br.readLine().split(" ");
			if(line[1].charAt(0) == 'e'){
				people.add(line[0]);
			}else{
				people.remove(line[0]);
			}
		}

		people.stream().sorted(Comparator.reverseOrder()).forEach(name -> sb.append(name).append('\n'));
		System.out.print(sb);
	}
}
