package 분할정복.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class Main_쿼드트리_1992 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] board;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        N = parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        quadTree(0, 0, N, N);
        System.out.println(sb);
    }

    static void quadTree(int r1, int c1, int r2, int c2) {
        if (checkHasOfOnly(0, r1, c1, r2, c2)) {
            sb.append('0');
        } else if (checkHasOfOnly(1, r1, c1, r2, c2)) {
            sb.append('1');
        } else {
            sb.append('(');
            quadTree(r1, c1, (r1 + r2) / 2, (c1 + c2) / 2);
            quadTree(r1, (c1 + c2) / 2, (r1 + r2) / 2, c2);
            quadTree((r1 + r2) / 2, c1, r2, (c1 + c2) / 2);
            quadTree((r1 + r2) / 2, (c1 + c2) / 2, r2, c2);
            sb.append(')');
        }
    }

    static boolean checkHasOfOnly(int target, int r1, int c1, int r2, int c2) {
        for (int r = r1; r < r2; r++) {
            for (int c = c1; c < c2; c++) {
                if (board[r][c] != target) {
                    return false;
                }
            }
        }
        return true;
    }
}
