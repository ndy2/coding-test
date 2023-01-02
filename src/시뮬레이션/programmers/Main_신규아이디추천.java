package 시뮬레이션.programmers;

public class Main_신규아이디추천 {

    public static void main(String[] args) {

//        System.out.println(new 이분탐색.boj.Solution().solution("...!@BaT#*..y.abcdefghijklm"));
//        System.out.println(new 이분탐색.boj.Solution().solution("\"z-+.^.\""));
//        System.out.println(new 이분탐색.boj.Solution().solution("=.="));
//        System.out.println(new 이분탐색.boj.Solution().solution("123_.def"));
        System.out.println(new Solution().solution("abcdefghijklmn.p"));

    }

    static class Solution {
        public String solution(String newId) {
            StringBuilder sub = new StringBuilder(newId.toLowerCase()
                    .replaceAll("[^a-z0-9\\-_.]", "")
                    .replaceAll("\\.\\.+", "."));

            if (sub.charAt(0) == '.') {
                sub = sub.deleteCharAt(0);
            }
            if (sub.length()>0 && sub.charAt(sub.length() - 1) == '.') {
                sub = sub.deleteCharAt(sub.length() - 1);
            }

            if (sub.toString().isBlank()) {
                sub.append("a");
            }

            if (sub.length() >= 16) {
                sub = new StringBuilder(sub.substring(0, 15));
                if (sub.charAt(sub.length() - 1) == '.') {
                    sub = new StringBuilder(sub.substring(0, sub.length() - 1));
                }
            }

            char last = sub.charAt(sub.length() - 1);
            while (sub.length() <= 2) {
                sub.append(last);
            }

            return sub.toString();
        }
    }
}
