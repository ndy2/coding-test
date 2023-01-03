package 단순구현.prgrms;

import java.io.IOException;

public class Main_옹알이 {


    public static void main(String[] args) throws IOException {

        Solution solution = new Solution();
        System.out.println(solution.solution(new String[]{"aya", "yee", "maa", "wyeoo"}));
        System.out.println(solution.solution(new String[]{"ayaye", "uuuma", "ye","yemawoo", "ayaa"}));
    }

    static class Solution {
        public int solution(String[] babbling) {
            int answer = 0;
            for (String s : babbling) {
                int offset = 0;
                while(offset < s.length()){
                    int prevOffset = offset;
                    offset += s.startsWith("aya", offset) ? 3 : 0;
                    offset += s.startsWith("ye", offset) ? 2 : 0;
                    offset += s.startsWith("woo", offset) ? 3 : 0;
                    offset += s.startsWith("ma", offset) ? 2 : 0;
                    if(offset == prevOffset) break;
                }
                answer += offset == s.length() ? 1 : 0;
            }

            return answer;
        }
    }
}
