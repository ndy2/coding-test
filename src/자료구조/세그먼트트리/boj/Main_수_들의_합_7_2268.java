package 자료구조.세그먼트트리.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_수_들의_합_7_2268 {

    static int[] arr;
    static long[] tree;

    // 0 0 0
    // 2 0 0
    // 2 3 0

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        arr = new int[n];
        tree = new long[4 * n];
        for (int i = 0; i < m; i++) {
            String[] abc = br.readLine().split(" ");
            int a = Integer.parseInt(abc[0]);
            int b = Integer.parseInt(abc[1]);
            int c = Integer.parseInt(abc[2]);

            if (a == 0) {
                int from = (b < c) ? b : c;
                int to = (b < c) ? c : b;

                long sum = rangeSum(0, n - 1, 1, from - 1, to - 1);
                sb.append(sum).append('\n');
            } else if (a == 1) {
                update(0, n-1, 1, b-1, c-arr[b-1]);
                arr[b - 1] = c;
            }
        }
        System.out.print(sb);
    }

    static long rangeSum(int start, int end, int idx, int left, int right) {
        if (left > end || right < start) return 0;

        if (left <= start && right >= end) {
            return tree[idx];
        } else {
            int mid = (start + end) / 2;
            return rangeSum(start, mid, idx * 2, left, right) +
                    rangeSum(mid + 1, end, idx * 2 + 1, left, right);
        }
    }

    static void update(int start, int end, int idx, int targetIdx, int amount) {
        if (start > targetIdx || end < targetIdx) return;

        tree[idx] += amount;
        if (start == end) return;

        int mid = (start + end) / 2;
        update(start, mid, idx * 2, targetIdx, amount);
        update(mid + 1, end, idx * 2 + 1, targetIdx, amount);
    }

}
