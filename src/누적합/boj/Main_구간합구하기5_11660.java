package 누적합.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main_구간합구하기5_11660 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        StringTokenizer line = new StringTokenizer(br.readLine());
        int N = parseInt(line.nextToken());
        int[][] sum = new int[N + 1][N + 1];

        for (int r = 1; r <= N; r++) {
            StringTokenizer row = new StringTokenizer(br.readLine());

            for (int c = 1; c <= N; c++) {
                sum[r][c] = sum[r - 1][c] + sum[r][c - 1] - sum[r - 1][c - 1] + parseInt(row.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();
        int M = parseInt(line.nextToken());
        for (int i = 0; i < M; i++) {
            StringTokenizer query = new StringTokenizer(br.readLine());
            int x1 = parseInt(query.nextToken()) - 1;
            int y1 = parseInt(query.nextToken()) - 1;
            int x2 = parseInt(query.nextToken());
            int y2 = parseInt(query.nextToken());

            sb.append(sum[x2][y2] - sum[x2][y1] - sum[x1][y2] + sum[x1][y1]).append('\n');
        }
        System.out.print(sb);
    }
}
