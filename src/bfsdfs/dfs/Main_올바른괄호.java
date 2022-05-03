package bfsdfs.dfs;

public class Main_올바른괄호 {


    public static void main(String[] args) {
        System.out.println(new Solution().solution(4));
    }

    static class Solution {
        int answer;
        int n;

        public int solution(int n) {
            this.n = n;
            dfs(0, 0);
            return answer;
        }

        private void dfs(int o, int c) {
            if(o==n){
                answer++;
                return;
            }
            if(o>c){
                dfs(o+1,c);
                dfs(o,c+1);
            }else if(o==c){
                dfs(o+1, c);
            }
        }
    }
}
