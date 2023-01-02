package 시뮬레이션.programmers

import kotlin.math.pow

fun main() {

    println(Solution().solution(3, 4, 17))
    println(Solution().solution(4, 30, 118))
    println(Solution().solution(3, 1, 125))
    println(Solution().solution(4, 27, 68))
}

class Solution {

    fun solution(n: Int, l: Long, r: Long): Int {
        if (n == 0) {
            return 1
        }

        val len = 5.0.pow(n).toLong()
        val base = len / 5

        val intervals = arrayOf(
            Pair(1L, base * 1),
            Pair(base + 1, base * 2),
            Pair(base * 3 + 1, base * 4),
            Pair(base * 4 + 1, base * 5)
        )

        var answer = 0
        intervals.forEach {
            val (stt, end) = it
            if (r >= stt && end >= l) {
                val nl = if (stt <= l) ((l-stt) % base) +1  else 1
                val nr = if (r <= end) ((r-stt) % base)+1  else base
                answer += solution(n - 1, nl, nr)
            }
        }
        return answer
    }
}