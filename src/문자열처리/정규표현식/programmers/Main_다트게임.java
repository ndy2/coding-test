package 문자열처리.정규표현식.programmers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main_다트게임 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution("10D#2S*3S"));
    }

    static class Solution {

        static class DartScore {

            int base;
            int times;

            DartScore(int base) {
                this.base = base;
                this.times = 1;
            }

            void star() {
                times *= 2;
            }

            void acha() {
                times *= -1;
            }

            int score() {
                return base * times;
            }
        }

        public int solution(String dartResult) {
            Pattern dartScorePattern = Pattern.compile("(\\d*)([SDT])([*#])?");
            Matcher dartScoreMatcher = dartScorePattern.matcher(dartResult);

            DartScore[] scores = new DartScore[3];
            for (int i = 0; i < 3; i++) {
                if(dartScoreMatcher.find()){
                    int base = Integer.parseInt(dartScoreMatcher.group(1));
                    String bonus = dartScoreMatcher.group(2);
                    int times = bonus.equals("S")?1:
                                bonus.equals("D")?2:3;

                    String option = dartScoreMatcher.group(3);

                    scores[i] = new DartScore((int)Math.pow(base, times));
                    if (option != null) {
                        if(option.equals("*")){
                            if(i!=0){
                                scores[i-1].star();
                            }
                            scores[i].star();
                        }else if(option.equals("#")){
                            scores[i].acha();
                        }
                    }
                }
            }

            return Stream.of(scores).map(DartScore::score).mapToInt(i->i).sum();
        }
    }
}
