package 이분탐색.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_나무자르기_2805 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");

		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);

		int[] trees = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.sorted()
				.toArray();

		int lo = 0;
		int hi = trees[trees.length - 1];

		while (lo + 1 < hi) {
			int mid = (lo + hi) / 2;
			long sum = getTreeHeightSumCutBy(trees, mid);

			if (sum >= m) {
				lo = mid;
			}
			else {
				hi = mid;
			}
		}
		System.out.print(lo);
	}

	static long getTreeHeightSumCutBy(int[] trees, int height) {
		int bound = upperBound(trees, height);

		long result = 0;
		for (int i = bound; i < trees.length; i++) {
			result += trees[i] - height;
		}
		return result;
	}


	static int upperBound(int[] data, int target) {
		int begin = 0;
		int end = data.length;

		while (begin < end) {
			int mid = (begin + end) / 2;

			if (data[mid] <= target) {
				begin = mid + 1;
			}
			else {
				end = mid;
			}
		}
		return end;
	}
}
