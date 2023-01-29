import kotlin.math.min

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
//    val a = ("${Int.MIN_VALUE} " + br.readLine()).split(" ").map { it.toInt() }.toIntArray()

    val lines = mutableListOf(0 to 0)
    val invMap = mutableMapOf(0 to 0)

    repeat(n) {
        val (a, b) = br.readLine().split(" ").map { it.toInt() }
        lines.add(a to b)
        invMap[b] = a
    }
    lines.sortBy { it.first }

    val dp = IntArray(n + 1)

    var xLen = 1
    val x = IntArray(n + 1) { Int.MAX_VALUE }
    x[0] = 0

    for (i in 1..n) {
        val cur = lines[i].second
        val idx = x.lowerBound(cur)
        if (idx == xLen - 1) {
            x[idx] = cur
            xLen++
        } else {
            x[idx] = min(x[idx], cur)
        }
        dp[i] = idx
    }

    val max = dp.max()
    val target = dp.indexOfFirst { it == max }
    var value = max
    var value2 = n - max

    val answer2 = mutableListOf<Int>()

    for (idx in target downTo 0) {
        if (dp[idx] == value) {
            value--
        } else {
            value2--
            answer2.add(lines[idx].first)
        }
    }

    for (idx in target + 1..n) {
        answer2.add(lines[idx].first)
    }

    println(n - max)
    println(answer2.sorted().joinToString("\n"))
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