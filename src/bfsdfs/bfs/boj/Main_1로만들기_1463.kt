package bfsdfs.bfs.boj

import java.util.PriorityQueue

private val br = System.`in`.bufferedReader()


fun main() {
    data class Info(
        val cnt: Int,
        val number: Int,
    )
    
    val n = br.readLine().toInt()

    val q = PriorityQueue(compareBy<Info> { it.cnt })
    q.add(Info(0, 1))
    val visited = BooleanArray(1_000_100)
    visited[1] = true
    while (true) {
        val cur = q.poll()
        val number = cur.number

        if (number == n) {
            println(cur.cnt)
            break
        }

        val next1 = number * 3
        if (next1 < 1_000_100 && !visited[next1]) {
            visited[next1] = true
            q.add(Info(cur.cnt + 1, next1))
        }

        val next2 = number * 2
        if (next2 < 1_000_100 && !visited[next2]) {
            visited[next2] = true
            q.add(Info(cur.cnt + 1, next2))
        }

        val next3 = number + 1
        if (next3 < 1_000_100 && !visited[next3]) {
            visited[next3] = true
            q.add(Info(cur.cnt + 1, next3))
        }
    }
}


