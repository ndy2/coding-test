package 수학.programmers;

public class Main_N개의최소공배수 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{2, 6, 8, 14}));
    }

    static class Solution {
        public int solution(int[] arr) {
            int len = arr.length - 1;

            for (int i = 0; i < len; i++) {
                int a = arr[i];
                int b = arr[i + 1];
                int gcd = gcd(a, b);
                arr[i+1] = lcm(gcd, a, b);
            }

            return arr[len];
        }

        int gcd(int a, int b) {
            int c;
            while (b > 0) {
                c = a % b;
                a = b;
                b = c;
            }
            return a;
        }

        int lcm(int gcd, int a, int b) {
            return gcd * (a / gcd) * (b / gcd);
        }
    }
}
