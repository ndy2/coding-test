import java.util.PriorityQueue

fun main() {

    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(" ").map { it.toInt() }

    // entry point count
    val arr = IntArray(n + 1)
    arr[0] = -1

    // list of next order
    val edges = Array(n + 1) { mutableListOf<Int>() }

    repeat(m) {
        val order = br.readLine().split(" ").map { it.toInt() }
        order.forEachIndexed { idx, i ->
            run {
                if (idx >= 2) arr[i]++
                if (idx >= 1 && idx < order.size - 1) edges[i].add(order[idx + 1])
            }
        }
    }
    val answer = StringBuilder()
    val added = mutableSetOf<Int>()

    val zeroIndices = arr.indexOfZerosFilter(added)
    val q = PriorityQueue<Int>()
    q.addAll(zeroIndices)
    added.addAll(zeroIndices)

    var count = 0

    while (!q.isEmpty()) {
        val t = q.poll()
        answer.append("$t ")
        count++
        edges[t].forEach { arr[it]-- }

        val nextZeroIndices = arr.indexOfZerosFilter(added)
        q.addAll(nextZeroIndices)
        added.addAll(nextZeroIndices)
    }
    if (count == n) println(answer) else println(0)
}

private fun IntArray.indexOfZerosFilter(processed: Set<Int>): List<Int> {
    val result = mutableListOf<Int>()
    this.forEachIndexed { index, i -> if (i == 0 && !processed.contains(index)) result.add(index) }
    return result
}