package 시뮬레이션.programmers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.max;

public class Main_추석트래픽 {

    public static void main(String[] args) {
//        System.out.println(
//                new 이분탐색.boj.Solution().solution(new String[]
//                        {"2016-09-15 20:59:57.421 0.351s",
//                                "2016-09-15 20:59:58.233 1.181s",
//                                "2016-09-15 20:59:58.299 0.8s",
//                                "2016-09-15 20:59:58.688 1.041s",
//                                "2016-09-15 20:59:59.591 1.412s",
//                                "2016-09-15 21:00:00.464 1.466s",
//                                "2016-09-15 21:00:00.741 1.581s",
//                                "2016-09-15 21:00:00.748 2.31s",
//                                "2016-09-15 21:00:00.966 0.381s",
//                                "2016-09-15 21:00:02.066 2.62s"})
//        );

        System.out.println(
                new Solution().solution(new String[]
                        {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"})
        );
    }

    static class Solution {

        static class Traffic implements Comparable<Traffic>{
            LocalDateTime from;
            LocalDateTime to;

            public Traffic(LocalDateTime to, Duration time) {
                this.to = to;
                this.from = to.minus(time.minus(Duration.ofMillis(1)));
            }

            public boolean overLap(Traffic t){
                return t.to.compareTo(from) >= 0 && t.from.compareTo(to) <=0;
            }

            public Traffic beforeTo(){
                return new Traffic(to, Duration.ofSeconds(1L));
            }

            public Traffic afterTo(){
                return new Traffic(to.plus(Duration.ofSeconds(1)), Duration.ofSeconds(1L));
            }

            public Traffic beforeFrom(){
                return new Traffic(from, Duration.ofSeconds(1L));
            }

            public Traffic afterFrom(){
                return new Traffic(from.plus(Duration.ofSeconds(1)), Duration.ofSeconds(1L));
            }

            public List<Traffic> all(){
                return List.of(beforeFrom(), afterFrom(), beforeTo(), afterTo());
            }

            @Override
            public String toString() {
                return "Traffic{" +
                        "from=" + from.toLocalTime() +
                        ", to=" + to.toLocalTime() +
                        '}';
            }

            @Override
            public int compareTo(Traffic o) {
                return from.compareTo(o.from);
            }
        }

        public int solution(String[] lines) {
            int answer = 0;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

            List<Traffic> traffics = Arrays.stream(lines)
                    .map(l -> {
                        LocalDateTime to = LocalDateTime.parse(l.substring(0, 23), formatter);
                        int millis = (int) (Float.parseFloat(l.substring(24, l.length() - 1)) * 1000);
                        Duration time = Duration.of(millis, ChronoUnit.MILLIS);
                        return new Traffic(to, time);
                    }).sorted()
                    .collect(Collectors.toList());


            for (Traffic traffic : traffics) {
                for (Traffic target : traffic.all()) {
                    int count = (int)traffics.stream().filter(t -> t.overLap(target)).count();
                    System.out.println("count = " + count);
                    answer = max(answer, count);
                }
                System.out.println();
            }

            return answer;
        }
    }
}
