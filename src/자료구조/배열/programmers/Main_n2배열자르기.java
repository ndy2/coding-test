package 자료구조.배열.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.ceil;

public class Main_n2배열자르기 {

    public static void main(String[] args) {

        System.out.println(
                Arrays.toString(new Solution().solution(3, 2, 5))
        );
    }

    static class Solution {
        public int[] solution(int n, long left, long right) {
            left++;
            right++;
            int len = (int) (right - left + 1);
            int[] answer = new int[len];

            for (long num = left; num <= right; num++) {
                int r = (int) ceil((double) num / n) - 1;
                int c = (int) ((num - 1) % n);
                answer[(int)(num - left)] = c <= r ? r + 1 : c + 1;
            }

            return answer;
        }
    }
}
