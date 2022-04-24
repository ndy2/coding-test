package 그리디.programmers;

import java.util.*;
import java.util.stream.Collectors;

public class Main_숫자게임 {


    public static void main(String[] args) {

        System.out.println(
                new Solution().solution(
                        new int[]{5, 1, 3, 7},
                        new int[]{2, 2, 6, 8}
                )
        );
    }

    static class Solution {
        public int solution(int[] A, int[] B) {
            int answer = 0;

            Queue<Integer> a = Arrays.stream(A).boxed().sorted().collect(Collectors.toCollection(LinkedList::new));
            Queue<Integer> b = Arrays.stream(B).boxed().sorted().collect(Collectors.toCollection(LinkedList::new));


            while(!a.isEmpty() && !b.isEmpty()){
                int numA = a.peek();
                int numB = b.peek();

                if(numA<numB){
                    //B win
                    a.remove();
                    b.remove();
                    answer++;
                }else{
                    //B skip for next win
                    b.remove();
                }
            }


            return answer;
        }
    }
}
