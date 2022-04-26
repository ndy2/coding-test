package 자료구조.스택.programmers;

import java.util.Stack;

public class Main_올바른괄호 {

    public static void main(String[] args) {
        System.out.println(
                new Solution().solution("()()")
        );
    }

    static class Solution {
        boolean solution(String s) {
            boolean answer = true;

            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {

                char cur = s.charAt(i);

                try {
                    if (cur == ')') {
                        char top = stack.peek();
                        if (top == '(') {
                            stack.pop();
                        } else {
                            throw new RuntimeException();
                        }
                    } else {
                        stack.push(cur);
                    }
                } catch (RuntimeException e) {
                    answer = false;
                    break;
                }
            }
            if(!stack.isEmpty()){
                answer = false;
            }

            return answer;
        }
    }
}
