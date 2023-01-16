package bfsdfs.bfs.boj

import java.util.PriorityQueue

//info.first = cnt, info.second = value
typealias Info = Pair<Int, Int>

fun main() {


    val (a, b) = System.`in`.bufferedReader().readLine().split(" ").map { it.toInt() }

    // b 에서 거꾸로 오면 경우가 더 적을듯?
    val q = PriorityQueue<Info>(compareBy { it.first })
    q.add(Info(1, b))
    val visited = mutableSetOf(b)

    var answer = -1
    while (!q.isEmpty()) {
        val (curCnt, curValue) = q.poll()

        if (curValue == a) {
            answer = curCnt
            break
        }

        if (curValue % 2 == 0 && !visited.contains(curValue / 2)) {
            visited.add(curValue / 2)
            q.add(Info(curCnt + 1, curValue / 2))
        } else if (curValue % 10 == 1 && !visited.contains(curValue / 10)) {
            visited.add(curValue / 10)
            q.add(Info(curCnt + 1, curValue / 10))
        }
    }
    println(answer)
}