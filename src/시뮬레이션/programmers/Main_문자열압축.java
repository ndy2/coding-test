package 시뮬레이션.programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Main_문자열압축 {

    public static void main(String[] args) {

        System.out.println(new Solution().solution("a"));
    }

    static class Solution {
        public int solution(String s) {
            int minLen = Integer.MAX_VALUE;

            int length = s.length();
            for (int i = 1; i <= length; i++) {
                int len = 0;
                Queue<String> queue = new LinkedList<>();
                for (int idx = 0; idx < length; idx += i) {
                    queue.offer(s.substring(idx, Integer.min(idx + i, length)));
                }

                String prev = "X";
                int combo = 1;
                while (!queue.isEmpty()) {
                    String cur = queue.poll();
                    if (cur.equals(prev)) {
                        combo++;
                    } else {
                        if (!prev.equals("X")) {
                            len += i;
                            if (combo >= 2) len += String.valueOf(combo).length();
                        }
                        combo = 1;
                    }
                    prev = cur;
                }

                len += prev.length();
                if (combo >= 2) len += String.valueOf(combo).length();

                minLen = Integer.min(minLen, len);
            }


            return minLen;
        }
    }
}
