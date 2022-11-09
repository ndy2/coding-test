package 자료구조.배열.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_두수의합_3723 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		String[] line = br.readLine().split(" ");
		int[] numbers = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(line[i]);
		}
		int x = Integer.parseInt(br.readLine());

		Arrays.sort(numbers);
		int bound = upperBound(numbers, x);
		int answer = 0;
		System.out.println("numbers = " + Arrays.toString(numbers));
		for (int i = 0; i < bound; i++) {

			answer += Arrays.binarySearch(numbers, x - numbers[i]) >= 0 ? 1 : 0;
			System.out.println("answer = " + answer);
		}
		System.out.print(answer/2);
	}

	private static int upperBound(int[] data, int target) {
		int begin = 0;
		int end = data.length;

		while(begin < end) {
			int mid = (begin + end) / 2;

			if(data[mid] <= target) {
				begin = mid + 1;
			}
			else {
				end = mid;
			}
		}
		return end;
	}

}
