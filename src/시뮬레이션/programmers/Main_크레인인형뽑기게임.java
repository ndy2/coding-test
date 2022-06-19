package 시뮬레이션.programmers;

import java.util.Stack;

public class Main_크레인인형뽑기게임 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[][]{
                {2, 0, 0, 0, 0},
                {2, 0, 0, 0, 0},
                {2, 0, 0, 0, 0},
                {2, 0, 0, 0, 0},
                {2, 0, 0, 0, 2},
        }, new int[]{1, 5, 3, 5, 1, 2, 1, 4}));
    }

    static class Solution {

        Stack<Integer> stack = new Stack<>();
        Stack<Integer>[] boardStack;

        public int solution(int[][] board, int[] moves) {
            int width = board[0].length;
            boardStack = new Stack[width];

            for (int i = 0; i < width; i++) {
                boardStack[i] = new Stack<>();

                int row = board.length;
                while (row > 0 && board[--row][i] != 0) {
                    boardStack[i].push(board[row][i]);
                }
            }

            int answer = 0;
            stack.push(-1);
            for (int move : moves) {
                if (!boardStack[move - 1].isEmpty()) {
                    Integer pop = boardStack[move - 1].pop();

                    if (stack.peek().equals(pop)) {
                        stack.pop();
                        answer += 2;
                    } else {
                        stack.push(pop);
                    }
                }
            }

            return answer;
        }
    }
}
