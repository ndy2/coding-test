package 누적합.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15724 {


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        int N, M;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] people = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                people[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] peopleSum = new int[N + 1][M + 1];

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= M; c++) {
                peopleSum[r][c] = people[r][c] + peopleSum[r - 1][c] + peopleSum[r][c - 1] - peopleSum[r - 1][c - 1];
            }
        }

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int A = peopleSum[x2][y2];
            int B = peopleSum[x2][y1-1];
            int C = peopleSum[x1-1][y2];
            int D = peopleSum[x1-1][y1-1];
            System.out.println(A - B - C + D);
        }
    }
}
