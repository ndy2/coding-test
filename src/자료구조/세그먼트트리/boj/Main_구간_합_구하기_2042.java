package 자료구조.세그먼트트리.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_구간_합_구하기_2042 {

    static long[] arr;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        String[] nmk = br.readLine().split(" ");
        int n = Integer.parseInt(nmk[0]);
        int m = Integer.parseInt(nmk[1]);
        int k = Integer.parseInt(nmk[2]);

        arr = new long[n];
        // create array
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        // setup segment tree
        tree = new long[4 * n];
        initTree(0, n - 1, 1);

        for (int i = 0; i < m + k; i++) {
            String[] abc = br.readLine().split(" ");
            int a = Integer.parseInt(abc[0]);
            int b = Integer.parseInt(abc[1]);
            long c = Long.parseLong(abc[2]);

            if (a == 1) {
                updateTree(0, n - 1, 1, b - 1, c - arr[b - 1]);
                arr[b - 1] = c;
            } else if (a == 2) {
                sb.append(rangeSum(0, n - 1, 1, b - 1, (int)(c - 1))).append('\n');
            }
        }
        System.out.print(sb);
    }

    static long initTree(int start, int end, int idx) {
        if (start == end) {
            tree[idx] = arr[start];
            return tree[idx];
        }
        int mid = (start + end) / 2;
        tree[idx] = initTree(start, mid, idx * 2) + initTree(mid + 1, end, idx * 2 + 1);
        return tree[idx];
    }

    static void updateTree(int start, int end, int idx, int targetIdx, long val) {
        if (targetIdx < start || targetIdx > end) return;

        tree[idx] += val;
        if (start == end) return;

        int mid = (start + end) / 2;
        updateTree(start, mid, idx * 2, targetIdx, val);
        updateTree(mid + 1, end, idx * 2 + 1, targetIdx, val);
    }

    static long rangeSum(int start, int end, int idx, int left, int right) {
        if (left > end || right < start) return 0;

        if (left <= start && right >= end) {
            return tree[idx];
        } else {
            int mid = (start + end) / 2;
            return rangeSum(start, mid, idx * 2, left, right)
                    + rangeSum(mid + 1, end, idx * 2 + 1, left, right);
        }
    }
}
