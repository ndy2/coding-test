package 이분탐색.programmers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main_차집합 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {

		String[] NM = br.readLine().split(" ");
		int N = Integer.parseInt(NM[0]);
		int M = Integer.parseInt(NM[1]);

		Set<Integer> setA = Arrays.stream(br.readLine().split(" "))
				.map(Integer::parseInt)
				.collect(Collectors.toSet());

		Set<Integer> setB = Arrays.stream(br.readLine().split(" "))
				.map(Integer::parseInt)
				.collect(Collectors.toSet());

		List<Integer> aDiffB = setA.stream()
				.filter(it -> !setB.contains(it))
				.sorted()
				.collect(Collectors.toList());

		StringBuilder sb = new StringBuilder();
		if(aDiffB.isEmpty()){
			sb.append(0);
		}else{
			sb.append(aDiffB.size()).append('\n');
			aDiffB.forEach(i -> sb.append(i).append(' '));
		}

		System.out.print(sb);
	}
}
