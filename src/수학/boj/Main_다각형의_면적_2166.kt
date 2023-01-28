import kotlin.math.abs
import kotlin.math.roundToInt
import kotlin.math.roundToLong

fun main() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val x = LongArray(n)
    val y = LongArray(n)

    for (i in 0..n - 1) {
        val (xi, yi) = br.readLine().split(" ").map { it.toLong() }
        x[i] = xi
        y[i] = yi
    }

    var answer = 0L
    for (i in 0..n - 2) {
        val a = x[i] * y[i + 1]
        answer += a
        val b = x[i + 1] * y[i]
        answer -= b
    }

    answer += x[n - 1] * y[0]
    answer -= x[0] * y[n - 1]
    print("%.1f".format(abs(answer).toDouble() / 2))
}