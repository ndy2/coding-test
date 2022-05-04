package 비트연산.programmers;

import java.util.Arrays;
import java.util.Stack;

public class Main_2개이하로다른비트 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(new long[]{2, 7})));
    }

    static class Solution {
        public long[] solution(long[] numbers) {
            return Arrays.stream(numbers).map(this::f).toArray();
        }

        long f(long n) {
            if(n%2==0) return n+1;
            StringBuilder sb = toOrder2(n);
            int idx = sb.lastIndexOf("01");

            sb.replace(idx, idx+2,"10");
            return Long.valueOf(sb.toString(), 2);
        }

        StringBuilder toOrder2(long num) {
            Stack<Character> stack = new Stack<>();
            while (num > 0) {
                long r = num % 2;
                stack.push((char) ('0' + r));
                num /= 2;
            }

            StringBuilder sb = new StringBuilder("0");
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            return sb;
        }
    }
}
