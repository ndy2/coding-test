package dp.programmers

import java.lang.Integer.min

fun main() {
    println(Solution().solution("1756"))
    println(Solution().solution("5123"))
}

class Solution {

    fun solution(numbers: String): Int {

        val n = numbers.length
        val dp = Array(n + 1) { IntArray(10) { Int.MAX_VALUE } }
        dp[0][4] = 0
        var cur = '6' - '0'
        for (i in 0 until n) {
            val next = numbers[i] - '0'

            for (s in 0..9) {
                if (dp[i][s] == Int.MAX_VALUE) continue

                if (cur == next) {
                    dp[i + 1][s] = min(dp[i + 1][s], dp[i][s] + 1)
                } else if (s == next) {
                    dp[i+1][cur] = min(dp[i+1][cur], dp[i][s] + 1)
                }else{
                    // cur -> next
                    dp[i + 1][s] = min(dp[i + 1][s], dp[i][s] + map[cur][next])

                    // s -> next
                    dp[i + 1][cur] = min(dp[i + 1][cur], dp[i][s] + map[s][next])
                }
            }
            cur = next
        }

        var answer = Int.MAX_VALUE
        dp[n].forEach { answer = min(answer, it) }
        return answer
    }

    val map = arrayOf(
        intArrayOf(1, 7, 6, 7, 5, 4, 5, 3, 2, 3),
        intArrayOf(7, 1, 2, 4, 2, 3, 5, 4, 5, 6),
        intArrayOf(6, 2, 1, 2, 3, 2, 3, 5, 4, 5),
        intArrayOf(7, 4, 2, 1, 5, 3, 2, 6, 5, 4),
        intArrayOf(5, 2, 3, 5, 1, 2, 4, 2, 3, 5),
        intArrayOf(4, 3, 2, 3, 2, 1, 2, 3, 2, 3),
        intArrayOf(5, 5, 3, 2, 4, 2, 1, 5, 3, 2),
        intArrayOf(3, 4, 5, 6, 2, 3, 5, 1, 2, 4),
        intArrayOf(2, 5, 4, 5, 3, 2, 3, 2, 1, 2),
        intArrayOf(3, 6, 5, 4, 5, 3, 2, 4, 2, 1),
    )
}