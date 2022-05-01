package dp.boj;

import java.io.*;

public class Main_평범한배낭_12865 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        String[] NK = br.readLine().split(" ");
        int N = Integer.parseInt(NK[0]);
        int K = Integer.parseInt(NK[1]);

        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            String[] costVal = br.readLine().split(" ");
            int cost = Integer.parseInt(costVal[0]);
            int val = Integer.parseInt(costVal[1]);

            for (int j = 0; j <= K; j++) {
                if(j < cost){
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = Integer.max(dp[i-1][j], dp[i-1][j-cost] + val);
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
