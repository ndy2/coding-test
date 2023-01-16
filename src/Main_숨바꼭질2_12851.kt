import java.util.PriorityQueue
import kotlin.math.min

typealias Info = Pair<Int, Int>

private fun isValid(x: Int): Boolean = x in 0..100_000

fun main() {

    val br = System.`in`.bufferedReader()
    val (a, b) = br.readLine().split(" ").map { it.toInt() }
    if (a >= b) {
        println(a - b)
        println(1)
        return
    }

    val visited = IntArray(100_001) { 1_000_000_000 }
    val q = PriorityQueue<Info>(compareBy { it.first })
    visited[a] = 0
    q.add(Info(0, a))

    var minTime = 100_001
    var minTimeCount = 0
    while (!q.isEmpty()) {
        val (curTime, curX) = q.poll()
        if (curX == b) {
            minTime = min(minTime, curTime)
            minTimeCount++
        }
        if (minTime != 100_001 && curTime > minTime) {
            break
        }

        for (tx in listOf(curX - 1, curX + 1, 2 * curX)) {
            if (isValid(tx) && visited[tx] >= curTime) {
                visited[tx] = curTime+1
                q.add(Info(curTime + 1, tx))
            }
        }
    }
    println(minTime)
    println(minTimeCount)
}