package 그래프.programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main_등대 {

	public static void main(String[] args) {


//		System.out.println(new 이분탐색.boj.Solution().solution(8, new int[][] {
//				{1, 2}, {1, 3}, {1, 4}, {1, 5}, {5, 6}, {5, 7}, {5, 8}
//		}));
//
		System.out.println(new Solution().solution(10, new int[][] {
				{4, 1}, {5, 1}, {5, 6}, {7, 6}, {1, 2}, {1, 3}, {6, 8}, {2, 9}, {9, 10}
		}));
	}

	static class Solution {

		public int[] parent;

		public int find(int a) {
			if (parent[a] == a)
				return a;
			else
				return parent[a] = find(parent[a]);
		}

		public void union(int a, int b) {
			a = find(a);
			b = find(b);
			if (a != b)
				parent[b] = a;
		}

		public int solution(int n, int[][] lighthouse) {
			if (n == 0) return 0;
			else if (n == 1 || n == 2 || n == 3) return 1;

			int[] marked = new int[n + 1];
			for (int[] edge : lighthouse) {
				marked[edge[0]]++;
				marked[edge[1]]++;
			}

			// 1. leaf 를 찾는다.
			List<Integer> leafIdx = new ArrayList<>();
			for (int i = 1; i <= n; i++) {
				if (marked[i] == 1) leafIdx.add(i);
			}

			// 2. leaf 에서 방문 할 수 있는 node 찾는다.
			Set<Integer> lightHouse = new HashSet<>();
			for (int[] edge : lighthouse) {
				if (leafIdx.contains(edge[0])) lightHouse.add(edge[1]);
				if (leafIdx.contains(edge[1])) lightHouse.add(edge[0]);
			}

			// 3. light house 에서 방문 할 수 있는 node 의 index 를 모두 모은다.
			Set<Integer> visitable = new HashSet<>();
			for (int[] edge : lighthouse) {
				if (lightHouse.contains(edge[0])) visitable.add(edge[1]);
				if (lightHouse.contains(edge[1])) visitable.add(edge[0]);
			}

			// 4. visitable 하지 않은 엣지들을 모은다.
			List<int[]> nonVisitableEdges = new ArrayList<>();
			for (int[] edge : lighthouse) {
				if (!(visitable.contains(edge[0]) || visitable.contains(edge[1]))) {
					nonVisitableEdges.add(edge);
				}
			}

			parent = new int[n + 1];
			for (int[] edge : nonVisitableEdges) {
				union(edge[0], edge[1]);
			}

			// parent -> edges
			Map<Integer, List<int[]>> map = new HashMap<>();
			for (int[] edge : nonVisitableEdges) {
				int represent = parent[edge[0]];
				if(!map.containsKey(represent)){
					map.put(represent, new ArrayList<>());
				}
				map.get(represent).add(edge);
			}

			int internalAnswer = 0;
			for (List<int[]> edge : map.values()) {
				int[][] split = new int[edge.size()][2];
				for (int i = 0; i < edge.size(); i++) {
					split[i] = edge.get(i);
				}
				internalAnswer += solution(edge.size()+1, split);
			}

			return lightHouse.size() + internalAnswer;
		}
	}

}
