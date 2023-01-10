import kotlin.math.min

fun main() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val (r0, g0, b0) = br.readLine().split(" ").map { it.toInt() }

    val dp = Array(3) { Array(n) { IntArray(3) { 1_000_000_000 } } }

    dp[0][0][0] = r0
    dp[1][0][1] = g0
    dp[2][0][2] = b0

    for (i in 1..n - 1) {
        val (r, g, b) = br.readLine().split(" ").map { it.toInt() }
        for (c in 0..2) {
            dp[c][i][0] = min(dp[c][i - 1][1], dp[c][i - 1][2]) + r
            dp[c][i][1] = min(dp[c][i - 1][0], dp[c][i - 1][2]) + g
            dp[c][i][2] = min(dp[c][i - 1][0], dp[c][i - 1][1]) + b
        }
    }
    println(
        listOf(
            dp[0][n - 1][1], dp[0][n - 1][2],
            dp[1][n - 1][0], dp[1][n - 1][2],
            dp[2][n - 1][0], dp[2][n - 1][1]
        ).min()
    )
}