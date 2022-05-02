package 시뮬레이션.programmers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main_영어끝말잇기 {

    public static void main(String[] args) {


        System.out.println(Arrays.toString(new Solution().solution(3, new String[]{"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"})));

    }

    static class Solution {

        Set<String> dic = new HashSet<>();

        public int[] solution(int n, String[] words) {
            int row = 0;

            String prev = null;
            for (int i = 0; i < words.length; i++) {
                int col = i % n;
                if (col == 0) row++;

                String word = words[i];

                //check duplication
                if (dic.contains(word)) {
                    return new int[]{col + 1, row};
                } else {
                    //check validity
                    if (prev != null && lastChar(prev) != firstChar(word)) {
                        return new int[]{col + 1, row};
                    }
                    dic.add(word);
                }
                prev = word;
            }

            return new int[]{0, 0};
        }

        char lastChar(String word) {
            return word.charAt(word.length() - 1);
        }

        char firstChar(String word) {
            return word.charAt(0);
        }
    }
}
