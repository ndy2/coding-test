package 자료구조.큐.boj

import java.util.*

fun main() {

    val br = System.`in`.bufferedReader()

    repeat(br.readLine().toInt()) {

        val q = LinkedList<Info>()
        val countMap = IntArray(9)

        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        val line = br.readLine().split(" ").map { it.toInt() }
        for (i in 0..n - 1) {
            countMap[line[i] - 1]++
            q.add(Info(line[i], i == m))
        }

        var answer = 0
        while (true) {
            val info = q.pollFirst()
            val p = info.priority
            if (countMap.drop(p).sum() != 0) {
                q.addLast(info)
            } else {
                answer++
                countMap[p - 1]--
                if(info.target) break
            }
        }
        println(answer)
    }
}

data class Info(
        val priority: Int,
        val target: Boolean
)