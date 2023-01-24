import kotlin.math.max
import kotlin.math.min

private val br = System.`in`.bufferedReader()
private fun readIntList() = br.readLine().split(" ").map { it.toInt() }

fun main() {

    val (n, m, r) = readIntList()
    val items = readIntList()

    val distMap = Array(n + 1) { IntArray(n + 1) { 1_000_000_000 } }
    for (i in 1..n) distMap[i][i] = 0

    repeat(r) {
        val (a, b, l) = readIntList()
        distMap[a][b] = min(distMap[a][b], l)
        distMap[b][a] = min(distMap[b][a], l)
    }


    for (j in 1..n) {
        for (i in 1..n) {
            for (k in 1..n) {
                distMap[i][k] = min(distMap[i][k], distMap[i][j] + distMap[j][k])
            }
        }
    }

    var maxAvailableItems = 0
    for (i in 1..n) {
        val sumAvailableItems = distMap[i].mapIndexed { idx, dist ->
            if (dist <= m) items[idx - 1] else 0
        }.sum()
        maxAvailableItems = max(maxAvailableItems, sumAvailableItems)
    }

    println(maxAvailableItems)
}