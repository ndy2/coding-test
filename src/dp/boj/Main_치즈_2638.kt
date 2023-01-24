private var n = 0
private var m = 0

// cheese map
private lateinit var cheese: Array<BooleanArray>

// visited
// - 치즈가 없는 구역에 대해서만 체크함
// - 즉, 치즈의 위치는 항상 visited 가 false 임
private lateinit var visited: Array<BooleanArray>

// 외부에서 cheese 가 있는 위치를 방문하면 카운트를 늘림
private lateinit var count: Array<IntArray>

fun main() {
    init()

    var time = 0
    while (true) {
        setup()
        time++
        dfs(0, 0)
        removeCheeses()
        if (noCheese()) break
    }
    println(time)
}

private fun removeCheeses() {
    for (r in 0..n - 1) {
        for (c in 0..m - 1) {
            if (cheese[r][c] && count[r][c] >= 2) cheese[r][c] = false
        }
    }
}

private fun init() {
    val br = System.`in`.bufferedReader()
    val (_n, _m) = br.readLine().split(" ").map { it.toInt() }
    n = _n; m = _m

    cheese = Array(n) { BooleanArray(m) }
    for (r in 0..n - 1) {
        cheese[r] = br.readLine().split(" ").map { it.toInt() == 1 }.toBooleanArray()
    }

}

private fun setup() {
    visited = Array(n) { BooleanArray(m) }
    visited[0][0] = true
    count = Array(n) { IntArray(m) }
}

private val dy = intArrayOf(0, 0, 1, -1)
private val dx = intArrayOf(1, -1, 0, 0)

private fun dfs(r: Int, c: Int) {
    for (d in 0..3) {
        val ty = r + dy[d]
        val tx = c + dx[d]
        if (isValid(ty, tx)) {

            if (cheese[ty][tx]) count[ty][tx]++
            else if (!visited[ty][tx]) {
                visited[ty][tx] = true
                dfs(ty, tx)
            }
        }
    }
}

private fun isValid(r: Int, c: Int) = r in 0..n - 1 && c in 0..m - 1

private fun noCheese(): Boolean {
    for (r in 0..n - 1) {
        for (c in 0..m - 1) {
            if (cheese[r][c]) return false
        }
    }
    return true
}

