package 자료구조.배열.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_알파벳개수_10808 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();

		int[] count = new int[26];
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			count[(int)(c - 'a')]++;
		}
		for (int i = 0; i < 26; i++) {
			System.out.printf("%d ", count[i]);
		}
	}


}
