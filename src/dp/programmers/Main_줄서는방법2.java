package dp.programmers;

import java.util.Arrays;

public class Main_줄서는방법2 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Main_줄서는방법.Solution().solution(20, 20)));
    }

    static class Solution{

        public int[] solution(int n, long k) {
            int[] answer = new int[n];
            for (int i = 0; i < n; i++) {
                answer[i] = i+1;
            }

            while(k-->0){
                nextPermutation(answer);
            }

            return answer;
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
