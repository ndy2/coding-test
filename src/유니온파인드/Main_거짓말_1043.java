package 유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main_거짓말_1043 {

    static List<Integer>[] parties;

    // union find ?
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        int[] orig = Arrays.stream(br.readLine().split(" "))
                .skip(1)
                .mapToInt(Integer::parseInt)
                .toArray();

        parties = new List[m];
        for (int i = 0; i < m; i++) {
            parties[i] = Arrays.stream(br.readLine().split(" "))
                    .skip(1)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }

        new Solution(n, m, orig, parties).solve();
    }

    static class Solution {

        int n;
        int m;
        int[] orig;
        List<Integer>[] parties;
        int[] parent;

        public Solution(int n, int m, int[] orig, List<Integer>[] parties) {
            this.n = n;
            this.m = m;
            this.orig = orig;
            this.parties = parties;
            this.parent = new int[n+1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
        }

        public void solve() {
            // step 0. handle trivial case
            if(orig.length==0){
                System.out.print(m);
                return;
            }

            // step 1. orig 집합을 모두 union 한다.
            for (int i : orig) {
                union(orig[0], i);
            }

            // step 2. parties 를 순회하며 각각을 union 한다.
            for (List<Integer> party : parties) {
                for (int p : party) {
                    union(party.get(0), p);
                }
            }

            // step 3. parties 를 순회하며 첫 원소의 부모와 orig 첫 원소의 부모를 비교한다.
            int answer = 0;
            for (List<Integer> party : parties) {
                int op = find(orig[0]);
                int pp = find(party.get(0));

                if(op!=pp) answer ++;
            }

            System.out.print(answer);
        }


        int find(int a) {
            if (parent[a] == a) return a;
            else return parent[a] = find(parent[a]);
        }

        void union(int a, int b) {
            a = find(a);
            b = find(b);
            if (a != b) {
                parent[b] = a;
            }

        }
    }

}
