package 자료구조.큐.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main_좋은_수_1060 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());

        List<Integer> list = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());
        int n = Integer.parseInt(br.readLine());

        Queue<Interval> q = new PriorityQueue<>();
        int prev = 0;
        for (int i : list) {
            int l = prev + 1;
            int r = i - 1;
            q.add(new Interval(i, i, 0));
            if (r >= l) {
                q.add(new Interval(l, r, r - l));
            }
            prev = i;
        }
        StringBuilder sb = new StringBuilder();
        int amount = 0;


        while (amount < n) {
            Interval interval = q.poll();
            if (interval == null) break;
            int curL = interval.l;
            int curR = interval.r;
            long curCnt = interval.cnt;

            sb.append(curL).append(' ');
            amount++;
            if (amount == n) break;
            if (curL == curR) continue;

            sb.append(curR).append(' ');
            amount++;

            if (curR - curL > 1) {
                int nextL = curL + 1;
                int nextR = curR - 1;
                int nextCnt = nextR - nextL + 1;
                q.add(new Interval(curL + 1, curR - 1, curCnt + nextCnt));
            }
        }

        // interval 이 더 없어서 break 한 경우
        if (amount < n) {
            int largest = list.get(size - 1);
            for (int i = 0; i < n - amount; i++) {
                sb.append(++largest).append(' ');
            }
        }
        System.out.println(sb);
    }

    static class Interval implements Comparable<Interval> {
        int l;
        int r;

        long cnt;

        public Interval(int l, int r, long cnt) {
            this.l = l;
            this.r = r;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Interval o) {
            return cnt == o.cnt ? Integer.compare(l, o.l) : Long.compare(cnt, o.cnt);
        }
    }
}
