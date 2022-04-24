package 수학.programmers;

public class Main_멀쩡한사각형 {

    public static void main(String[] args) {
        System.out.println(
                new Solution().solution(1, 1)
        );
    }

    static class Solution {

        int gcd(int a, int b) {
            // make a > b
            if (b > a) {
                int temp = b;
                b = a;
                a = temp;
            }

            if (a % b == 0) {
                return b;
            } else {
                return gcd(b, a - b);
            }
        }

        public long solution(int w, int h) {
            int gcd = gcd(w, h);

            int w1 = w / gcd;
            int h1 = h / gcd;

            double[] heights = new double[w1 + 1];
            boolean[] isPoint = new boolean[w1 + 1];

            for (int idx = 0; idx <= w1; idx++) {
                heights[idx] = (idx * (double) h1) / (long)w1;
                isPoint[idx] = (idx % (long)w1 == 0);
            }

            long cnt = 0;
            for (int idx = 0; idx < w1; idx++) {
                double leftHeight = heights[idx];
                double rightHeight = heights[idx + 1];

                int l1 = (int) leftHeight;
                int r1 = (int) rightHeight;

                boolean lp = isPoint[idx];
                boolean rp = isPoint[idx + 1];

                cnt += (r1 - l1) + (!rp ? 1 : 0);
            }

            return (long) w * (long) h - cnt * gcd;
        }
    }
}
