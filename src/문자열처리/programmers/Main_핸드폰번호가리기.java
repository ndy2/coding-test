package 문자열처리.programmers;

public class Main_핸드폰번호가리기 {

    public static void main(String[] args) {

        System.out.println(new Solution().solution("01033334444"));
    }

    static class Solution {
        public String solution(String p) {
            return "*".repeat(p.length() - 4) +
                    p.substring(p.length() - 4);
        }
    }
}
