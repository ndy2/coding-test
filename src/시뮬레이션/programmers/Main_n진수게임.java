package 시뮬레이션.programmers;

import java.util.Stack;

public class Main_n진수게임 {

    public static void main(String[] args) {

        System.out.println(new Solution().solution(16, 16, 2, 1));
        System.out.println(new Solution().solution(16, 16, 2, 2));

//        System.out.println(new Solution().solution(2, 4, 2, 1));
    }

    static class Solution {
        public String solution(int n, int t, int m, int p) {
            StringBuilder answer = new StringBuilder();

            int curNum = -1;
            int cnt = 0;
            int prevIdx = -1;
            char[] prev = new char[0];
            while (cnt < m*t) {
                int curIdx=-1;
                if(prevIdx < prev.length - 1){
                    //can use prev
                    curIdx = prevIdx+1;
                }else if(prevIdx == prev.length - 1){
                    //need to retrieve chars
                    curNum ++;
                    char[] cur = num2orderN(curNum, n);
                    curIdx = 0;
                    prev = cur;
                }

                if(isTubeTurn(cnt, m, p)){
                    answer.append(prev[curIdx]);
                }

                prevIdx = curIdx;
                cnt++;
            }
            return answer.toString();
        }

        boolean isTubeTurn(int cnt, int m, int p){
            return cnt%m==p-1;
        }

        char[] num2orderN(int num, int n) {
            if(num==0) return new char[]{'0'};
            Stack<Integer> s = new Stack<>();
            while (num > 0) {
                int r = num % n;
                num /= n;
                s.push(r);
            }
            int len = s.size();
            char[] ret = new char[len];
            for (int i = 0; i < len; i++) {
                Integer pop = s.pop();
                ret[i] = pop >= 10 ? (char) ('A' - 10 + pop) : (char) ('0' + pop);
            }
            return ret;
        }
    }
}
