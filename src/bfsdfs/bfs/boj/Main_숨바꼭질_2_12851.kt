package bfsdfs.bfs.boj

import java.util.*

// typealias Info = Pair<Int, Int>

fun main() {

    val br = System.`in`.bufferedReader()
    val (a, b) = br.readLine().split(" ").map { it.toInt() }

    val visited = BooleanArray(100002)
    val q = LinkedList<Info>()
    q.add(Info(0, a))

    var minTime = 0
    var minTimeCount = 0
    while (!q.isEmpty()) {
        val (curTime, curX) = q.poll()
        visited[curX] = true

        if (curX == b && minTimeCount > 0 && minTime == curTime) minTimeCount++
        if (curX == b && minTimeCount == 0) {
            minTime = curTime
            minTimeCount++
        }

        for (tx in listOf(curX - 1, curX + 1, 2 * curX)) {
            if (tx < 0 || tx > 100_000) continue
            if (!visited[tx]) q.add(Info(curTime + 1, tx))
        }
    }
    println(minTime)
    println(minTimeCount)
}