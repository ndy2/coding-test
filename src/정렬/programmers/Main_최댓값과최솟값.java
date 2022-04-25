package 정렬.programmers;

import java.util.Arrays;
import static java.util.Comparator.naturalOrder;

public class Main_최댓값과최솟값 {

    public static void main(String[] args) {
        System.out.println(
                new Solution().solution("1 2 3 4")
        );
    }

    static class Solution {
        public String solution(String s) {
            String answer = "";

            Integer max = Arrays.stream(s.split(" "))
                    .map(Integer::valueOf)
                    .max(naturalOrder()).get();

            Integer min = Arrays.stream(s.split(" "))
                    .map(Integer::valueOf)
                    .min(naturalOrder()).get();

            return min +" " + min;
        }
    }
}
