package 자료구조.큐.prgrms;

import java.util.PriorityQueue;
import java.util.Queue;

public class Main_셔틀버스 {

	public static void main(String[] args) {

	}

	static class Solution {

		static int FIRST_BUS_TIME = 60 * 9;

		public String solution(int n, int t, int m, String[] timetable) {

			Queue<Integer> bus = new PriorityQueue<>();
			for (int i = 0; i < n; i++) {
				bus.add(FIRST_BUS_TIME + t * i);
			}

			


			return "";
		}
	}
}
