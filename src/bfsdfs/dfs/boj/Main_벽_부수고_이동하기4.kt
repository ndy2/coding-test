private val br = System.`in`.bufferedReader()
private var n = 0
private var m = 0
private lateinit var map: Array<BooleanArray>
private lateinit var visited: Array<BooleanArray>

fun main() {

    val nm = br.readLine().split(" ").map { it.toInt() }
    n = nm[0]
    m = nm[1]

    map = Array(n) { BooleanArray(m) }
    visited = Array(n) { BooleanArray(m) }
    val countMap = Array(n) { IntArray(m) }
    val groupMap = Array(n) { IntArray(m) }

    for (r in 0..n - 1) {
        val line = br.readLine()
        for (c in 0..m - 1) {
            map[r][c] = line[c] == '1'
        }
    }
    var group = 1
    for (r in 0..n - 1) {
        for (c in 0..m - 1) {
            if (!visited[r][c] && !map[r][c]) {
                count = 0
                set = mutableSetOf()
                dfs(r, c)

                for ((sr, sc) in set) {
                    countMap[sr][sc] = count
                    groupMap[sr][sc] = group
                }
                group++
            }
        }
    }

    val answer = StringBuilder()
    for (r in 0..n - 1) {
        val line = StringBuilder()
        for (c in 0..m - 1) {
            if (!map[r][c]) {
                line.append(0)
            } else {
                var countSum = 1
                val groupSet = mutableSetOf<Int>()
                for (d in 0..3) {
                    val ty = r + dy[d]
                    val tx = c + dx[d]
                    if (isValid(ty, tx) && !map[ty][tx] && !groupSet.contains(groupMap[ty][tx])) {
                        groupSet.add(groupMap[ty][tx])
                        countSum += countMap[ty][tx]
                    }
                }
                line.append(countSum % 10)
            }
        }
        answer.append(line).append('\n')
    }
    println(answer)
}

private var count = 0
private var set = mutableSetOf<Pair<Int, Int>>()

private val dy = intArrayOf(0, 0, 1, -1)
private val dx = intArrayOf(1, -1, 0, 0)
private fun isValid(r: Int, c: Int) = r in 0..n - 1 && c in 0..m - 1

private fun dfs(r: Int, c: Int) {

    visited[r][c] = true
    set.add(Pair(r, c))
    count = (count + 1) % 10
    for (d in 0..3) {
        val ty = r + dy[d]
        val tx = c + dx[d]
        if (isValid(ty, tx) && !visited[ty][tx] && !map[ty][tx]) {
            dfs(ty, tx)
        }
    }
}

