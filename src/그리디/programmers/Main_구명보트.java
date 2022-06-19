package 그리디.programmers;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Main_구명보트 {

    public static void main(String[] args) {

        System.out.println(new Solution().solution(new int[]{70, 50, 80, 50}, 100));
    }

    static class Solution {
        public int solution(int[] people, int limit) {
            ArrayDeque<Integer> deque = new ArrayDeque<>(people.length);
            Arrays.stream(people).sorted().boxed().forEach(deque::add);

            int answer = 0;
            while(!deque.isEmpty()){
                int n = deque.size();
                if(n>=2){
                    if(deque.getLast() + deque.getFirst() <= limit){
                        deque.removeFirst();
                    }
                }
                deque.removeLast();
                answer++;
            }

            return answer;
        }
    }
}
