package 수학.programmers;

import java.util.Stack;

public class Main_k진수에서소수개수구하기 {

    public static void main(String[] args) {

        System.out.println(new Solution().solution(524287, 2));
    }

    static class Solution {
        public int solution(int n, int k) {
            String num = toOrderK(n, k);
            String[] targets = num.split("0+");

            System.out.println("num = " + num);
            for (String target : targets) {
                System.out.println("target = " + target);
            }

            int answer = 0;
            for (String target : targets) {
                long i = Long.parseLong(target);
                if(isPrime(i)){
                    answer++;
                }
            }

            return answer;
        }

        boolean isPrime(long i) {
            if(i==1) return false;
            if(i==2) return true;
            if(i%2==0) return false;

            for (long d = 3; (double)d <= Math.sqrt(i); d+=2) {
                if(i%d==0) return false;
            }
            return true;
        }

        String toOrderK(int num, int k) {

            Stack<Character> stack = new Stack<>();
            while (num > 0) {
                int r = num % k;
                stack.push((char)(r+'0'));
                num /= k;
            }
            StringBuilder sb = new StringBuilder();
            while(!stack.isEmpty()){
                sb.append(stack.pop());
            }
            return sb.toString();
        }
    }
}
