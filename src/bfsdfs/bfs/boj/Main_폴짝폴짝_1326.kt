package bfsdfs.bfs.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue
import java.util.StringTokenizer

fun main() {
    class Info(
        val idx: Int,
        val cnt: Int,
    ) : Comparable<Info> {

        override fun compareTo(other: Info): Int {
            return cnt - other.cnt;
        }
    }

    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val arr = mutableListOf(-1)
    for (i in 1..n) {
        arr.add(st.nextToken().toInt())
    }

    val line = br.readLine().split(" ")
    val a = line[0].toInt()
    val b = line[1].toInt()

    val visited = BooleanArray(n + 1)

    val q = PriorityQueue<Info>()
    q.add(Info(a, 0))
    visited[a] = true

    var answer = -1;
    while (!q.isEmpty()) {
        val cur = q.poll()
        val curIdx = cur.idx
        val curVal = arr[curIdx]

        if (curIdx == b) {
            answer = cur.cnt
            break
        }

        var nextIdx = curIdx
        while (nextIdx > 0) {
            if (!visited[nextIdx]) {
                visited[nextIdx] = true
                q.add(Info(nextIdx, cur.cnt + 1))
            }
            nextIdx -= curVal
        }

        nextIdx = curIdx
        while (nextIdx <= n) {
            if (!visited[nextIdx]) {
                visited[nextIdx] = true
                q.add(Info(nextIdx, cur.cnt + 1))
            }
            nextIdx += curVal
        }
    }
    print(answer)
}

