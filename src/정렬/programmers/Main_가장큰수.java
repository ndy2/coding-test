package 정렬.programmers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Main_가장큰수 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{0, 0, 0, 0}));
    }

    static class Solution {

        Comparator<String> comp = (a,b) -> Comparator.<String>naturalOrder().compare(b+a, a+b);

        public String solution(int[] numbers) {
            if(Arrays.stream(numbers).sum() == 0){
                return "0";
            }

            return Arrays.stream(numbers)
                    .mapToObj(String::valueOf)
                    .sorted(comp)
                    .collect(Collectors.joining());
        }
    }
}
