import java.lang.StringBuilder
import kotlin.math.min

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()


    val result = Array(n + 1) { IntArray(n + 1) { 1_000_000_000 } }
    for (i in 1..n) result[i][i] = 0
    repeat(m) {
        val (a, b, c) = br.readLine().split(" ").map { it.toInt() }
        val cur = result[a][b]
        result[a][b] = if (cur == 0) c else min(c, cur)
    }


    for (j in 1..n) {
        for (i in 1..n) {
            for (k in 1..n) {
                result[i][k] = min(result[i][k], result[i][j] + result[j][k])
            }
        }
    }

    val answer = StringBuilder()
    for (r in 1..n) {
        for (c in 1..n) {
            val cost = if (result[r][c] == 1_000_000_000) 0 else result[r][c]
            answer.append(cost).append(' ')
        }
        answer.append('\n')
    }
    println(answer)
}