import java.io.BufferedReader
import java.lang.Math.max

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, m) = readIntArr(br)
    val arrM = readIntArrPad(br)
    val arrC = readIntArrPad(br)

    val len = arrC.sum()
    val dp = Array(101) { IntArray(10001) }

    for (i in 1..n) {
        for (j in 0..len) {
            if (j - arrC[i] >= 0) {
                dp[i][j] = max(dp[i][j], dp[i - 1][j - arrC[i]] + arrM[i])
            }
            dp[i][j] = max(dp[i][j], dp[i - 1][j])
        }
    }
    for (i in 0..len) {
        if (dp[n][i] >= m) {
            println(i)
            break
        }
    }
}

private fun readIntArr(br: BufferedReader) = br.readLine().split(" ").map { it.toInt() }.toIntArray()
private fun readIntArrPad(br: BufferedReader) = ("0 " + br.readLine()).split(" ").map { it.toInt() }.toIntArray()