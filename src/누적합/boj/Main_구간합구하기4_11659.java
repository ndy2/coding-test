package 누적합.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main_구간합구하기4_11659 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N,M;
    static int[] sum;

    public static void main(String[] args) throws IOException {

        StringTokenizer line1 = new StringTokenizer(br.readLine());
        N = parseInt(line1.nextToken());
        M = parseInt(line1.nextToken());

        sum = new int[N+1];
        StringTokenizer line2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sum[i+1] = sum[i] + parseInt(line2.nextToken());
        }

        for (int t = 0; t < M; t++) {
            StringTokenizer lineN = new StringTokenizer(br.readLine());

            int i = parseInt(lineN.nextToken());
            int j = parseInt(lineN.nextToken());

            System.out.println(sum[j] - sum[i-1]);
        }
    }

}
