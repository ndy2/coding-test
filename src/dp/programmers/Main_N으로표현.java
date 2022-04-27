package dp.programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main_N으로표현 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(5, 12));
    }

    static class Solution {
        public int solution(int N, int number) {

            Set<Integer> history = new HashSet<>();
            List<Set<Integer>> rowHistory = new ArrayList<>(9);
            rowHistory.add(new HashSet<>());
            for (int row = 1; row <= 8; row++) {
                HashSet<Integer> curHistory = new HashSet<>();
                rowHistory.add(curHistory);
                int cons = cons(row, N);
                if(cons == number){
                    return row;
                }
                curHistory.add(cons);

                for (int i = 1; i <= row/2  ; i++) {
                    Set<Integer> history1 = rowHistory.get(i);
                    Set<Integer> history2 = rowHistory.get(row - i);
                    for (int col1 : history1) {
                        for (int col2 : history2) {

                            addIfNotDuplicated(history, curHistory, col1 + col2);
                            addIfNotDuplicated(history, curHistory, col1 - col2);
                            addIfNotDuplicated(history, curHistory, col2 - col1);
                            addIfNotDuplicated(history, curHistory, col1 * col2);
                            addIfNotDuplicated(history, curHistory, col1 / col2);
                            addIfNotDuplicated(history, curHistory, col2 / col1);

                        }
                    }
                }
                if(curHistory.contains(number)) return row;

            }

            return -1;
        }

        void addIfNotDuplicated(Set<Integer> history, HashSet<Integer> curHistory, int add) {
            if(!history.contains(add) && add>=1) {
                curHistory.add(add);
                history.add(add);
            }
        }

        int cons(int i, int N) {
            int ret = 0;
            while(i--!=0 ){
                ret += N * Math.pow(10, i);
            }
            return ret;
        }
    }
}

