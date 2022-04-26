package bfs.programmers;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_경주로건설 {

    public static void main(String[] args) {

//        System.out.println(new Solution().solution(new int[][]{
//                {0, 0, 0},
//                {0, 0, 0},
//                {0, 0, 0}
//        }));

        System.out.println(new Solution().solution(new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 1, 1, 1, 1, 1, 0},
                {1, 0, 0, 1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 1, 1, 1},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 1, 1, 0}
        }));

    }

    static class Solution {
        int[][][] minVisitedPriceWithDirection;

        //아래쪽, 위쪽, 오른쪽, 왼쪽
        int[] dy = {1, -1, 0, 0};
        int[] dx = {0, 0, 1, -1};

        // 100 -> 직진, 500 -> 회전, -1-> 역주행
        int[][] cost = {
                {100, -1, 600, 600},
                {-1, 100, 600, 600},
                {600, 500, 100, -1},
                {600, 500, -1, 100},
                {100, 100, 100, 100}
        };

        int[] flip = {1, 0, 3, 2, -1};

        static class Info implements Comparable<Info> {
            int r, c;
            int d;
            int price;

            public Info(int r, int c, int d, int price) {
                this.r = r;
                this.c = c;
                this.d = d;
                this.price = price;
            }

            @Override
            public int compareTo(Info o) {
                return price - o.price;
            }
        }

        public int solution(int[][] board) {
            int n = board.length;
            minVisitedPriceWithDirection = new int[5][n][n];
            for (int d = 0; d < 5; d++) {
                for (int i = 0; i < n; i++) {
                    Arrays.fill(minVisitedPriceWithDirection[d][i], Integer.MAX_VALUE);
                }
            }

            Queue<Info> q = new PriorityQueue<>();
            Info init = new Info(0, 0, 4, 0);
            q.offer(init);

            int answer = Integer.MAX_VALUE;

            while (!q.isEmpty()) {
                Info pos = q.poll();
                if (minVisitedPriceWithDirection[pos.d][pos.r][pos.c] <= pos.price) continue;
                minVisitedPriceWithDirection[pos.d][pos.r][pos.c] = pos.price;

                if (pos.r == n - 1 && pos.c == n - 1) {
                    answer = Integer.min(answer, pos.price);
                    continue;
                }

                for (int d = 0; d < 4; d++) {
                    int ty = pos.r + dy[d];
                    int tx = pos.c + dx[d];

                    if (d == flip[pos.d]) continue;
                    if (!isValid(ty, tx, n, board)) continue;

                    int tPrice = pos.price + cost[pos.d][d];
                    if (minVisitedPriceWithDirection[pos.d][ty][tx] <= tPrice) continue;

                    Info nextInfo = new Info(ty, tx, d, tPrice);
                    q.offer(nextInfo);
                }

            }
            return answer;
        }

        boolean isValid(int r, int c, int n, int[][] board) {
            return r >= 0 && r < n && c >= 0 && c < n && board[r][c] == 0;
        }
    }
}
