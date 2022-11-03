package 브루트포스.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_2차원_동전_뒤집기 {

	public static void main(String[] args) {

		System.out.println(new Solution().solution(
				new int[][] {
						{1, 0, 1},
						{0, 0, 0},
						{0, 0, 0}
				}, new int[][] {
						{1, 0, 1},
						{0, 0, 0},
						{0, 0, 0}
				}
				));

	}

	static class Solution {

		int[][] beginning;
		int[][] target;
		int h;
		int w;

		public int solution(int[][] beginning, int[][] target) {
			init(beginning, target);
			if(isDone(beginning)){
				return 0;
			}

			int maxFlipCount = h + w;
			for (int flipCount = 1; flipCount <= maxFlipCount / 2; flipCount++) {

				int[] indices = new int[maxFlipCount];
				Arrays.fill(indices, maxFlipCount - flipCount, maxFlipCount, 1);

				do {
					List<Integer> flipIndices = getFlipIndices(maxFlipCount, indices);
					int[][] cur = cloneBeginning();
					flipIndices.forEach(i -> flipRowOrCol(cur, i));

					if (isDone(cur)) {
						return flipCount;
					}
				}
				while (nextPermutation(indices));
			}

			return -1;
		}

		private void flipRowOrCol(int[][] cur, int idx) {
			// Flip row
			if (idx < h) {
				for (int c = 0; c < w; c++) {
					cur[idx][c] = flipCoin(cur[idx][c]);
				}
			}
			// Flip Col
			else {
				for (int r = 0; r < h; r++) {
					cur[r][idx - h] = flipCoin(cur[r][idx - h]);
				}
			}
		}

		private int flipCoin(int coin) {
			return coin == 0 ? 1 : 0;
		}

		private void init(int[][] beginning, int[][] target) {
			h = beginning.length;
			w = beginning[0].length;
			this.beginning = beginning;
			this.target = target;
		}

		private List<Integer> getFlipIndices(int maxFlipCount, int[] indices) {
			List<Integer> flipIndices = new ArrayList<>();
			for (int i = 0; i < maxFlipCount; i++) {
				if (indices[i] == 1) {
					flipIndices.add(i);
				}
			}
			return flipIndices;
		}

		private int[][] cloneBeginning() {
			int[][] clone = new int[h][w];
			for (int r = 0; r < h; r++) {
				clone[r] = Arrays.copyOf(beginning[r], w);
			}
			return clone;
		}

		boolean isDone(int[][] cur) {
			for (int r = 0; r < h; r++) {
				for (int c = 0; c < w; c++) {
					if (cur[r][c] != target[r][c]) {
						return false;
					}
				}
			}
			return true;
		}

		boolean nextPermutation(int[] origin) {
			int N = origin.length;
			int i = N - 1;
			while (i > 0 && origin[i - 1] >= origin[i]) --i;
			if (i == 0) return false;

			int j = N - 1;
			while (origin[i - 1] >= origin[j]) --j;

			int tmp = origin[i - 1];
			origin[i - 1] = origin[j];
			origin[j] = tmp;

			int k = N - 1;
			while (i < k) {
				tmp = origin[i];
				origin[i] = origin[k];
				origin[k] = tmp;
				++i;
				--k;
			}
			return true;
		}
	}
}
