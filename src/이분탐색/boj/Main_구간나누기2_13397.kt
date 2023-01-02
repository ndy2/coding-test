package 이분탐색.boj

import java.lang.Integer.max
import java.lang.Integer.min

/*
val br = System.`in`.bufferedReader()
fun readIntArr(): IntArray {
    return br.readLine()
            .split(" ")
            .map { it.toInt() }
            .toIntArray()
}*/

fun main() {
    val (n, m) = readIntArr()
    val arr = readIntArr()
    Solution(n, m, arr).solve()
}

class Solution(
        val n: Int,
        val m: Int,
        val arr: IntArray
) {
    fun solve() {
        var lo = 0
        var hi = arr.max() - arr.min()

        while (lo < hi) {
            val mid = (lo + hi) / 2
            if (check(mid)) {
                hi = mid
            } else {
                lo = mid + 1
            }
        }

        println(lo)
    }

    // Score 를 target 으로 하기 위해 필요한 구간의 개수가 m 개 이하이다.
    private fun check(target: Int): Boolean {
        var cnt = 0
        var min = arr[0]
        var max = arr[0]

        for (i in 0..arr.size -1) {
            val cur = arr[i]
            min = min(min, cur)
            max = max(max, cur)
            if (max - min > target){
                cnt ++
                min = arr[i]
                max = arr[i]
            }
        }
        cnt++
        return cnt <= m
    }
}