package bfsdfs.bfs.programmers;

import java.util.PriorityQueue;
import java.util.Queue;

public class Main_게임맵최단거리 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[][]{
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 0, 1}
        }));
    }

    static class Solution {

        static class Info implements Comparable<Info> {
            int r, c;
            int len;

            public Info(int r, int c, int len) {
                this.r = r;
                this.c = c;
                this.len = len;
            }

            @Override
            public int compareTo(Info o) {
                return len - o.len;
            }
        }

        int H, W;
        boolean[][] visited;

        int[] dy = {1, -1, 0, 0};
        int[] dx = {0, 0, 1, -1};

        public int solution(int[][] maps) {
            H = maps.length;
            W = maps[0].length;

            visited = new boolean[H][W];

            Queue<Info> q = new PriorityQueue<>();
            visited[0][0] = true;
            q.offer(new Info(0, 0, 1));

            int answer = -1;
            while (!q.isEmpty()) {
                Info pos = q.poll();
                if (pos.r == H - 1 && pos.c == W - 1) {
                    answer = pos.len;
                    break;
                }

                for (int d = 0; d < 4; d++) {
                    int ty = pos.r + dy[d];
                    int tx = pos.c + dx[d];

                    if (isValid(ty, tx) && maps[ty][tx] == 1 && !visited[ty][tx]) {
                        visited[ty][tx] = true;
                        q.offer(new Info(ty, tx, pos.len + 1));
                    }
                }
            }

            return answer;
        }

        boolean isValid(int r, int c) {
            return r >= 0 && r < H && c >= 0 && c < W;
        }
    }

}
