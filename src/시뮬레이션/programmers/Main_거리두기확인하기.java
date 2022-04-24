package 시뮬레이션.programmers;


import java.util.Arrays;

public class Main_거리두기확인하기 {

    public static void main(String[] args) {

        System.out.println(
                Arrays.toString(new Solution().solution(new String[][]{
                        {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                        {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                        {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                        {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                        {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
                }))
        );

    }


    static class Solution {

        //우, 좌, 아래, 위
        int[] dy = {0, 0, 1, -1};
        int[] dx = {1, -1, 0, 0};

        //우측아래, 좌측아래, 우측위, 좌측위
        int[] diagY = {1, 1, -1, -1};
        int[] diagX = {1, -1, 1, -1};

        int[][] relDir = {{0, 2}, {1, 2}, {0, 3}, {1, 3}};

        int[] ans = new int[5];
        char[][] map = new char[5][5];

        public int[] solution(String[][] places) {
            Arrays.fill(ans, 1);

            for (int i = 0; i < 5; i++) {
                initMap(places[i]);

                for (int r = 0; r < 5; r++) {
                    for (int c = 0; c < 5; c++) {

                        if (map[r][c] != 'P') continue;

                        for (int d = 0; d < 4; d++) {
                            int ty = r + dy[d];
                            int tx = c + dx[d];

                            if (!isValid(ty, tx)) continue;

                            if (map[ty][tx] == 'P') {
                                ans[i] = 0;
                                break;
                            }
                        }
                        if (ans[i] == 0) break;

                        for (int d = 0; d < 4; d++) {
                            int ty = r + diagY[d];
                            int tx = c + diagX[d];

                            if (!isValid(ty, tx)) continue;

                            if (map[ty][tx] == 'P') {
                                for (int dir : relDir[d]) {
                                    int tty = r + dy[dir];
                                    int ttx = c + dx[dir];
                                    if (map[tty][ttx] == 'O') {
                                        ans[i] = 0;
                                        break;
                                    }
                                }
                            }
                            if (ans[i] == 0) break;
                        }

                        for (int d = 0; d < 4; d++) {
                            int ty = r + 2* dy[d];
                            int tx = c + 2* dx[d];

                            if(!isValid(ty, tx)) continue;

                            if(map[ty][tx] == 'P'){
                                int tty = r + dy[d];
                                int ttx = c + dx[d];

                                if(map[tty][ttx]=='O'){
                                    ans[i] = 0;
                                    break;
                                }
                            }
                        }

                    }
                    if (ans[i] == 0) break;
                }

            }

            return ans;
        }

        private boolean isValid(int r, int c) {
            return r >= 0 && r < 5 && c >= 0 && c < 5;
        }

        private void initMap(String[] place) {
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    map[r][c] = place[r].charAt(c);
                }
            }
        }
    }
}
