package 수학.programmers;

import java.util.Map;

public class Main_모음사전 {

    public static void main(String[] args) {
        System.out.println(
                new Solution().solution("EIO")
        );
    }

    static class Solution {
        public int solution(String word) {

            int l = word.length();

            Map<Character, Integer> char2Idx
                    = Map.of('A', 0, 'E', 1, 'I', 2, 'O', 3, 'U', 4);
            int[] numChild = {781, 156, 31, 6, 1};

            int answer = 0;
            for (int i = 0; i < l; i++) {
                answer += numChild[i] * char2Idx.get(word.charAt(i));
            }
            return answer + l;
        }
    }
}
