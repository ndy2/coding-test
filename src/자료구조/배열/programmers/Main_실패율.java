package 자료구조.배열.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main_실패율 {

    public static void main(String[] args) {

//        System.out.println(Arrays.toString(new 이분탐색.boj.Solution().solution(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3})));
        System.out.println(Arrays.toString(new Solution().solution(5, new int[]{1,1,2,2,2,2})));
    }

    static class Solution {

        static class Stage implements Comparable<Stage> {

            public Stage(int stageNumber, float failureRate) {
                this.stageNumber = stageNumber;
                this.failureRate = failureRate;
            }

            int stageNumber;
            float failureRate;

            @Override
            public int compareTo(Stage o) {
                return o.failureRate != failureRate ?  ((o.failureRate - failureRate) > 0 ? 1 : -1) : stageNumber - o.stageNumber;
            }
        }



        public int[] solution(int N, int[] stageArr) {

            int[] users = new int[N+2];
            for (int i : stageArr) {
                users[i]++;
            }

            int[] sumUsers = new int[N+2];
            for (int i = 1; i <= N+1; i++) {
                sumUsers[i] = users[i] + sumUsers[i-1];
            }

            int totalUser = sumUsers[N+1];
            List<Stage> stages = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                stages.add(new Stage(i, totalUser == sumUsers[i-1] ? 0 : (float)users[i]/(totalUser - sumUsers[i-1])));
            }
            stages.sort(Comparator.naturalOrder());

            int[] answer = new int[N];
            for (int i = 0; i < N; i++) {
                answer[i] = stages.get(i).stageNumber;
            }

            return answer;
        }
    }
}
