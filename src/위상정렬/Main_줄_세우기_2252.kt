package 위상정렬

import java.lang.IllegalStateException
import java.lang.StringBuilder

private val br = System.`in`.bufferedReader()
private fun readIntList() = br.readLine().split(" ").map { it.toInt() }

private var n = 0
private var m = 0

private lateinit var edgesMap: Array<MutableSet<Int>>
private lateinit var invEdgesMap: Array<MutableSet<Int>>
private lateinit var visited: BooleanArray

private fun getTarget(): Int {
    for (i in n downTo 0) {
        if (!visited[i] && invEdgesMap[i].isEmpty()) return i
    }
    throw IllegalStateException()
}

fun main() {
    val (_n, _m) = readIntList()
    n = _n
    m = _m

    edgesMap = Array(n + 1) { mutableSetOf() }
    invEdgesMap = Array(n + 1) { mutableSetOf() }
    visited = BooleanArray(n + 1)

    repeat(m) {
        val (a, b) = readIntList()
        edgesMap[a].add(b)
        invEdgesMap[b].add(a)
    }

    val answer = StringBuilder()
    while (true) {
        val target = getTarget()
        if (target == 0) break

        visited[target] = true
        answer.append("$target ")
        val edges = edgesMap[target]
        for (next in edges) {
            invEdgesMap[next].remove(target)
        }
    }

    println(answer)
}
