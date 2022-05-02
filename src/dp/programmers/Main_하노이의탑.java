package dp.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main_하노이의탑 {

    public static void main(String[] args) {

        System.out.println(Arrays.deepToString(new Solution().solution(3)));
    }

    static class Solution {
        public int[][] solution(int n) {

            List<List<Integer>> prev = new ArrayList<>();
            prev.add(List.of(1,3));
            while(--n>0){
                prev = getNext(prev);
            }

            int[][] answer = new int[prev.size()][2];
            for (int i = 0; i < prev.size(); i++) {
                answer[i][0] = prev.get(i).get(0);
                answer[i][1] = prev.get(i).get(1);
            }

            return answer;
        }


        List<List<Integer>> getNext(List<List<Integer>> prev){
            int n = prev.size();
            List<List<Integer>> next = new ArrayList<>(2*n+1);

            for (List<Integer> move : prev) {
                next.add(move.stream().map(i -> i==3?2:i==2?3:1).collect(Collectors.toList()));
            }
            next.add(List.of(1,3));
            for (List<Integer> move : prev) {
                next.add(move.stream().map(i -> i==2?1:i==1?2:3).collect(Collectors.toList()));
            }
            return next;
        }

    }
}
