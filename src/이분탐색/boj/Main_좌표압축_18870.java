package 이분탐색.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main_좌표압축_18870 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());

		List<Integer> coordinates = Arrays.stream(br.readLine().split(" "))
				.map(Integer::parseInt).collect(Collectors.toList());

		List<Integer> sortedCoordinates = coordinates.stream()
				.distinct().sorted().collect(Collectors.toList());

		StringBuilder sb = new StringBuilder();
		for (Integer c : coordinates) {
			int idx = Collections.binarySearch(sortedCoordinates, c);
			sb.append(idx).append(' ');
		}
		System.out.println(sb);
	}
}
