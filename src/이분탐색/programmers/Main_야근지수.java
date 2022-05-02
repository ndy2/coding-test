package 이분탐색.programmers;

import java.util.*;
import java.util.stream.Collectors;

public class Main_야근지수 {


    public static void main(String[] args) {

        System.out.println(new Solution().solution(3, new int[]{1, 1}));

    }

    static class Solution {
        public long solution(int n, int[] works) {

            int r = Arrays.stream(works).max().getAsInt();
            int l = 0;

            while(l<r){
                int m = (l+r)/2;
                int workAmount = Arrays.stream(works).filter(i -> i>m)
                        .map(i -> (i-m)).sum();

                //일을 너무 많이 함
                if(workAmount > n){
                    l = m+1;
                }
                //일을 너무 적게 함
                else{
                    r = m;
                }

            }
            int height = l;



            long workAmount = Arrays.stream(works)
                    .mapToLong(i -> i)
                    .map(i -> (i-height)>0?i-height:0)
                    .sum();

            long canWorkMore = n - workAmount;
            Queue<Integer> workDone = Arrays.stream(works)
                    .map(i -> (i - height) > 0 ? height : i)
                    .boxed()
                    .collect(Collectors.toCollection(() ->new PriorityQueue<>(Comparator.<Integer>reverseOrder())));

            while(canWorkMore-->0){
                Integer poll = workDone.poll();
                workDone.add(poll-1);
            }

            long answer = 0;
            while(!workDone.isEmpty()){
                Integer cur = workDone.poll();
                if(cur<=0) continue;
                answer += (long) cur * (long) cur;
            }
            return answer;
        }
    }
}
