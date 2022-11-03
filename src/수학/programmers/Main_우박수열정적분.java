package 수학.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_우박수열정적분 {

	public static void main(String[] args) {

		System.out.println(Arrays.toString(new Solution().solution(5, new int[][] {
				{0, 0}, {0, -1}, {2, -3}, {3, -3}
		})));
	}

	static class Solution {

		public double[] solution(int k, int[][] ranges) {
			List<Double> sum = getSum(k);
			return getAnswer(ranges, sum);
		}

		// resolve sub sum based on Collatz conjecture
		private List<Double> getSum(int k) {
			List<Double> sum = new ArrayList<>();
			double prevSum = 0;
			sum.add(prevSum);

			while (k != 1) {
				int prevY = k;
				if (k % 2 == 0) {
					k /= 2;
				}
				else {
					k = 3 * k + 1;
				}
				int curY = k;

				double area = (double) (prevY + curY) / 2;
				double curSum = prevSum + area;

				sum.add(curSum);
				prevSum = curSum;
			}
			return sum;
		}

		private double[] getAnswer(int[][] ranges, List<Double> sum) {
			int len = sum.size();

			double[] answer = new double[ranges.length];
			for (int i = 0; i < ranges.length; i++) {
				int a = ranges[i][0];
				int b = len + ranges[i][1] - 1;

				answer[i] = a <= b ? sum.get(b) - sum.get(a) : -1;
			}
			return answer;
		}
	}
}
