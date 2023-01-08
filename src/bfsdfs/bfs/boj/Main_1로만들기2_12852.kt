package bfsdfs.bfs.boj

import java.util.PriorityQueue

fun main() {

    class Info(
        val cnt: Int,
        val numbers: List<Int>
    )

    fun copyAndAdd(cur: Info, next1: Int): List<Int> {
        val result = ArrayList(cur.numbers)
        result.add(next1)
        return result
    }


    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val q = PriorityQueue(compareBy<Info> { it.cnt })
    q.add(Info(0, listOf(1)))
    val visited = BooleanArray(1_000_100)
    visited[1] = true
    while (true) {
        val cur = q.poll()
        val number = cur.numbers.last()

        if (number == n) {
            println(cur.cnt)
            println(cur.numbers.reversed().joinToString(" "))
            break
        }

        val next1 = number * 3
        if (next1 < 1_000_100 && !visited[next1]) {
            visited[next1] = true
            q.add(Info(cur.cnt + 1, copyAndAdd(cur, next1)))
        }

        val next2 = number * 2
        if (next2 < 1_000_100 && !visited[next2]) {
            visited[next2] = true
            q.add(Info(cur.cnt + 1, copyAndAdd(cur, next2)))
        }

        val next3 = number + 1
        if (next3 < 1_000_100 && !visited[next3]) {
            visited[next3] = true
            q.add(Info(cur.cnt + 1, copyAndAdd(cur, next3)))
        }
    }

}


