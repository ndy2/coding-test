package 자료구조.세그먼트트리.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_최솟값과_최댓값_2357 {

    //@formatter:off
    /**
       75 30 100 38 50 51 52 20 81 5
                                    100 (0~9)
                     100 (0~4)                      81(5~9)
                100 (0~2)       50(3~4)        52 (5~7)       81 (8~9)
           75 (0~1) 100 (2) 38(3) 50(4)      52(5~6)  20(7)  81(8)   5(9)
        75(0) 30(1)                       51(5), 52(6)
     */
    //@formatter:on

    static int n, m;
    static int[] arr;

    static int[] maxTree; // segment tree with max values
    static int[] minTree; // segment tree with min values

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        maxTree = new int[4 * n];
        minTree = new int[4 * n];
        initMax(0, n - 1, 1);
        initMin(0, n - 1, 1);

        for (int i = 0; i < m; i++) {
            String[] ab = br.readLine().split(" ");
            int a = Integer.parseInt(ab[0]);
            int b = Integer.parseInt(ab[1]);

            int from = Integer.min(a, b) - 1;
            int to = Integer.max(a, b) - 1;

            int min = getMin(0, n - 1, 1, from, to);
            int max = getMax(0, n - 1, 1, from, to);
            sb.append(min).append(' ');
            sb.append(max).append('\n');
        }

        System.out.print(sb);
    }

    static void initMax(int start, int end, int idx) {
        if (start == end) {
            maxTree[idx] = arr[start];
        } else {
            int mid = (start + end) / 2;
            initMax(start, mid, idx * 2);
            initMax(mid + 1, end, idx * 2 + 1);

            maxTree[idx] = Integer.max(maxTree[2 * idx], maxTree[2 * idx + 1]);
        }
    }


    static void initMin(int start, int end, int idx) {
        if (start == end) {
            minTree[idx] = arr[start];
        } else {
            int mid = (start + end) / 2;
            initMin(start, mid, idx * 2);
            initMin(mid + 1, end, idx * 2 + 1);

            minTree[idx] = Integer.min(minTree[2 * idx], minTree[2 * idx + 1]);
        }
    }


    static int getMax(int start, int end, int idx, int left, int right) {
        if (start > right || end < left) return Integer.MIN_VALUE;

        if (left <= start && end <= right) {
            return maxTree[idx];
        } else {
            int mid = (start + end) / 2;
            return Integer.max(
                    getMax(start, mid, idx * 2, left, right),
                    getMax(mid + 1, end, idx * 2 + 1, left, right)
            );
        }
    }

    static int getMin(int start, int end, int idx, int left, int right) {
        if (start > right || end < left) return Integer.MAX_VALUE;

        if (left <= start && end <= right) {
            return minTree[idx];
        } else {
            int mid = (start + end) / 2;
            return Integer.min(
                    getMin(start, mid, idx * 2, left, right),
                    getMin(mid + 1, end, idx * 2 + 1, left, right)
            );
        }
    }
}
