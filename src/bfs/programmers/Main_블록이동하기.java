package bfs.programmers;

import java.util.PriorityQueue;
import java.util.Queue;

public class Main_블록이동하기 {

    public static void main(String[] args) {

        System.out.println(
                new Solution().solution(new int[][]{
                        {0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}
                })
        );
    }


    static class Solution {
        static class Info implements Comparable<Info> {
            int r1, c1, d1, r2, c2, d2;
            int cnt;

            @Override
            public int compareTo(Info o) {
                return cnt - o.cnt;
            }

            public Info(int r1, int c1, int d1, int r2, int c2, int d2, int cnt) {
                this.r1 = r1;
                this.c1 = c1;
                this.d1 = d1;
                this.r2 = r2;
                this.c2 = c2;
                this.d2 = d2;
                this.cnt = cnt;
            }

            @Override
            public String toString() {
                return "Info{" +
                        "r1=" + r1 +
                        ", c1=" + c1 +
                        ", d1=" + d1 +
                        ", r2=" + r2 +
                        ", c2=" + c2 +
                        ", d2=" + d2 +
                        ", cnt=" + cnt +
                        '}';
            }

            boolean isDone() {
                return (r1 == h - 1 && c1 == w - 1) || (r2 == h - 1 && c2 == w - 1);
            }
        }

        static int h;
        static int w;

        public int solution(int[][] board) {
            int answer = 0;
            h = board.length;
            w = board[0].length;

            boolean[][][] visited = new boolean[4][h][w];
            //동, 서, 남, 북
            int[] dy = {0, 0, 1, -1};
            int[] dx = {1, -1, 0, 0};
            //ro[d] -> 회전 확인 방향
            int[][] ro = {
                    {2, 3}, //동 -> 북, 남
                    {2, 3}, //서
                    {0, 1}, //남
                    {0, 1}  //북
            };
            int[] flip = {1, 0, 3, 2};

            visited[0][0][0] = true;
            visited[1][0][1] = true;

            Queue<Info> q = new PriorityQueue<>();
            q.offer(new Info(0, 0, 0, 0, 1, 1, 0));

            while (!q.isEmpty()) {
                Info pos = q.poll();
                System.out.println("pos = " + pos);
                if(pos.isDone()){
                    answer = pos.cnt;
                    break;
                }
                for (int d = 0; d < 4; d++) {

                    int nextR1 = pos.r1 + dy[d];
                    int nextC1 = pos.c1 + dx[d];
                    int nextR2 = pos.r2 + dy[d];
                    int nextC2 = pos.c2 + dx[d];

                    if (isValid(nextR1, nextC1) && isValid(nextR2, nextC2)
                            && isRoad(nextR1, nextC1, board) && isRoad(nextR2, nextC2, board)
                            && !visited[pos.d1][nextR1][nextC1] && !visited[pos.d2][nextR2][nextC2]) {

                        visited[pos.d1][nextR1][nextC1] = true;
                        visited[pos.d2][nextR2][nextC2] = true;
                        q.offer(new Info(nextR1, nextC1, pos.d1, nextR2, nextC2, pos.d2, pos.cnt + 1));
                    }
                }

                //coor 1을 기준으로 rotation
                for (int rd : ro[pos.d1]) {
                    // 회전의 진행방향, 대각선 방향
                    int nextD1 = rd;
                    int nextR2 = pos.r1 + dy[rd];
                    int nextC2 = pos.c1 + dx[rd];
                    int nextD2 = flip[rd];

                    int diagR = pos.r1 + dy[rd] + dy[pos.d1];
                    int diagC = pos.c1 + dx[rd] + dx[pos.d1];

                    if (isValid(nextR2, nextC2) && isValid(diagR, diagC)
                            && isRoad(nextR2, nextC2, board) && isRoad(diagR, diagC, board)
                            && !visited[nextD1][pos.r1][pos.c1] && !visited[nextD2][nextR2][nextC2]) {

                        visited[nextD1][pos.r1][pos.c1] = true;
                        visited[nextD2][nextR2][nextC2] = true;
                        q.offer(new Info(pos.r1, pos.c1, nextD1, nextR2, nextC2, nextD2, pos.cnt + 1));
                    }
                }


                //coor 2을 기준으로 rotation
                for (int rd : ro[pos.d2]) {
                    // 회전의 진행방향, 대각선 방향
                    int nextD2 = rd;
                    int nextR1 = pos.r2 + dy[rd];
                    int nextC1 = pos.c2 + dx[rd];
                    int nextD1 = flip[rd];

                    int diagR = pos.r2 + dy[rd] + dy[pos.d2];
                    int diagC = pos.c2 + dx[rd] + dx[pos.d2];

                    if (isValid(nextR1, nextC1) && isValid(diagR, diagC)
                            && isRoad(nextR1, nextC1, board) && isRoad(diagR, diagC, board)
                            && !visited[nextD2][pos.r2][pos.c2] && !visited[nextD1][nextR1][nextC1]) {

                        visited[nextD2][pos.r2][pos.c2] = true;
                        visited[nextD1][nextR1][nextC1] = true;
                        q.offer(new Info(nextR1, nextC1, nextD1, pos.r2, pos.c2, nextD2, pos.cnt + 1));
                    }
                }

            }
            return answer;
        }

        private boolean isValid(int r, int c) {
            return r >= 0 && r < h && c >= 0 && c < w;
        }


        private boolean isRoad(int r, int c, int[][] board) {
            return board[r][c] == 0;
        }
    }
}
