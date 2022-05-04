package 백트래킹.programmers;

import java.util.*;
import java.util.stream.Collectors;

public class Main_리틀프렌즈사천성 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(3, 3, new String[]{
                "DBA", "C*A", "CDB"
        }));
    }

    static class Solution {

        static class Coor {
            int r, c;

            public Coor(int r, int c) {
                this.r = r;
                this.c = c;
            }
        }

        static class Pair implements Comparable<Pair> {
            char tile;
            Coor a, b;

            public Pair(char tile, Coor a) {
                this.tile = tile;
                this.a = a;
            }

            @Override
            public int compareTo(Pair o) {
                return tile - o.tile;
            }
        }

        static class Board {
            char[][] board;
            List<Pair> pieces;
            int n;
            boolean[] removed;
            String answer = "IMPOSSIBLE";

            public Board(int m, int n, String[] board) {
                Map<Character, Pair> map = new HashMap<>();

                this.pieces = new ArrayList<>();
                this.board = new char[m][n];
                for (int r = 0; r < m; r++) {
                    for (int c = 0; c < n; c++) {
                        this.board[r][c] = board[r].charAt(c);
                        char tile = this.board[r][c];
                        if (tile >= 'A' && tile <= 'Z') {
                            if (map.containsKey(tile)) {
                                map.get(tile).b = new Coor(r, c);
                            } else {
                                map.put(tile, new Pair(tile, new Coor(r, c)));
                            }
                        }
                    }
                }

                for (char[] chars : this.board) {
                    System.out.println("chars = " + Arrays.toString(chars));
                }
                pieces = map.values().stream().sorted().collect(Collectors.toList());
                for (Pair piece : pieces) {
                    System.out.println("piece = "+ piece.tile +  " a.r " + piece.a.r + " a.c " + piece.a.c + " b.r " + piece.b.r+" b.c " + piece.b.c);
                }
                this.n = pieces.size();
                System.out.println("n = " + this.n);
                removed = new boolean[this.n];
            }

            void dfs(StringBuilder sb) {
                if (sb.length() == n) {
                    answer = sb.toString();
                }

                for (int i = 0; i < n; i++) {
                    if (!removed[i]) {
                        Pair target = pieces.get(i);
                        //check removable
                        if (removable(target)) {
                            //remove
                            remove(target, i);
                            System.out.println("target.tile = " + target.tile);
                            sb.append(target.tile);
                            dfs(sb);
                            sb.deleteCharAt(sb.length() - 1);
                        }
                    }
                }
            }

            void remove(Pair target, int i) {
                removed[i] = true;
                board[target.a.r][target.a.c] = '.';
                board[target.b.r][target.b.c] = '.';
            }

            boolean removable(Pair target) {
                //같은 행
                if (target.a.r == target.b.r) {
                    for (int c = target.a.c+1; c < target.b.c; c++) {
                        if (board[target.a.r][c] != '.') return false;
                    }
                    return true;
                }
                //같은 열
                else if (target.a.c == target.b.c) {
                    for (int r = target.a.r+1; r < target.b.r; r++) {
                        if (board[r][target.a.c] != '.') return false;
                    }
                    return true;
                }
                //  A
                //B
                else if (target.a.c > target.b.c) {
                    boolean upperValid = true;
                    for (int r = target.a.r; r < target.b.r ; r++) {
                        if(board[r][target.b.c] !='.') upperValid = false;
                    }
                    for (int c = target.b.c; c < target.a.c ; c++) {
                        if(board[target.a.r][c] != '.') upperValid = false;
                    }

                    boolean belowValid = true;
                    for (int r = target.b.r; r > target.a.r ; r--) {
                        if(board[r][target.a.c] !='.') belowValid = false;
                    }
                    for (int c = target.a.c; c > target.b.c ; c--) {
                        if(board[target.b.r][c] != '.') belowValid = false;
                    }
                    return upperValid || belowValid;
                }
                //A
                //  B
                else {
                    boolean upperValid = true;
                    for (int r = target.a.r; r < target.b.r ; r++) {
                        if(board[r][target.b.c] !='.') upperValid = false;
                    }
                    for (int c = target.b.c; c > target.a.c ; c--) {
                        if(board[target.a.r][c] != '.') upperValid = false;
                    }

                    boolean belowValid = true;
                    for (int r = target.b.r; r > target.a.r ; r--) {
                        if(board[r][target.a.c] !='.') belowValid = false;
                    }

                    for (int c = target.a.c; c < target.b.c ; c++) {
                        if(board[target.b.r][c] != '.') belowValid = false;
                    }

                    return upperValid || belowValid;
                }
            }

        }

        public String solution(int m, int n, String[] boardIn) {
            Board board = new Board(m, n, boardIn);
            board.dfs(new StringBuilder());
            return board.answer;
        }
    }
}
