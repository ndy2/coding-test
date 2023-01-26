fun main() {

    val br = System.`in`.bufferedReader()
    val a = br.readLine()
    val b = br.readLine()

    val n = a.length
    val m = b.length

    val dp = Array(n+1) { IntArray(m+1) }

    for (r in 0..n - 1) {
        for (c in 0..m - 1) {
            dp[r + 1][c + 1] = maxOf(
                dp[r][c + 1],
                dp[r + 1][c],
                dp[r][c] + if (a[r] == b[c]) 1 else 0
            )
        }
    }

    var r = n
    var c = m
    val answer = StringBuilder()
    while (answer.length < dp[n][m]) {
        if (a[r - 1] == b[c - 1]) {
            answer.append(a[r - 1])
            r--
            c--
        } else if (dp[r][c] == dp[r][c - 1]) {
            c--
        } else if (dp[r][c] == dp[r - 1][c]) {
            r--
        }
    }
    println(dp[n][m])
    if(dp[n][m]!=0) println(answer.reverse())
}