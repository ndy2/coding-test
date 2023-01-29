package 다익스트라.boj

import java.util.PriorityQueue
import kotlin.math.min

private typealias Edges = HashMap<Int, Int>

private data class Edge(val first: Int, val second: Int)

fun main() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()

    val edgeMap = Array(n + 1) { Edges() }
    repeat(m) {
        val (a, b, c) = br.readLine().split(" ").map { it.toInt() }
        if (edgeMap[a].containsKey(b)) edgeMap[a][b] = min(edgeMap[a][b]!!, c)
        else edgeMap[a][b] = c
    }

    val (from, to) = br.readLine().split(" ").map { it.toInt() }

    // costs from 'from' to another vertices
    val costs = IntArray(n + 1) { 1_000_000_000 }
    costs[from] = 0

    val visited = BooleanArray(n + 1)

    val q = PriorityQueue<Edge>(compareBy { it.second })
    q.add(Edge(from, 0))

    while (!q.isEmpty()) {
        val (curV, _) = q.poll()

        if (curV == to) {
            break
        }

        if (visited[curV]) continue
        visited[curV] = true

        for ((v, w) in edgeMap[curV]) {
            if (costs[v] > costs[curV] + w) {
                costs[v] = costs[curV] + w
                q.add(Edge(v, costs[curV] + w))
            }
        }
    }

    println(costs[to])
}