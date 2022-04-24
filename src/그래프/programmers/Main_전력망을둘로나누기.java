package 그래프.programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main_전력망을둘로나누기 {

    public static void main(String[] args) {
        System.out.println(
                new Solution().solution(9, new int[][]{
                        {1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}
                })
        );
    }


    static class Solution {

        List<List<Integer>> edges;
        boolean[] visited;
        int cnt = 0;
        int answer = 100;

        public int solution(int n, int[][] wires) {
            initEdges(n, wires);
            for (int[] wire : wires) {
                //remove wire
                int a = wire[0];
                int b = wire[1];
                removeEdge(a, b);

                visited = new boolean[n+1];
                int start = 1;

                dfs(start);
                int curAns = Math.abs((n - cnt) - cnt);
                answer = Math.min(answer, curAns);
                restoreEdge(a, b);
                cnt = 0;

            }

            return answer;
        }

        private void dfs(int from) {
            visited[from] = true;
            cnt++;
            for (Integer to : edges.get(from)) {
                if(!visited[to]){
                    dfs(to);
                }
            }
        }

        private void restoreEdge(int a, int b) {
            edges.get(a).add(b);
            edges.get(b).add(a);
        }

        private void removeEdge(int a, int b) {
            edges.get(a).remove((Object)b);
            edges.get(b).remove((Object)a);
        }

        private void initEdges(int n, int[][] wires) {
            edges = new ArrayList<>(n+1);
            for (int i = 0; i <= n; i++) {
                edges.add(new LinkedList<>());
            }

            for (int[] wire : wires) {
                int a = wire[0];
                int b = wire[1];
                edges.get(a).add(b);
                edges.get(b).add(a);
            }
        }
    }
}
