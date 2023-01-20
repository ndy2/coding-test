package 그래프.boj

private val br = System.`in`.bufferedReader()

private lateinit var colors: Array<Int>
private lateinit var edgeMap: Array<MutableList<Int>>
private var flag = false

fun main() {
    repeat(br.readLine().toInt()) {
        val (V, E) = br.readLine().split(" ").map { it.toInt() }
        colors = Array(V + 1) { 0 }
        edgeMap = Array(V + 1) { mutableListOf() }

        repeat(E) {
            val (u, v) = br.readLine().split(" ").map { it.toInt() }
            edgeMap[u].add(v)
            edgeMap[v].add(u)
        }

        var answer = "YES"
        while (true) {
            val initV = colors.indexOfLast { it == 0 }
            if (initV == 0) break

            flag = false
            dfs(initV, 1)
            if (flag) {
                answer = "NO"
                break
            }
        }
        println(answer)
    }
}

private fun dfs(idx: Int, c: Int) {
    colors[idx] = c
    if (flag) return

    for (nv in edgeMap[idx]) {
        if (colors[nv] == 0) {
            dfs(nv, 3 - c)
        } else if (colors[nv] == c) {
            flag = true
            return
        }
    }
}


