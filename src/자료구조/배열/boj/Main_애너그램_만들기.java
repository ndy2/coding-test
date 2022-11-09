package 자료구조.배열.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_애너그램_만들기 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] a = count(br.readLine());
		int[] b = count(br.readLine());

		int answer = 0;
		for (int i = 0; i < 26; i++) {
			answer += Math.abs(a[i] - b[i]);
		}
		System.out.print(answer);
	}

	static int[] count(String a) {
		int[] result = new int[26];
		for (int i = 0; i < a.length(); i++) {
			result[a.charAt(i) - 'a']++;
		}
		return result;
	}
}
