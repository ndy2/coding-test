package 이분탐색.boj

import kotlin.math.abs

fun main() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val list = br.readLine().split(" ").map { it.toLong() }.sorted().toMutableList()
    var minAbsSum = Long.MAX_VALUE

    var minI = 0L
    var minJ = 0L
    var minK = 0L

    for (i in 0..n - 3) {
        val base = list[i]

        list.removeAt(i)
        var j = 0
        var k = list.lastIndex

        while (j < k) {
            val absSum = abs(base + list[j] + list[k])
            if (absSum < minAbsSum) {
                minAbsSum = absSum

                minI = base
                minJ = list[j]
                minK = list[k]
            }

            if (base + list[j] + list[k] < 0) {
                j++
            } else {
                k--
            }
        }
        list.add(i, base)
    }
    val answer = listOf(minI, minJ, minK).sorted()
    println(answer.joinToString(" "))
}