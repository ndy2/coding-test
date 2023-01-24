private val br = System.`in`.bufferedReader()
private lateinit var map: Array<CharArray>
private lateinit var visited: Array<BooleanArray>
private var n = 0

fun main() {
    initMap()

    val a = getCountWith(n, map, visited)
    changeMap(n)
    val b = getCountWith(n, map, visited)

    println("$a $b")
}

private fun initMap() {
    n = br.readLine().toInt()

    map = Array(n) { CharArray(n) }
    for (r in 0..n - 1) {
        map[r] = br.readLine().toCharArray()
    }

    visited = Array(n) { BooleanArray(n) }
}

private fun changeMap(n: Int) {
    for (r in 0..n - 1) {
        for (c in 0..n - 1) {
            if (map[r][c] == 'R') map[r][c] = 'G'
        }
    }
    for (r in 0..n - 1) {
        visited[r].fill(false)
    }
}

private fun getCountWith(n: Int, map: Array<CharArray>, visited: Array<BooleanArray>): Int {
    var count = 0
    for (r in 0..n - 1) {
        for (c in 0..n - 1) {
            if (!visited[r][c]) {
                count++
                dfs(r, c, map[r][c])
            }
        }
    }
    return count
}

private val dy = intArrayOf(0, 0, 1, -1)
private val dx = intArrayOf(1, -1, 0, 0)

private fun dfs(r: Int, c: Int, t: Char) {
    for (d in 0..3) {
        val ty = r + dy[d]
        val tx = c + dx[d]
        if (isValid(ty, tx) && !visited[ty][tx] && map[ty][tx] == t) {
            visited[ty][tx] = true
            dfs(ty, tx, t)
        }
    }
}

private fun isValid(r: Int, c: Int) = r in 0..n - 1 && c in 0..n - 1
