package 브루트포스.순열.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main_외벽점검 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(12, new int[]{1, 3, 4, 9, 10}, new int[]{3, 5, 7}));

//        System.out.println(new 이분탐색.boj.Solution().solution(12, new int[]{2, 4, 6, 8}, new int[]{1, 1, 1, 1}));
    }

    static class Solution {
        public int solution(int n, int[] weak, int[] dist) {
            List<List<Integer>> weakCases = new ArrayList<>();
            List<Integer> weakList = Arrays.stream(weak).boxed().collect(Collectors.toCollection(LinkedList::new));

            int ws = weak.length;
            weakCases.add(List.copyOf(weakList));
            for (int i = 0; i < ws -1 ; i++) {
                weakList.add(weakList.remove(0) + n);
                weakCases.add(List.copyOf(weakList));
            }

            Arrays.sort(dist);

            int answer = 100_000;

            do {
                for (List<Integer> weaks : weakCases) {
                    int searchIdx = 0;
                    for (int i = 0; i < dist.length; i++) {
                        int from = weaks.get(searchIdx);
                        int to = from + dist[i];
                        searchIdx= upperBound(weaks, to);

                        if(searchIdx == ws){
                            answer = Integer.min(answer, i+1);
                            break;
                        }
                    }
                }
            }while(nextPermutation(dist));

            return answer;
        }

        static int upperBound(List<Integer> data, int target) {
            int begin = 0;
            int end = data.size();

            while(begin < end) {
                int mid = (begin + end) / 2;
                if(data.get(mid) <= target) {
                    begin = mid + 1;
                }
                else {
                    end = mid;
                }
            }
            return end;
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
