package 단순구현.prgrms;

import java.io.IOException;

public class Main_옹알이2 {


    public static void main(String[] args) throws IOException {

        Solution solution = new Solution();
        System.out.println(solution.solution(new String[]{"aya", "yee", "maa", "wyeoo"}));
        System.out.println(solution.solution(new String[]{"ayaye", "uuuma", "ye","yemawoo", "ayaa"}));
    }

    static class Solution {
        public int solution(String[] babbling) {
            int answer = 0;
            String[] aya = new String[] {"aya", "ye", "woo", "ma"};

            for (String s : babbling) {

                int offset = 0;
                String prev = "";
                while(offset < s.length()){
                    int prevOffset = offset;
                    for(String a : aya){
                        if(s.startsWith(a, offset) && !prev.equals(a)){
                            offset+=a.length();
                            prev = a;
                            break;
                        }
                    }
                    if(offset == prevOffset) break;
                }
                answer += offset == s.length() ? 1 : 0;
            }

            return answer;
        }
    }
}
