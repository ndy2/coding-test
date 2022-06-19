package 비트연산.programmers;

import java.util.Arrays;

public class Main_비밀지도 {

    public static void main(String[] args) {

        System.out.println(
                Arrays.toString(new Solution().solution(5, new int[]{9, 20, 28, 18, 11}, new int[]{30, 1, 21, 17, 28}))
        );
    }

    static class Solution {
        public String[] solution(int n, int[] arr1, int[] arr2) {
            String[] answer = new String[n];

            for (int r = 0; r < n; r++) {
                boolean[] map = new boolean[n];

                int row1 = arr1[r];
                String binaryRow = Integer.toBinaryString(row1);
                for (int c = n - binaryRow.length(); c < n; c++) {
                    map[c] = binaryRow.charAt(c - n + binaryRow.length())=='1';
                }

                int row2 = arr2[r];
                String binaryRow2 = Integer.toBinaryString(row2);
                for (int c = n - binaryRow2.length(); c < n; c++) {
                    map[c] |= binaryRow2.charAt(c - n + binaryRow2.length())=='1';
                }

                StringBuilder sb = new StringBuilder();
                for (int c = 0; c < n; c++) {
                    sb.append(map[c]?'#':' ');
                }
                answer[r] = sb.toString();
            }

            return answer;
        }
    }
}
