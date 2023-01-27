package dp.boj

private const val mod = 1_000_000_000


fun main() {
    val n = System.`in`.bufferedReader().readLine().toInt()

    // dp[l][e][b] // length, end, bit
    val dp = Array(n + 1) { Array(10) { IntArray(1024) } }
    for (i in 1..9)
        dp[1][i][1.shl(i)] = 1

    for (l in 1..n - 1) {
        for (e in 0..9) {
            for (b in 1..1023) {
                if (e > 0) {
                    val nb = b.or(1.shl(e - 1))
                    dp[l + 1][e - 1][nb] += dp[l][e][b]
                    dp[l + 1][e - 1][nb] %= mod
                }

                if (e < 9) {
                    val nb = b.or(1.shl(e + 1))
                    dp[l + 1][e + 1][nb] += dp[l][e][b]
                    dp[l + 1][e + 1][nb] %= mod
                }
            }
        }
    }

    var answer = 0
    for (e in 0..9) {
        answer += dp[n][e][1023]
        answer %= mod
    }
    println(answer)
}
