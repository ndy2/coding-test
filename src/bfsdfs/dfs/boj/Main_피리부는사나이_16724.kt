private var n = 0
private var m = 0

private lateinit var map: Array<IntArray>
private lateinit var visited: Array<IntArray>

fun main() {

    val br = System.`in`.bufferedReader()
    val nm = br.readLine().split(" ").map { it.toInt() }
    n = nm[0]
    m = nm[1]
    map = Array(n) { IntArray(m) }
    visited = Array(n) { IntArray(m) }

    for (r in 0..n - 1) {
        val line = br.readLine()
        for (c in 0..m - 1) {
            map[r][c] = when (line[c]) {
                'R' -> 0; 'L' -> 1; 'D' -> 2; 'U' -> 3
                else -> throw IllegalStateException()
            }
        }
    }

    for (r in 0..n - 1) {
        for (c in 0..m - 1) {
            if (visited[r][c] == 0) {
                fowDfs(r, c)
                invDfs(r, c)
                groupId++
            }
        }
    }
    println(groupId - 1)
}

private var groupId = 1

private val dy = intArrayOf(0, 0, 1, -1)
private val dx = intArrayOf(1, -1, 0, 0)

private fun isValid(r: Int, c: Int) = r in 0..n - 1 && c in 0..m - 1

private fun fowDfs(r: Int, c: Int) {
    visited[r][c] = groupId

    val ty = r + dy[map[r][c]]
    val tx = c + dx[map[r][c]]

    if (!isValid(ty, tx) || visited[ty][tx] != 0) {
        visited[r][c] *= 100
    } else {
        fowDfs(ty, tx)
    }
    invDfs(r, c)
}

private fun invDfs(r: Int, c: Int) {
    visited[r][c] = groupId

    for (d in 0..3) {
        val ty = r + -1 * dy[d]
        val tx = c + -1 * dx[d]

        if (!isValid(ty, tx)) continue
        if (visited[ty][tx] == 0 && map[ty][tx] == d) {
            invDfs(ty, tx)
        }
    }
}

