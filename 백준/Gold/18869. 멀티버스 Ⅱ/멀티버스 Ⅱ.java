import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {

		String[] MN = br.readLine().split(" ");
		int M = Integer.parseInt(MN[0]);
		int N = Integer.parseInt(MN[1]);

		Map<String, Integer> map = new HashMap<>();

		for (int i = 0; i < M; i++) {
			String[] line = br.readLine().split(" ");
			List<Integer> univ = Arrays.stream(line).map(Integer::parseInt).collect(Collectors.toList());

			String key = univ.stream()
					.map(u -> lowerBound(univ, u))
					.map(String::valueOf)
					.collect(Collectors.joining());

			map.put(key, map.getOrDefault(key, 0) + 1);
		}

		int answer = map.values()
				.stream().filter(a -> a > 1)
				.map(a -> (a * (a - 1) / 2))
				.reduce(0, Integer::sum);
		System.out.println(answer);
	}

	static int lowerBound(List<Integer> list, int key) {
		int left = 0, right = list.size() - 1;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (list.get(mid) < key)
				left = mid + 1;
			else
				right = mid - 1;
		}

		return left;
	}
}
