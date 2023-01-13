package bfsdfs.bfs.boj

import java.util.*


fun main() {
    data class Info(
        val pos: Int,
        val cnt: Int,
    )


    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val snakeAndLadders = buildMap(m) {
        repeat(n + m) {
            val (x, y) = br.readLine().split(" ").map { it.toInt() }
            put(x, y)
        }
    }
    val visited = BooleanArray(101)

    val q = PriorityQueue<Info>(compareBy { it.cnt })
    q.add(Info(1, 0))
    visited[1] = false

    while (!q.isEmpty()) {
        val cur = q.poll()

        if (cur.pos == 100) {
            println(cur.cnt)
            break
        }

        for (i in 1..6) {
            val nextPos = if (snakeAndLadders.containsKey(cur.pos + i)) snakeAndLadders[cur.pos + i]!! else cur.pos + i
            if (nextPos <= 100 && !visited[nextPos]) {
                visited[nextPos] = true
                q.add(Info(nextPos, cur.cnt + 1))
            }
        }
    }
}
