import kotlin.math.max

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val a = ("0 " + br.readLine()).split(" ").map { it.toInt() }.toIntArray()
    val dp = IntArray(n + 1)

    for (i in 1..n) {
        val cur = a[i]
        var maxPrevLen = 0
        for (j in 0..i - 1) {
            if (a[j] < cur) {
                maxPrevLen = max(maxPrevLen, dp[j])
            }
        }
        dp[i] = maxPrevLen + 1
    }
    println(dp.max())
}