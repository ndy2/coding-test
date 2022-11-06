package 자료구조.링크드리스트.boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main_요세푸스문제_1158 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {

		String[] NK = br.readLine().split(" ");
		int N = Integer.parseInt(NK[0]);
		int K = Integer.parseInt(NK[1]);

		int[] value = new int[N];
		int[] prev = new int[N];
		int[] next = new int[N];

		for (int i = 0; i < N; i++) {
			value[i] = i + 1;
			prev[i] = i == 0 ? N - 1 : i - 1;
			next[i] = i == N - 1 ? 0 : i + 1;
		}

		StringBuilder sb = new StringBuilder();
		sb.append('<');
		int cur = N - 1;
		for (int i = 0; i < N; i++) {

			// find next Idx
			int curPlusK = cur;
			for (int j = 0; j < K; j++) {
				curPlusK = next[curPlusK];
			}

			// remove
			sb.append(value[curPlusK]);
			if(i!=N-1){
				sb.append(", ");
			}
			next[prev[curPlusK]] = next[curPlusK];
			prev[next[curPlusK]] = prev[curPlusK];

			cur = curPlusK;
		}
		sb.append('>');
		System.out.print(sb);
	}
}
