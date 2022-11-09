package 자료구조.배열.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_Strfry_11328 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {

			String[] line = br.readLine().split(" ");
			sb.append(Arrays.equals(count(line[0]), count(line[1]))? "Possible\n" : "Impossible\n");
		}
		System.out.print(sb);
	}

	static int[] count(String a) {
		int[] result = new int[26];
		for (int i = 0; i < a.length(); i++) {
			result[a.charAt(i) - 'a']++;
		}
		return result;
	}
}
