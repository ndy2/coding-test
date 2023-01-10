import java.io.BufferedReader
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

fun main() {

    val br = System.`in`.bufferedReader()
    repeat(readInt(br)) {
        var answer = Double.MAX_VALUE

        val n = readInt(br)
        val p = readArrIntArray(br, n)

        val vectorSum = IntArray(2)
        p.forEach { vectorSum[0] += it[0]; vectorSum[1] += it[1]; }

        val indices = IntArray(n) { i -> if (i >= n / 2) 1 else 0 }
        do {
            val cur = vectorSum.copyOf()
            for ((i, value) in indices.withIndex()) {
                if (value == 1) {
                    cur[0] -= 2 * p[i][0]
                    cur[1] -= 2 * p[i][1]
                }
            }
            answer = min(answer, dist(cur))
        } while (nextPermutation(indices))
        println(answer)
    }
}

private fun dist(v: IntArray): Double {
    return sqrt((v[0]).toDouble().pow(2) + (v[1]).toDouble().pow(2))
}

private fun readInt(br: BufferedReader) = br.readLine().toInt()
private fun readIntArray(br: BufferedReader) = br.readLine().split(" ").map { it.toInt() }.toIntArray()
private fun readArrIntArray(br: BufferedReader, n: Int): Array<IntArray> {
    return IntRange(1, n)
        .map { readIntArray(br) }
        .toTypedArray()
}

private fun nextPermutation(origin: IntArray): Boolean {
    val N = origin.size
    var i = N - 1
    while (i > 0 && origin[i - 1] >= origin[i]) --i
    if (i == 0) return false
    var j = N - 1
    while (origin[i - 1] >= origin[j]) --j
    var tmp = origin[i - 1]
    origin[i - 1] = origin[j]
    origin[j] = tmp
    var k = N - 1
    while (i < k) {
        tmp = origin[i]
        origin[i] = origin[k]
        origin[k] = tmp
        ++i
        --k
    }
    return true
}