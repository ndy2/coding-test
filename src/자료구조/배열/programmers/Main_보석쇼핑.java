package 자료구조.배열.programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main_보석쇼핑 {

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(new 이분탐색.boj.Solution().solution(new String[]
//                // 0     1         2     3      4       5           6           7
//                {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"})));
//
//        System.out.println(Arrays.toString(new 이분탐색.boj.Solution().solution(new String[]
//                //    0,   1,   2 ,  3,   4,  5 ,   6,   7   8    9    10   11    12
//                {"D", "R", "A", "A", "A", "A", "A", "A", "R", "C", "D", "A", "A"})));
//
//        System.out.println(Arrays.toString(new 이분탐색.boj.Solution().solution(new String[]
////                0,   1,   2   3      4   5   6
//                {"A", "A", "A", "B", "A", "A", "A"})));
//
//        System.out.println(Arrays.toString(new 이분탐색.boj.Solution().solution(new String[]
//                //    0,   1,
//                {"A", "B", "A"})));

        System.out.println(Arrays.toString(new Solution().solution(new String[]
            //    0,   1,  2,  3,   4
                {"A", "B", "C", "D" ,"E"})));

//        System.out.println(Arrays.toString(new 이분탐색.boj.Solution().solution(new String[]
//                //    0,   1,
//                {"AA", "AB", "AC", "AA", "AC"})));
    }

    static class Solution {
        public int[] solution(String[] gems) {
            int[] answer = {};

            Set<String> gemSet = Arrays.stream(gems).collect(Collectors.toSet());
            int numGems = gemSet.size();

            int minLen = 100_000;
            int minLeft = 100_000;
            int minRight = 100_000;

            Map<String, Integer> gemCount = new HashMap<>();

            int prevRight = 0;
            for (int left = 0; left <= gems.length - numGems; left++) {
                int right = prevRight;

                while(gemCount.size() < numGems && right <= gems.length - 1){
                    add(gems[right], gemCount);
                    right++;
                }

                if(right - left + 1 < minLen && gemCount.size() == numGems){
                    minLen = right- left + 1;
                    minLeft = left;
                    minRight = right;
                }
                System.out.println("left = " + left+ " right = " + (right -1));
                System.out.println("gemCount = " + gemCount);
                System.out.println();
                remove(gems[left], gemCount);
                prevRight = right;
            }

            return new int[]{minLeft, minRight -1};
        }

        void add(String gem, Map<String, Integer> gemCount) {
            if(gemCount.containsKey(gem)) gemCount.put(gem, gemCount.get(gem) + 1);
            else gemCount.put(gem, 1);
        }

        void remove(String gem, Map<String, Integer> gemCount) {
            if(gemCount.get(gem) == 1 ) gemCount.remove(gem);
            else gemCount.put(gem, gemCount.get(gem) - 1);
        }
    }
}
