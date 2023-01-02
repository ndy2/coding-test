package 브루트포스.순열.programmers;

import java.util.*;
import java.util.stream.Collectors;

public class Main_메뉴리뉴얼 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(
                new String[]{"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"},
                new int[]{2, 3, 5})));
    }

    static class Solution {

        boolean[] alphabets = new boolean[26];
        boolean[][] orderMap;
        Map<Character, Integer> char2idx = new HashMap<>();
        Map<Integer, Character> idx2char = new HashMap<>();

        public String[] solution(String[] orders, int[] course) {
            initAlphabets(orders);
            intMaps(alphabets);

            int numMenus = idx2char.size();
            initOrderMap(orders, numMenus);

            List<String> answers = new ArrayList<>();

            //loop course
            for (int c : course) {
                int[] idx = new int[numMenus];
                //setup 이분탐색.boj.getIndices for permutation
                Arrays.fill(idx, numMenus - c, numMenus, 1);

                int maxCnt = 0;
                List<String> answerForCurrentCourse = new ArrayList<>();
                do {
                    List<Integer> targetIndices = getTargetIndices(numMenus, c, idx);

                    int cnt = 0;
                    for (boolean[] menus : orderMap) {
                        boolean eatAll = true;
                        for (Integer target : targetIndices) {
                            if (!menus[target]) {
                                eatAll = false;
                                break;
                            }

                        }
                        if (eatAll) cnt += 1;
                    }
                    if (cnt >= 2) {
                        if (cnt > maxCnt) {
                            answerForCurrentCourse.clear();
                            answerForCurrentCourse.add(toString(targetIndices));
                            maxCnt = cnt;
                        }else if(cnt == maxCnt){
                            answerForCurrentCourse.add(toString(targetIndices));
                        }
                    }

                } while (nextPermutation(idx));
                answers.addAll(answerForCurrentCourse);
                System.out.println("answerForCurrentCourse = " );
                System.out.println("maxCnt = " + maxCnt);
                for (String s : answerForCurrentCourse) {
                    System.out.println("s = " + s);
                }
                System.out.println();
            }


            answers.sort(Comparator.naturalOrder());
            return answers.toArray(new String[0]);
        }

        private String toString(List<Integer> targetIndices) {
            return targetIndices.stream()
                    .map(i -> idx2char.get(i)+"")
                    .collect(Collectors.joining());
        }

        private void initOrderMap(String[] orders, int numMenus) {
            orderMap = new boolean[orders.length][numMenus];
            for (int i = 0; i < orders.length; i++) {
                String order = orders[i];
                for (char c : order.toCharArray()) {
                    orderMap[i][char2idx.get(c)] = true;
                }
            }
        }

        private List<Integer> getTargetIndices(int l, int c, int[] idx) {
            List<Integer> targetIndices = new ArrayList<>(c);
            for (int i = 0; i < l; i++) {
                if (idx[i] == 1) {
                    targetIndices.add(i);
                }
            }
            return targetIndices;
        }

        private void initAlphabets(String[] orders) {
            for (int i = 0; i < orders.length; i++) {
                String order = orders[i];
                for (char c : order.toCharArray()) {
                    alphabets[c - 'A'] = true;
                }
            }
        }

        void intMaps(boolean[] hasAlphabetOfIdx) {
            List<Character> menus = new ArrayList<>();
            for (int i = 0; i < 26; i++) {
                if (hasAlphabetOfIdx[i]) menus.add((char) (i + 'A'));
            }
            for (int idx = 0; idx < menus.size(); idx++) {
                Character menu = menus.get(idx);
                idx2char.put(idx, menu);
                char2idx.put(menu, idx);
            }
        }

        boolean nextPermutation(int[] origin) {
            int N = origin.length;
            int i = N - 1;
            while (i > 0 && origin[i - 1] >= origin[i]) --i;
            if (i == 0) return false;

            int j = N - 1;
            while (origin[i - 1] >= origin[j]) --j;

            int tmp = origin[i - 1];
            origin[i - 1] = origin[j];
            origin[j] = tmp;

            int k = N - 1;
            while (i < k) {
                tmp = origin[i];
                origin[i] = origin[k];
                origin[k] = tmp;
                ++i;
                --k;
            }
            return true;
        }

    }

}
