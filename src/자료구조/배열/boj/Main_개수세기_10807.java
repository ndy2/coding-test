package 자료구조.배열.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_개수세기_10807 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		String numbersString = br.readLine();
		int v = Integer.parseInt(br.readLine());

		System.out.print(
				Arrays.stream(numbersString.split(" "))
				.map(Integer::parseInt)
				.filter(i -> i == v)
				.count()
		);
	}
}
