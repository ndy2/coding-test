package 그리디.programmers;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main_단속카메라 {

    public static void main(String[] args) {

        System.out.println(new Solution().solution(new int[][]
                {{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}}
        ));
    }

    static class Solution {

        static class Pair implements Comparable<Pair>{
            int b, e;

            public Pair(int[] r) {
                this.b = r[0];
                this.e = r[1];
            }

            @Override
            public int compareTo(Pair o) {
                return e-o.e;
            }
        }

        public int solution(int[][] routes) {
            int answer = 0;
            Queue<Pair> q = Stream.of(routes).map(Pair::new).collect(Collectors.toCollection(PriorityQueue::new));

            int curCamera = -30001;
            while(!q.isEmpty()){
                if(curCamera < q.peek().b){
                    curCamera = q.peek().e;
                    answer++;
                }
                q.poll();
            }

            return answer;
        }
    }
}
