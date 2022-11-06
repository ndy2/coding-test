package 이분탐색.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main_과자나눠주기_16401 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {

		String[] MN = br.readLine().split(" ");
		int M = Integer.parseInt(MN[0]);
		int N = Integer.parseInt(MN[1]);

		List<Integer> snacks = Arrays.stream(br.readLine().split(" "))
				.map(Integer::parseInt)
				.sorted()
				.collect(Collectors.toList());

		int l = 1;
		int r = 1_000_000_000;
//		int r = 20;

		while (l + 1 < r) {
			int m = (l + r) / 2;
			// check - m 길이의 과자를 분배 할 수 있는지 여부
			boolean check = false;

			if (snacks.get(N - 1) >= m) {
				int count = 0;
				int idx = lowerBound(snacks, m);

				for (int i = idx; i < N; i++) {
					count += snacks.get(i) / m;

					if (count >= M) {
						check = true;
						break;
					}
				}
			}

			if (!check) {
				// 길이를 더 줄여본다.
				r = m;
			}
			else {
				l = m;
			}
		}

		int sumSnack = snacks.stream().reduce(0, Integer::sum);

		if (l == 1 && M > sumSnack) System.out.println(0);
		else System.out.println(l);
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
