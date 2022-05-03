package 문자열처리.programmers;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main_JadenCase문자열만들기 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution("3people   unFollowed me")+".");
        System.out.println(new Solution().solution("for   the     last      week   ")+".");

    }

    static class Solution {
        public String solution(String str) {

            Pattern pattern = Pattern.compile(" +");
            Matcher matcher = pattern.matcher(str);

            str = str.replaceAll(" +", " ");
            List<String> words = Stream.of(str.split(" "))
                    .map(s -> {
                        char c = toUpperCase(s.charAt(0));
                        return c + s.substring(1).toLowerCase();
                    }).collect(Collectors.toList());

            StringBuilder sb = new StringBuilder(words.get(0));
            for (int i = 1; i < words.size(); i++) {
                matcher.find();
                sb.append(matcher.group()).append(words.get(i));
            }
            if(matcher.find()) sb.append(matcher.group());
            return sb.toString();
        }

        private char toUpperCase(char c) {
            return c >= 'a' && c <= 'z' ? (char) (c + ('A' - 'a')) : c;
        }
    }
}
