package bfsdfs.bfs.boj

import java.util.*

fun main() {

    val br = System.`in`.bufferedReader()

    repeat(br.readLine().toInt()) {

        val (a, b) = br.readLine().split(" ").map { it.toInt() }
        val codeHistory = CharArray(10000) { 'X' }
        val prevHistory = IntArray(10000) { -2 }

        val q = LinkedList<Int>()
        q.add(a)
        codeHistory[a] = 'X'
        prevHistory[a] = -1

        while (!q.isEmpty() && codeHistory[b] == 'X') {
            val cur = q.poll()

            for ((code, op) in operations) {
                val nextValue = op(cur)
                if (codeHistory[nextValue] == 'X') {
                    codeHistory[nextValue] = code
                    prevHistory[nextValue] = cur
                    q.add(nextValue)
                }
            }
        }

        val sb = StringBuilder()
        var me = b
        while (true) {
            if (me == a) break
            val cmd = codeHistory[me]
            sb.append(cmd)
            me = prevHistory[me]
        }
        println(sb.reverse())
    }
}

private val operations = mapOf(
    'D' to ::double,
    'S' to ::subtract,
    'L' to ::rotateLeft,
    'R' to ::rotateRight
)

private fun double(a: Int): Int {
    return (a * 2) % 10000
}

private fun subtract(a: Int): Int {
    return if (a == 0) 9999 else a - 1
}

private fun rotateLeft(a: Int): Int {

    val d1 = a / 1000
    val d2 = (a % 1000) / 100
    val d3 = (a % 100) / 10
    val d4 = a % 10
    return 1000 * d2 + 100 * d3 + 10 * d4 + 1 * d1
}

private fun rotateRight(a: Int): Int {

    val d1 = a / 1000
    val d2 = (a % 1000) / 100
    val d3 = (a % 100) / 10
    val d4 = a % 10
    return 1000 * d4 + 100 * d1 + 10 * d2 + 1 * d3
}