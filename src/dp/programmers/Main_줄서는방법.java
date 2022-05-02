package dp.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main_줄서는방법 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(new Solution().solution(3, 5)));
    }

    static class Solution {

        public int[] solution(int n, long k) {
            if(n==1) return new int[]{1};
            long fn = 1;
            List<Integer> remain = new ArrayList<>();
            for (int i = 1; i <= n ; i++) {
                remain.add(i);
                fn *=i;
            }
            k--;

            int idx = 0;
            int[] answer = new int[n];
            while(n>0){
                fn/=n;
                int q = (int)(k/fn);
                answer[idx++]=remain.get(q);
                remain.remove(q);
                k %=fn;
                n--;
            }

            return answer;
        }

    }
}
