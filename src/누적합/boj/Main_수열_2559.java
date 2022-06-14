package 누적합.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;

public class Main_수열_2559 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        StringTokenizer line1 = new StringTokenizer(br.readLine());
        N = parseInt(line1.nextToken());
        K = parseInt(line1.nextToken());
        arr = new int[N];

        StringTokenizer line2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(line2.nextToken());
        }

        int sum = 0;
        for (int i = 0; i < K; i++) {
            sum += arr[i];
        }
        int maxSum = sum;


        for (int i = K; i < N; i++) {
            sum -= arr[i - K];
            sum += arr[i];

            maxSum = max(sum, maxSum);
        }
        System.out.print(maxSum);
    }

}
