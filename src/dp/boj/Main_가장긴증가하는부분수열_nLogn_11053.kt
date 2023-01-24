import kotlin.math.min

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val a = ("0 " + br.readLine()).split(" ").map { it.toInt() }.toIntArray()
    val dp = IntArray(n + 1)

    var xLen = 1
    val x = IntArray(n + 1) {Int.MAX_VALUE}
    x[0] = 0

    for (i in 1..n) {
        val cur = a[i]
        val idx = x.lowerBound(cur)
        if (idx == xLen-1) {
            x[idx] = cur
            xLen++
        } else {
            x[idx] = min(x[idx], cur)
        }
        dp[i] = idx
    }
    println(dp.max())
}

// a 값 이하인 마지막 인덱스
private fun IntArray.lowerBound(a: Int): Int {

    if (this[size - 1] < a) return size - 1

    var l = 0
    var r = size
    while (l + 1 < r) {
        val mid = (l + r) / 2
        if (this[mid] < a) {
            l = mid
        } else {
            r = mid
        }
    }
    return r
}