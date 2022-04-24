package 시뮬레이션.programmers;

import java.util.Map;

public class Main_방문길이 {

    public static void main(String[] args) {

        System.out.println(
                new Solution().solution("RRRRRUUUUUDDDDDDDDDD")
        );
        System.out.println(
                new Solution().solution("ULURRDLLU")
        );
    }

    static class Solution {
        public int solution(String dirs) {

            boolean[][][] visited = new boolean[4][11][11];

            int r = 5, c = 5;

            Map<Character, Integer> char2dir
                    = Map.of('U', 0,
                    'D', 1,
                    'R', 2,
                    'L', 3);
            int[] dy = {-1, 1, 0, 0};
            int[] dx = {0, 0, 1, -1};

            int[] flip = {1, 0, 3, 2};

            int ans = 0;
            for (int i = 0; i < dirs.length(); i++) {
                int dir = char2dir.get(dirs.charAt(i));

                int ty = r + dy[dir];
                int tx = c + dx[dir];

                if (isValid(ty, tx)) {
                    if (!visited[dir][r][c]) {
                        visited[dir][r][c] = true;
                        visited[flip[dir]][ty][tx] = true;
                        ans++;
                    }
                    r += dy[dir];
                    c += dx[dir];
                }
            }

            return ans;
        }

        private boolean isValid(int r, int c) {
            return r >= 0 && r <= 10 && c >= 0 && c <= 10;
        }
    }
}
