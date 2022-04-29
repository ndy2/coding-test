package 문자열처리.programmers;

public class Main_숫자문자열과영단어 {

    public static void main(String[] args) {

        System.out.println(new Solution().solution("2three45sixseven"));
    }

    static class Solution {
        public int solution(String s) {
            return Integer.parseInt(
                    s.replace("zero", "0")
                            .replace("one", "1")
                            .replace("two", "2")
                            .replace("three", "3")
                            .replace("four", "4")
                            .replace("five", "5")
                            .replace("six", "6")
                            .replace("seven", "7")
                            .replace("eight", "8")
                            .replace("nine", "9")
            );
        }
    }
}
