package bfsdfs.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main_빛의경로사이클 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(new String[]{
                "L"
        })));
    }

    static class Solution {
        List<Integer> answer = new ArrayList<>();

        int h, w;
        char[][] map;
        boolean[][][] visited;

        //오른쪽, 왼쪽, 아래쪽, 위쪽
        int[] dy = {0, 0, 1, -1};
        int[] dx = {1, -1, 0, 0};

        Map<Character, int[]> dir = Map.of(
                'S', new int[]{0, 1, 2, 3},
                'L', new int[]{3, 2, 0, 1},
                'R', new int[]{2, 3, 1, 0}
        );

        public int[] solution(String[] grid) {

            h = grid.length;
            w = grid[0].length();

            initMap(grid);
            visited = new boolean[4][h][w];

            for (int r = 0; r < h; r++) {
                for (int c = 0; c < w; c++) {
                    for (int d = 0; d < 4; d++) {
                        search(d, r, c);
                    }
                }
            }

            return answer.stream().sorted().mapToInt(i -> i).toArray();
        }

        void initMap(String[] grid) {
            map = new char[h][w];
            for (int r = 0; r < h; r++) {
                for (int c = 0; c < w; c++) {
                    map[r][c] = grid[r].charAt(c);
                }
            }
        }

        void search(int d, int r, int c) {
            int len = 0;

            while (true) {
                r = (r + h) % h;
                c = (c + w) % w;
                if (visited[d][r][c]) {
                    if (len > 0) {
                        answer.add(len);
                    }
                    return;
                } else {
                    visited[d][r][c] = true;
                    d = (dir.get(map[r][c]))[d];
                    r += dy[d];
                    c += dx[d];
                    len += 1;
                }
            }
        }
    }

}

