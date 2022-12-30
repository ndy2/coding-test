package 수학.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * -3 -2 -1  0  1  2  3
 * --------------------
 * -3 |37    36(6^2) 15      34      33      32      31
 * -2 |38    17      16(4^2)  15     14      13      30
 * -1 |39    18       5       4(2^2)  3      12      29
 * 0 |40    19       6       1(1^2)  2      11      28
 * 1 |41    20       7       8       9(3^2) 10      27
 * 2 |42    21      22      23      24      25(5^2) 26
 * 3 |43    44      45      46      47      48      49(7^2)
 */

public class Main_1022_소용돌이_예쁘게_출력하기 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        new Solution().solve(
                Integer.parseInt(line[0]),
                Integer.parseInt(line[1]),
                Integer.parseInt(line[2]),
                Integer.parseInt(line[3])
        );
    }

    static class Solution {

        void solve(int r1, int c1, int r2, int c2) {

            int r1c1 = getNumber(r1, c1);
            int r1c2 = getNumber(r1, c2);
            int r2c1 = getNumber(r2, c1);
            int r2c2 = getNumber(r2, c2);

            int maxLength = Stream.of(r1c1, r1c2, r2c1, r2c2)
                    .map(String::valueOf)
                    .map(String::length)
                    .max(Comparator.naturalOrder()).get();
            String format = "%" + maxLength + "d ";

            StringBuilder sb = new StringBuilder();
            for (int r = r1; r <= r2; r++) {
                for (int c = c1; c <= c2; c++) {
                    sb.append(String.format(format, getNumber(r, c)));
                }
                sb.append('\n');
            }
            System.out.print(sb);
        }

        int getNumber(int r, int c) {

            // ㄱ shape
            if (r - c < 0) {
                int a = r - c;
                int dist = -1 - a;

                // 오른쪽 에서 찾음
                if (r + c >= 0) {
                    int base = (2 * c - 1) * (2 * c - 1) + 1;
                    return base + dist;
                }
                // 위쪽 에서 찾음
                else {
                    int base = (2 * r) * (2 * r);
                    return base - dist;
                }
            }
            // ㄴ shape
            else {
                int dist = r - c;

                // 왼쪽 에서 찾음
                if (r + c <= 0) {
                    int base = (2 * c) * (2 * c) + 1;
                    return base + dist;
                }
                // 아래쪽 에서 찾음
                else {
                    int base = (2 * r + 1) * (2 * r + 1);
                    return base - dist;
                }

            }

        }
    }

}
