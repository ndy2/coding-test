package 그래프.programmers;

import java.util.*;

public class Main_배달 {

    public static void main(String[] args) {

        System.out.println(new Solution().solution(5, new int[][]{{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}}, 3));
    }

    static class Solution {

        static final int INF = 100_000_000;
        int[][] map;
        boolean[] visited;
        int[] cost;
        List<Integer>[] edges;

        public int solution(int N, int[][] road, int K) {
            initMapAndEdges(N, road);
            visited = new boolean[N + 1];
            initCost(N);

            dijkstra(1);


            for (int i = 1; i <= N; i++) {
                System.out.printf("cost[%d] = %d%n", i, cost[i]);
            }
            return (int)Arrays.stream(cost).filter(i -> i <= K).count();
        }

        private void dijkstra(int start) {

            Queue<Integer> q = new LinkedList<>();
            cost[start] = 0;
            q.offer(start);

            while (!q.isEmpty()) {
                int from = q.poll();
                visited[from] = true;

                System.out.print("from " + from+ " -> ");
                for (int to : edges[from]) {
                    System.out.print(to + " ");
                    int origCost = cost[to];
                    int costViaFrom = cost[from] + map[from][to];

                    if(origCost > costViaFrom){
                        //update cost
                        cost[to] = costViaFrom;
                        q.offer(to);
                    }else{
                        if(!visited[to]) {
                            q.offer(to);
                        }
                    }
                }
                System.out.println();

            }

        }

        private void initCost(int N) {
            cost = new int[N + 1];
            Arrays.fill(cost, INF);
        }

        void initMapAndEdges(int N, int[][] road) {
            map = new int[N+1][N+1];

            edges = new List[N+1];
            for (int i = 1; i <= N; i++) {
                edges[i] = new ArrayList<>();
            }

            for (int[] row : map) {
                Arrays.fill(row, INF);
            }

            for (int[] edge : road) {
                int from = edge[0];
                int to = edge[1];
                int cost = edge[2];

                int prevCost = map[from][to];
                if(cost < prevCost){
                    //이번깨 더 저렴함
                    map[from][to] = cost;
                    map[to][from] = cost;

                    edges[from].add(to);
                    edges[to].add(from);
                }
            }
        }
    }
}
