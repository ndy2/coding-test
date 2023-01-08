package dp.boj

import java.util.*

fun main() {
    val reader = System.`in`.bufferedReader()

    val n = reader.readLine().toInt()
    val a = Array(n) { IntArray(2) }
    val dp = Array(n) { IntArray(n) }
    for (i in 0 until n) {
        val st = StringTokenizer(reader.readLine())
        a[i][0] = st.nextToken().toInt()
        a[i][1] = st.nextToken().toInt()
    }
    for (i in 1 until n) {
        for (j in 0 until n - i) {
            dp[j][j + i] = Int.MAX_VALUE
            for (k in 0 until i) {
                val cost = dp[j][j + k] + dp[j + k + 1][j + i] + a[j][0] * a[j + k][1] * a[j + i][1]
                dp[j][j + i] = Math.min(dp[j][j + i], cost)
            }
        }
    }
    println(dp[0][n - 1])
}
