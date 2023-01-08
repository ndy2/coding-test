package bfsdfs.dfs.boj

private lateinit var visited: BooleanArray
private lateinit var parents: IntArray
private lateinit var edges: Array<MutableList<Int>>

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    visited = BooleanArray(n + 1)
    parents = IntArray(n + 1)
    edges = Array(n + 1) { mutableListOf() }

    repeat(n - 1) {
        val (a, b) = br.readLine().split(" ").map { it.toInt() }
        edges[a].add(b)
        edges[b].add(a)
    }

    dfs(1)
    val answer = parents.drop(2).joinToString(separator = "\n")
    println(answer)
}


private fun dfs(idx: Int) {
    visited[idx] = true
    for (nextIdx in edges[idx]) {
        if (!visited[nextIdx]) {
            parents[nextIdx] = idx
            dfs(nextIdx)
        }
    }
}