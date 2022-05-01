package 그리디.boj;

import java.io.*;
import java.util.*;

public class Main_회의실배정_1931 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Bar implements Comparable<Bar>{
        int begin;
        int end;

        public Bar(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        public int compareTo(Bar o) {
            return end != o.end? end - o.end : begin - o.begin;
        }
    }

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        Queue<Bar> q = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            String[] beginEnd = br.readLine().split(" ");
            int begin = Integer.parseInt(beginEnd[0]);
            int end = Integer.parseInt(beginEnd[1]);
            q.offer(new Bar(begin, end));
        }

        int ans = 0;
        int prevEnd = 0;
        while(!q.isEmpty()){
            Bar cur = q.poll();
            if(prevEnd <= cur.begin){
                ans++;
                prevEnd = cur.end;
            }
        }
        System.out.println(ans);
    }
}
