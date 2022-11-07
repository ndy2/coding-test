package 자료구조.해시.boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main_수강신청_13414 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		String[] KL = br.readLine().split(" ");
		int K = Integer.parseInt(KL[0]);
		int L = Integer.parseInt(KL[1]);

		int cur = 0;
		Map<String, Integer> map = new HashMap<>();

		for (int i = 0; i < L; i++) {
			String id = br.readLine();
			map.put(id, ++cur);
		}

		map.entrySet().stream()
						.sorted(Comparator.comparingInt(Map.Entry::getValue))
						.limit(K)
						.map(Map.Entry::getKey)
						.forEach(k -> sb.append(k).append('\n'));

		System.out.print(sb);
	}
}
