package 벨만포드

private val br = System.`in`.bufferedReader()
private fun readIntList() = br.readLine().split(" ").map { it.toInt() }

fun main() {

    repeat(br.readLine().toInt()) {

        val (n, m, w) = readIntList()
        val edgesMap = initEdgesMap(n, m, w)
        val cost = IntArray(n + 1) { 100_000_000 }
        cost[0] = 0
        var answer = "NO"

        val stt = 1
        cost[stt] = 0
        repeat(n - 1) {
            // edge relaxation
            for (v in 1..n) {
                for (edge in edgesMap[v]) {
                    if (cost[edge.to] > cost[v] + edge.weight) {
                        cost[edge.to] = cost[v] + edge.weight
                    }
                }
            }
        }

        // edge relaxation 한번 더
        for (v in 1..n) {
            for (edge in edgesMap[v]) {
                if (cost[edge.to] > cost[v] + edge.weight) {
                    answer  = "YES"
                }
            }
        }
        println(answer)
    }
}

private data class Edge(
    val to: Int,
    val weight: Int,
)

private fun initEdgesMap(n: Int, m: Int, w: Int): Array<MutableList<Edge>> {
    val edgesMap = Array(n + 1) { mutableListOf<Edge>() }
    repeat(m) {
        val (s, e, t) = readIntList()
        edgesMap[s].add(Edge(e, t))
        edgesMap[e].add(Edge(s, t))
    }
    repeat(w) {
        val (s, e, t) = readIntList()
        edgesMap[s].add(Edge(e, -t))
    }
    return edgesMap
}
