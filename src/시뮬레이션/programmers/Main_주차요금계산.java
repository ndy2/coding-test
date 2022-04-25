package 시뮬레이션.programmers;

import java.util.*;

public class Main_주차요금계산 {

    public static void main(String[] args) {

        System.out.println(
                Arrays.toString(new Solution().solution(
                        new int[]{180, 5000, 10, 600},
                        new String[]{"05:34 5961 IN",
                                "06:00 0000 IN",
                                "06:34 0000 OUT",
                                "07:59 5961 OUT",
                                "07:59 0148 IN",
                                "18:59 0000 IN",
                                "19:09 0148 OUT",
                                "22:59 5961 IN",
                                "23:00 5961 OUT"}
                ))
        );
    }

    static class Solution {

        static class Time {
            String time;
            int hour;
            int minute;
            int minuteAll;

            public Time(String time) {
                this.time = time;
                String[] split = time.split(":");
                hour = Integer.parseInt(split[0]);
                minute = Integer.parseInt(split[1]);
                minuteAll = 60 * hour + minute;
            }

            public static int gap(Time a, Time b) {
                return b.minuteAll - a.minuteAll;
            }
        }

        static class Car implements Comparable<Car> {
            String id;
            List<Time> times;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Car car = (Car) o;
                return Objects.equals(id, car.id);
            }

            @Override
            public int hashCode() {
                return Objects.hash(id);
            }

            @Override
            public int compareTo(Car o) {
                return id.compareTo(o.id);
            }

            public int getTime() {
                if (times.size() % 2 == 1) times.add(new Time("23:59"));

                int ret = 0;
                for (int i = 0; i < times.size(); i += 2) {
                    ret += Time.gap(times.get(i), times.get(i + 1));
                }
                return ret;
            }
        }

        public int[] solution(int[] fees, String[] records) {
            Map<String, Car> carMap = new HashMap<>();

            for (String record : records) {
                String[] s = record.split(" ");
                String id = s[1];
                Car car = carMap.get(id);
                if (car == null) {
                    Car newCar = new Car();
                    newCar.id = id;
                    newCar.times = new ArrayList<>();
                    newCar.times.add(new Time(s[0]));
                    carMap.put(id, newCar);
                } else {
                    car.times.add(new Time(s[0]));
                }
            }

            Queue<Car> q = new PriorityQueue<>(carMap.values());

            int[] answer = new int[q.size()];
            int idx = 0;
            while (!q.isEmpty()) {
                Car car = q.poll();
                int time = car.getTime();
                answer[idx++] =
                        fees[1] + (time>fees[0]? (int) Math.ceil((double)(time - fees[0]) / fees[2]) * fees[3] :0);

            }
            return answer;
        }
    }
}
