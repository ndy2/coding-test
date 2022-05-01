package 그리디.boj;

import java.io.*;
import java.util.*;

/**
 *  <a href="https://www.acmicpc.net/problem/11047">11047번: 동전</a>
 */
public class Main_동전0_11047 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Integer> coins = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            coins.add(Integer.parseInt(br.readLine()));
        }
        coins.sort(Comparator.reverseOrder());
        int remain = K;
        int ans = 0;
        while(remain > 0){
            int removeCoin = -1;
            for (Integer coin : coins) {
                if(coin <= remain){
                    removeCoin = coin;
                    break;
                }
            }
            int q = remain / removeCoin;
            remain -= q * removeCoin;
            ans +=q;
        }

        System.out.println(ans);
    }
}
