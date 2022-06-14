package 누적합.boj;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main_인간_컴퓨터_상호작용_16139 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s = br.readLine();
        int len = s.length();

        int[][] counts = new int[26][len +1];
        for (int i = 0; i < len; i++) {
            int alphabet = s.charAt(i)-'a';

            for (int alpha = 0; alpha < 26; alpha++) {
                counts[alpha][i+1] = counts[alpha][i];
                counts[alpha][i+1] += alpha == alphabet ? 1 : 0;
            }
        }

        int q = parseInt(br.readLine());
        for (int t = 0; t < q; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int targetAlphabet = st.nextToken().charAt(0) - 'a';
            int l = parseInt(st.nextToken());
            int r = parseInt(st.nextToken());

            sb.append(counts[targetAlphabet][r+1] - counts[targetAlphabet][l]).append('\n');
        }
        System.out.print(sb);
    }
}
