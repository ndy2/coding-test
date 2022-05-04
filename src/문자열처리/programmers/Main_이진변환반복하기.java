package 문자열처리.programmers;

import java.util.Arrays;
import java.util.Stack;

public class Main_이진변환반복하기 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(new Solution().solution("110010101001")));
    }

    static class Solution {
        public int[] solution(String s) {
            int[] answer = new int[2];

            while (true) {
                if(s.equals("1")) break;
                answer[1] += count0(s);

                s = s.replaceAll("0+", "");
                int len = s.length();
                s = toOrder2(len);
                answer[0] += 1;
            }

            return answer;
        }

        int count0(String s) {
            return (int) s.chars().filter(i -> i == '0').count();
        }

        String toOrder2(int num) {
            Stack<Character> stack = new Stack<>();
            while (num > 0) {
                int r = num % 2;
                stack.push((char) ('0' + r));
                num /= 2;
            }

            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            return sb.toString();
        }
    }
}
