package bfsdfs.dfs.boj// @formatter:off

private lateinit var board: Array<IntArray>
private var n = 0
private val answer = IntArray(3)

fun main() {
    initBoard()

    dfs(0, 0, n)
    println(answer.joinToString("\n"))
}

private fun dfs(r: Int, c: Int, n: Int) {
    if (n == 1) {
        proc(board[r][c])
    } else {
        val check = check(r, c, n)
        if (check in -1..1) proc(check)
        else {
            val nn = n / 3
            dfs(r, c, nn); dfs(r, c + nn, nn); dfs(r, c + 2 * nn, nn)
            dfs(r + nn, c, nn); dfs(r + nn, c + nn, nn); dfs(r + nn, c + 2 * nn, nn)
            dfs(r + 2 * nn, c, nn); dfs(r + 2 * nn, c + nn, nn); dfs(r + 2 * nn, c + 2 * nn, nn)
        }
    }
}

// bfsdfs.dfs.boj.check given part contains only of -1 or 0 or 1
// then each -1, 0, 1 orElse return -100
private fun check(r: Int, c: Int, n: Int): Int {
    var containsMinusOne = false
    var containsZero = false
    var containsPlusOne = false

    for (row in r..r + n - 1) {
        for (col in c..c + n - 1) {
            when (board[row][col]) {
                -1 -> { containsMinusOne = true; if (containsZero || containsPlusOne) return -10 }
                0 -> { containsZero = true; if (containsMinusOne || containsPlusOne) return -10 }
                1 -> { containsPlusOne = true; if (containsMinusOne || containsZero) return -10 }
            }
        }
    }
    return if (containsMinusOne) -1 else if (containsZero) 0 else 1
}

private fun proc(v: Int) {
    when (v) {
        -1 -> answer[0]++
        0 -> answer[1]++
        1 -> answer[2]++
    }
}

private fun initBoard() {
    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()

    board = Array(n) { IntArray(n) }
    for (r in 0..n - 1) {
        board[r] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }
}

// @formatter:on
