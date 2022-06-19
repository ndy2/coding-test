package 문자열처리.programmers;

import static java.lang.Character.isAlphabetic;
import static java.lang.Character.toLowerCase;

public class Main_뉴스클러스터링 {

    public static void main(String[] args) {

        System.out.println(new Solution().solution("E=M*C^2", "e=m*c^2"));
    }

    static class Solution {

        int[] a = new int[676];
        int[] b = new int[676];

        int countIntersection;
        int countUnion;

        public int solution(String str1, String str2) {
            initArr(str1, a);
            initArr(str2, b);

            for (int i = 0; i < 676; i++) {
                countIntersection += Integer.min(a[i], b[i]);
                countUnion += Integer.max(a[i], b[i]);
            }

            System.out.println("countIntersection = " + countIntersection);
            System.out.println("countUnion = " + countUnion);
            if (countIntersection == 0 && countUnion == 0) return 65536;

            return countIntersection * 65536 / countUnion;
        }

        void initArr(String str1, int[] arr) {
            for (int i = 0; i < str1.length() - 1; i++) {
                char c1 = str1.charAt(i);
                char c2 = str1.charAt(i + 1);

                if (isAlphabetic(c1) && isAlphabetic(c2)) {
                    c1 = toLowerCase(c1);
                    c2 = toLowerCase(c2);

                    int idx = (c1 - 'a') * 26 + (c2 - 'a');
                    arr[idx]++;
                }
            }
        }
    }
}
