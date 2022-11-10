package dp.programmers;

public class Main_3xn타일링 {

	public static void main(String[] args) {

		System.out.println(new Solution().solution(4));
		System.out.println(new Solution().solution(6));
	}

	static class Solution {
		private static final int mod = 1_000_000_007;
		public int solution(int n) {
			long adder = 0;

			long[] arr = new long [5001];
			arr[0] = 0;
			arr[2] = 3;
			for(int i = 4; i <= n; i = i+2) {
				arr[i] = (3*arr[i-2] + 2*(adder+1))%mod;
				adder = (adder + arr[i-2])%mod;
			}
			return (int) arr[n]%mod;
		}
	}
}
