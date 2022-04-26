package 브루트포스.순열.programmers;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main_후보키 {

    public static void main(String[] args) {

        System.out.println(new Solution().solution(new String[][]{
                {"100", "ryan", "music", "2"},
                {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"},
                {"400", "con", "computer", "4"},
                {"500", "muzi", "music", "3"},
                {"600", "apeach", "music", "2"}
        }));
        System.out.println(new Solution().solution(new String[][]{
                {"1", "aaa", "c", "ng"},
                {"1", "bbb", "c", "g"},
                {"1", "aaa", "d", "ng"},
                {"2", "bbb", "d", "ng"}
        }));
    }

    static class Solution {
        public int solution(String[][] relation) {
            List<Integer> remainIdx = Stream.iterate(0, i -> i + 1)
                    .limit(relation[0].length).collect(Collectors.toCollection(ArrayList::new));

            int size = remainIdx.size();
            int targetCount = 1;

            List<Set<Integer>> answer = new ArrayList<>();

            while (size >= targetCount) {
                int[] idx = new int[size];
                Arrays.fill(idx, size - targetCount, size, 1);
                do {
                    List<Integer> targetIdx = resolveIntegers(remainIdx, size, idx);
                    if (isTupleKey(relation, answer, targetIdx)) {
                        answer.add(new HashSet<>(targetIdx));
                    }
                } while (nextPermutation(idx));

                size = remainIdx.size();
                targetCount++;
            }

            return answer.size();
        }

        boolean isTupleKey(String[][] relation, List<Set<Integer>> prevTupleKeys, List<Integer> targetIdx) {
            return isMinimal(prevTupleKeys, targetIdx) && isUnique(relation, targetIdx);
        }

        List<Integer> resolveIntegers(List<Integer> remainIdx, int size, int[] idx) {
            List<Integer> targetIdx = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (idx[i] == 1) targetIdx.add(remainIdx.get(i));
            }
            return targetIdx;
        }

        boolean isMinimal(List<Set<Integer>> prevTupleKeys, List<Integer> targetIdx) {
            for (Set<Integer> prevTupleKey : prevTupleKeys) {
                if (new HashSet<>(targetIdx).containsAll(prevTupleKey)) {
                    return false;
                }
            }
            return true;
        }

        boolean isUnique(String[][] relation, List<Integer> targetIdx) {
            Set<Integer> hashSet = new HashSet<>();
            for (String[] row : relation) {

                String[] strings = targetIdx.stream()
                        .map(i -> row[i]).toArray(String[]::new);
                int hash = Objects.hash((Object[]) strings);

                if (hashSet.contains(hash)) {
                    return false;
                } else {
                    hashSet.add(hash);
                }
            }
            return true;
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
