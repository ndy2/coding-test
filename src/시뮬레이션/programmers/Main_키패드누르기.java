package 시뮬레이션.programmers;

import java.util.HashMap;
import java.util.Map;

public class Main_키패드누르기 {

    public static void main(String[] args) {

        System.out.println(new Solution().solution(new int[]{7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2}, "right"));
    }

    static class Solution {

        static class Button {

            int r;
            int c;

            public Button(int r, int c) {
                this.r = r;
                this.c = c;
            }

            int dist(Button button) {
                return Math.abs(r - button.r) + Math.abs(c - button.c);
            }
        }

        int left = 100;
        int right = 200;

        public String solution(int[] numbers, String hand) {
            Map<Integer, Button> buttons = new HashMap<>() {{
                        put(1, new Button(0, 0));
                        put(2, new Button(0, 1));
                        put(3, new Button(0, 2));

                        put(4, new Button(1, 0));
                        put(5, new Button(1, 1));
                        put(6, new Button(1, 2));

                        put(7, new Button(2, 0));
                        put(8, new Button(2, 1));
                        put(9, new Button(2, 2));

                        put(100, new Button(3, 0));
                        put(0, new Button(3, 1));
                        put(200, new Button(3, 2));
            }};


            StringBuilder answer = new StringBuilder();
            for (int number : numbers) {
                if(number == 1 || number == 4 || number == 7){
                    useLeft(answer, number);
                }else if(number ==3 || number == 6 || number ==9){
                    useRight(answer, number);
                }else{
                    Button targetButton = buttons.get(number);

                    int leftDist = targetButton.dist(buttons.get(left));
                    int rightDist = targetButton.dist(buttons.get(right));

                    if(leftDist < rightDist){
                        useLeft(answer, number);
                    }else if(leftDist > rightDist){
                        useRight(answer, number);
                    }else{
                        if("right".equals(hand)){
                            useRight(answer, number);
                        }else{
                            useLeft(answer, number);
                        }
                    }
                }
            }

            return answer.toString();
        }

        void useRight(StringBuilder answer, int number) {
            answer.append("R");
            right = number;
        }

        void useLeft(StringBuilder answer, int number) {
            answer.append("L");
            left = number;
        }
    }
}
