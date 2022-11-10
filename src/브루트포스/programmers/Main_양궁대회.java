package 브루트포스.programmers;

import java.util.Arrays;

public class Main_양궁대회 {

	public static void main(String[] args) {


		Solution solution = new Solution();
//		System.out.println(Arrays.toString(solution.solution(5, new int[] {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0})));

		int[] a = {0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1};
		int[] b = {1, 1, 2, 0, 1, 2, 2, 0, 0, 0, 0};
		int[] c = {4, 2, 2, 0, 1, 0, 0, 0, 0, 0, 0};

//		System.out.println(solution.getScore(a, b));
//		System.out.println(solution.getScore(a, c));

		System.out.println(Arrays.toString(solution.solution(9, a)));
	}

	static class Solution {
		public int[] solution(int n, int[] info) {

			int size = 10 + n;
			int r = 10;

			int[] idx = new int[size];
			Arrays.fill(idx, size - r, size, 1);

			int maxScoreGap = -2;
			int[] answer = new int[] {-1};

			do {
				int[] count = getCount(idx);
				int scoreGap = getScore(info, count);
				if (scoreGap == -1) continue;

				if (scoreGap > maxScoreGap) {
					maxScoreGap = scoreGap;
					answer = count;
				}
				else if (scoreGap == maxScoreGap) {
					answer = getManyLowScoreHit(answer, count);
				}
			}
			while (nextPermutation(idx));

			return answer;
		}

		int[] getManyLowScoreHit(int[] a, int[] b) {
			for (int i = 10; i >= 0; i--) {
				if (a[i] > b[i]) return a;
				else if (a[i] < b[i]) return b;
			}
			throw new IllegalStateException();
		}

		//라이언이 이긴 경우 점수처
		//어피치가 이긴경우 -1
		int getScore(int[] a, int[] b) {
			int aScore = 0;
			int bScore = 0;
			for (int i = 0; i <= 10; i++) {
				int score = 10 - i;

				if (a[i] >= b[i] && a[i] != 0) {
					aScore += score;
				}
				else if (b[i] > a[i]) {
					bScore += score;
				}
			}


			return bScore > aScore ? bScore - aScore : -1;
		}

		private int[] getCount(int[] idx) {
			int[] result = new int[11];

			int cur = 0;
			int count = 0;
			for (int i = 0; i < idx.length; i++) {
				if (idx[i] == 1) {
					result[cur++] = count;
					count = 0;
				}
				else {
					count++;
				}
			}
			result[cur] = count;
			return result;
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

