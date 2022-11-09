package 다익스트라.prgrms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

public class Main_미로탈출 {

	public static void main(String[] args) {


		Solution solution = new Solution();

		System.out.println(solution.solution(3, 1, 3, new int[][] {
				{1, 2, 2}, {3, 2, 3}
		}, new int[] {2}));

		System.out.println(solution.solution(4, 1, 4, new int[][] {
				{1, 2, 1}, {3, 2, 1}, {2, 4, 1}
		}, new int[] {2, 3}));

		System.out.println(solution.solution(5, 1, 5, new int[][] {
				{1, 2, 1}, {2, 3, 1}, {3, 2, 1}, {3, 5, 1}, {1, 5, 10}
		}, new int[] {3}));
	}

	static class Solution {

		public int solution(int n, int start, int end, int[][] roads, int[] traps) {

			List<Edge>[] reverseEdgeMap = new ArrayList[n + 1];
			List<Edge>[] edgeMap = new ArrayList[n + 1];
			for (int v = 1; v <= n; v++) {
				edgeMap[v] = new ArrayList<>();
				reverseEdgeMap[v] = new ArrayList<>();
			}

			for (int[] road : roads) {
				int p = road[0];
				int q = road[1];
				int s = road[2];
				Edge edge = new Edge(p, q, s, false);
				edgeMap[p].add(edge);
				reverseEdgeMap[q].add(edge);
			}

			boolean[] trap = new boolean[traps.length];
			int[][] cost = new int[n + 1][(int) Math.pow(2, traps.length)];
			for (int v = 0; v <= n; v++) {
				Arrays.fill(cost[v], 1_000_000_000);
			}
			cost[start][0] = 0;
			BitSet trapBitSet = new BitSet(trap.length);
			List<Integer> trapsList = Arrays.stream(traps).boxed().collect(Collectors.toList());

			Queue<Edge> q = new PriorityQueue<>();
			q.add(new Edge(start, 0, false));

			int answer = Integer.MAX_VALUE;

			while (!q.isEmpty()) {
				Edge cur = q.poll();
				if (cur.p == end) {
					answer = Integer.min(answer, cur.s);
					continue;
				}


				//flip edges
				if (trapsList.contains(cur.p)) {
					int idx = trapsList.indexOf(cur.p);
					trapBitSet.flip(idx);
					edgeMap[cur.p].forEach(Edge::flip);
					reverseEdgeMap[cur.p].forEach(Edge::flip);
				}
				int trapIdx = toInt(trapBitSet);
				if(cur.s > cost[cur.p][trapIdx]) continue;

				for (Edge next : edgeMap[cur.p]) {
					if (next.flipped) continue;
					int nextTrapIdx = getNextTrapIdx(trapIdx, trapsList, next.q);
					if (cost[next.q][nextTrapIdx] > cost[cur.p][trapIdx] + next.s) {
						cost[next.q][nextTrapIdx] = cost[cur.p][trapIdx] + next.s;
						q.add(new Edge(next.q, cost[cur.p][trapIdx] + next.s, false));
					}
				}

				for (Edge next : reverseEdgeMap[cur.p]) {
					if (!next.flipped) continue;
					int nextTrapIdx = getNextTrapIdx(trapIdx, trapsList, next.p);
					if (cost[next.p][nextTrapIdx] > cost[cur.p][trapIdx] + next.s) {
						cost[next.p][nextTrapIdx] = cost[cur.p][trapIdx] + next.s;
						q.add(new Edge(next.p, cost[cur.p][trapIdx] + next.s, true));
					}
				}
			}
			return answer;
		}

		private int getNextTrapIdx(int trapIdx, List<Integer> trapsList, int next) {
			int nextTrapIdx = trapIdx;
			if (trapsList.contains(next)) {
				int idx = trapsList.indexOf(next);
				BitSet bitSet = toBitSet(nextTrapIdx);
				bitSet.flip(idx);
				nextTrapIdx = toInt(bitSet);
			}
			return nextTrapIdx;
		}

		static class Edge implements Comparable<Edge> {
			int q, p, s;
			boolean flipped;

			Edge(int p, int s, boolean flipped) {
				this(p, -1, s, flipped);
			}

			Edge(int p, int q, int s, boolean flipped) {
				this.p = p;
				this.q = q;
				this.s = s;
				this.flipped = flipped;
			}

			void flip() {
				flipped = !flipped;
			}

			int flipIdx() {
				return flipped ? 1 : 0;
			}

			@Override
			public int compareTo(Edge o) {
				return s - o.s;
			}
		}

		static int toInt(BitSet bitSet) {
			int intValue = 0;
			for (int bit = 0; bit < bitSet.length(); bit++) {
				if (bitSet.get(bit)) {
					intValue |= (1 << bit);
				}
			}
			return intValue;
		}

		static BitSet toBitSet(int value) {
			BitSet bitSet = new BitSet();
			int bit = 0;
			while (value > 0) {
				bitSet.set(bit++, value % 2 == 1);
				value >>= 1;
			}
			return bitSet;
		}
	}
}
