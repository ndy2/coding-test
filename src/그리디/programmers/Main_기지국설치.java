package 그리디.programmers;

public class Main_기지국설치 {

    public static void main(String[] args) {
        int n = 11;
        int[] stations = {4,11};
        int w = 1;

        System.out.println(new Solution().solution(n,stations,w));
        System.out.println(new Solution().solution(16,new int[]{9},2));

    }

    static class Solution {
        public int solution(int n, int[] stations, int w) {

            int answer = 0;
            int prevRight = 0;
            for (int i = 0; i < stations.length; i++) {
                int curLeft = stations[i] - w;
                int curRight = stations[i] + w;
                int dist = curLeft - prevRight -1;
                if(dist > 0){
                    //기지국 사이에 빈 공간이 있는 경우
                    answer += Math.ceil((double)dist / (2*w+1));
                }
                prevRight = curRight;
            }

            int finalDist = n - prevRight;
            if(finalDist > 0){
                //기지국 사이에 빈 공간이 있는 경우
                answer += Math.ceil((double)finalDist / (2*w+1));
            }

            return answer;
        }
    }
}
