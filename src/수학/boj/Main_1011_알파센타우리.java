package 수학.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1011_알파센타우리 {

    /*
     이동 횟수 : 중간 과정 / 총 합
     1 : 1 / 1

     2 : 1 1 /2     최대

     3 : 1 1 1 / 3
     3 : 1 2 1 / 4   최대

     4 : 1 1 1 1 / 4
     4 : 1 1 2 1 / 5
     4 : 1 2 1 1 / 5
     4 : 1 2 2 1 / 6  최대

     5 : 1 1 1 1 1 / 5
     5 : 1 1 1 2 1 / 6
     5 : 1 1 2 2 1 / 7
     5 : 1 2 2 2 1 / 8
     5 : 1 2 3 2 1 / 9 최대

     6 : 1 2 3 3 2 1 / 12 : 최대
     7 : 1 2 3 4 3 2 1 / 16 : 최대
     8 : 1 2 3 4 4 3 2 1 / 20 : 최대

     f(n) = 꽉꽉 눌러 담아 이동할 수 있는 거리
     e.g.) f(5) = 9, f(6) = 12, f(7) = 16, f(8) = 20

     f(n) = Math.floor((n+1) * (n+1) / 4))
     f^(-1)(y) = Math.floor(y*4 +1)) -1
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            String[] xy = br.readLine().split(" ");
            long dist = Long.parseLong(xy[1]) - Long.parseLong(xy[0]);
            sb.append(((long)Math.ceil(Math.sqrt(dist * 4)) - 1)).append('\n');
        }
        System.out.println(sb);
    }
}
