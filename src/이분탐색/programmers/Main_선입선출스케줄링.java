package 이분탐색.programmers;

public class Main_선입선출스케줄링 {

	public static void main(String[] args) {

		System.out.println(new Solution().solution(3, new int[] {1, 2, 3}));
	}

//	static class Solution {
//		public int solution(int n, int[] cores) {
//
//			int lo = 0;
//			int hi = 10000 * n;
//			int m = -1;
//
//			while (lo + 1 < hi) {
//
//				int mid = (lo + hi) / 2;
//				int count = countWork(mid, cores);
//
//				System.out.println("mid = " + mid);
//				System.out.println("count = " + count);
//				System.out.println();
//
//				if(count ==4){
//					System.out.println();
//				}
//
//				if (count < n) {
//					lo = mid;
//				}
//				else {
//					hi = mid;
//					m = count;
//				}
//			}
//
//			int time = lo;
//			m -= n;
//
//			System.out.println("time = " + time);
//			System.out.println("m = " + m);
//
//			for (int i = cores.length-1; i > 0; i--) {
//				if (time % cores[i] == 0) {
//					if (m == 0) {
//						return i + 1;
//					}
//					m--;
//				}
//			}
//
//			return -1;
//		}
//
//		private int countWork(int time, int[] cores) {
//			int result = 0;
//			for (int core : cores) {
//				result += time / core + 1;
//			}
//			return result;
//		}
//	}

	static class Solution {
		public int solution(int n, int[] cores) {
			int answer = 0;


			int min = 0; // 시간의 최소값
			int max = 10000*n; // 시간의 최대값

			int time = 0;
			int m = 0;

			while (true) {
				int mid = (min+max)/2;

				int count = calculate(mid, cores);

				System.out.println("mid = " + mid);
				System.out.println("count = " + count);
				System.out.println();

				if(min>max){

					break;
				}

				if (count >= n) { // 해당시간까지 처리한 작업량보다 n이 크면 time과 m갱신
					max = mid-1;
					time = mid;
					m = count;
				}else{
					min = mid+1;
				}
			}

			m-=n; // 처리한 작업량과 n의 차이 갱신

			System.out.println("time = " + time);
			System.out.println("m = " + m);

			for(int i = cores.length-1; i>=0; i--){
				if (time % cores[i] == 0) {
					if (m == 0) {
						answer = i+1;
						break;
					}
					m--;
				}
			}

			return answer;
		}

		static int calculate(int time, int[] cores){

			if (time == 0) { // 시간이 0일 때, 처리할 수 있는 작업량은 코어의 개수
				return cores.length;
			}

			int count = cores.length; // 시간이 0일 때, 처리한 작업량

			for(int i = 0; i<cores.length; i++){ // time까지 코어가 처리할 수 있는 작업량 합산
				count += (time/cores[i]);
			}

			return count;
		}
	}

}
