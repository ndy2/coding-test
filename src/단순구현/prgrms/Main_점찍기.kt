package 단순구현.prgrms

import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    val solution = Solution_점찍기()
    println(solution.solution(2, 4))
    println(solution.solution(1, 5))
}

class Solution_점찍기 {
    fun solution(k: Int, d: Int): Long {
        var answer = 0L

        var cx = 0
        while (cx <= d) {
            val maxHeight = sqrt(d.toDouble().pow(2) - cx.toDouble().pow(2))
            answer += maxHeight.toInt() / k + 1
            cx += k
        }
        return answer
    }
}