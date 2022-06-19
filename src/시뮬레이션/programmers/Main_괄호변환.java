package 시뮬레이션.programmers;

import java.util.Stack;

public class Main_괄호변환 {

    //균형잡힌 괄호 - balanced
    //올바른 - complete
    public static void main(String[] args) {
        System.out.println(new Solution().solution("(()())()"));
        System.out.println(new Solution().solution(")("));
        System.out.println(new Solution().solution("()))((()"));

    }

    static class Solution {

        public String solution(String p) {
            if (p.isEmpty()) {
                return "";
            }

            int idx = minBalancedIdx(p);
            String u = p.substring(0, idx + 1);
            String v = p.substring(idx + 1);

            if (isComplete(u)) {
                return u + solution(v);
            } else {
                return "(" + solution(v) + ")" + proc(u);
            }
        }

        String proc(String p) {
            String substring = p.substring(1, p.length() - 1);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < substring.length(); i++) {
                sb.append(substring.charAt(i) == '(' ? ')' : '(');
            }
            return sb.toString();
        }

        boolean isComplete(String p) {
            Stack<Character> s = new Stack<>();
            s.push('X');
            for (int i = 0; i < p.length(); i++) {
                Character peek = s.peek();
                char cur = p.charAt(i);

                if (peek == '(' && cur == ')') {
                    s.pop();
                } else if (peek == ')' && cur == '(') {
                    return false;
                } else {
                    s.push(cur);
                }
            }

            return s.size() == 1;
        }

        int minBalancedIdx(String p) {
            int o = 0;
            int c = 0;

            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) == '(') o++;
                else if (p.charAt(i) == ')') c++;

                if (o == c) return i;
            }

            throw new RuntimeException();
        }
    }
}
