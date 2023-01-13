private var n = 0
private lateinit var map: Array<IntArray>
private val answer = StringBuilder()

private fun init() {
    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()

    map = Array(n) { IntArray(n) }
    for (r in 0..n - 1) {
        map[r] = br.readLine().map { it.digitToInt() }.toIntArray()
    }
}


fun main() {

    init()
    dfs(0, 0, n)
    println(answer)
}

private fun dfs(r: Int, c: Int, len: Int) {
    if (len == 1) {
        answer.append(map[r][c])
    } else {
        if (check(r, c, len)) {
            answer.append(map[r][c])
        } else {
            answer.append('(')
            val nLen = len / 2
            dfs(r, c, nLen); dfs(r, c + nLen, nLen)
            dfs(r + nLen, c, nLen); dfs(r + nLen, c + nLen, nLen)
            answer.append(')')
        }

    }
}

private fun check(row: Int, col: Int, len: Int): Boolean {
    val first = map[row][col]
    for (r in row..row + len - 1) {
        for (c in col..col + len - 1) {
            if (map[r][c] != first) return false
        }
    }
    return true
}