package 시뮬레이션.programmers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main_프렌즈4블록 {

    public static void main(String[] args) {

        System.out.println(new Solution().solution(6, 6, new String[]{
                "TTTANT",
                "RRFACC",
                "RRRFCC",
                "TRRRAA",
                "TTMMMF",
                "TMMTTJ"
        }));

    }

    static class Solution {

        public int solution(int m, int n, String[] board) {

            char[][] map = new char[n][m];
            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    map[col][m - row - 1] = board[row].charAt(col);
                }
            }
            int answer = 0;
            while (true) {
                Set<Integer> removed = new HashSet<>();
                for (int r = 0; r < n - 1; r++) {
                    for (int c = 0; c < m - 1; c++) {
                        char c1 = map[r][c];
                        char c2 = map[r][c + 1];
                        char c3 = map[r + 1][c];
                        char c4 = map[r + 1][c + 1];
                        if (c1 != ' ' && c1 == c2 && c2 == c3 && c3 == c4) {
                            removed.add(r * n + c);
                            removed.add(r * n + c + 1);
                            removed.add((r + 1) * n + c);
                            removed.add((r + 1) * n + c + 1);
                        }
                    }
                }

                for (Integer i : removed) {
                    int r = i / n;
                    int c = i % n;

                    map[r][c] = ' ';
                }

                for (int r = 0; r < n; r++) {
                    for (int c = 0; c < m; c++) {
                        if (map[r][c] == ' ') {
                            int nonBlankIdx = c;
                            while (map[r][nonBlankIdx] == ' ') {
                                nonBlankIdx++;
                                if (nonBlankIdx == m) {
                                    c = m;
                                    break;
                                }
                            }
                            if (c == m) {
                                break;
                            }
                            drop(map, r, c, nonBlankIdx);
                        }
                    }
                }

                int curRemoved = removed.size();
                if (curRemoved == 0) break;
                answer += curRemoved;
            }
            return answer;
        }

        void drop(char[][] map, int r, int blankIdx, int nonBlankIdx) {
            map[r][blankIdx] = map[r][nonBlankIdx];
            map[r][nonBlankIdx] = ' ';
        }
    }
}
