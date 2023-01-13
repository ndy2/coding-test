import kotlin.math.max

fun main() {

    val br = System.`in`.bufferedReader()
    val a = br.readLine()
    val b = br.readLine()

    val n = a.length
    val m = b.length

    val dp = Array(n + 1) { IntArray(m + 1) }
    for (r in 1..n) {
        for (c in 1..m) {
            if (a[r - 1] == b[c - 1]) dp[r][c] = dp[r - 1][c - 1] + 1
            else dp[r][c] = max(dp[r - 1][c], dp[r][c - 1])
        }
    }
    println(dp[n][m])
}