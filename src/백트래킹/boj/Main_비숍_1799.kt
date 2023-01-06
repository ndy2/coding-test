import java.lang.Math.max

private var n = 0
private lateinit var newBoard: Array<ShortArray>
private var count = 0
private var answer = 0

fun main() {

    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()


    val board = Array(n) { BooleanArray(n) }
    for (r in 0..n - 1) {
        br.readLine().split(" ").map { it == "1" }
            .forEachIndexed { c, b -> board[r][c] = b }
    }

    val nn = n * 2 - 1
    newBoard = Array(nn) { ShortArray(nn) { Short.MAX_VALUE } }
    for (r in 0..n - 1) {
        for (c in 0..n - 1) {
            val (nr, nc) = transform(r, c)
            newBoard[nr][nc] = if (board[r][c]) 0 else 1
        }
    }

    callDfs(-1)
    val ans0 = answer
    answer = 0
    count = 0
    callDfs(-2)
    println(ans0 + answer)
}

private fun dfs(nr: Int, nc: Int) {
    if (newBoard[nr][nc] > 0) return

    val rRange = rangeByIdx(nc)
    val cRange = rangeByIdx(nr)

    count++
    answer = max(count, answer)

    if (nr == 2 * n - 2) {
        count--
        return
    }

    newBoard[nr][nc] = 1 // fill itself
    fillRow(nr, cRange, nc)
    fillCol(nc, rRange, nr)

    callDfs(nr)

    count--
    newBoard[nr][nc] = 0 //unFill itself
    unFillRow(nr, cRange, nc)
    unFillCol(nc, rRange, nr)
}

private fun callDfs(nr: Int) {
    for (tr in nr + 2..2 * n - 2 step 2) {
        if (newBoard[tr].contains(0)) {
            for (tc in rangeByIdx(tr) step 2) {
                dfs(tr, tc)
            }
            break
        }
    }
}

fun fillRow(r: Int, range: IntProgression, skip: Int) {
    for (col in range) {
        if (col == skip) continue
        newBoard[r][col]++
    }
}


fun unFillRow(r: Int, range: IntProgression, skip: Int) {
    for (col in range) {
        if (col == skip) continue
        newBoard[r][col]--
    }
}


fun fillCol(c: Int, range: IntProgression, skip: Int) {
    for (row in range) {
        if (row == skip) continue
        newBoard[row][c]++
    }
}


fun unFillCol(c: Int, range: IntProgression, skip: Int) {
    for (row in range) {
        if (row == skip) continue
        newBoard[row][c]--
    }
}

fun transform(r: Int, c: Int): Pair<Int, Int> {
    val nr = r + c
    val nc = if (r > c) n - (r - c) - 1 else n + (c - r) - 1
    return Pair(nr, nc)
}

fun rangeByIdx(idx: Int): IntProgression {
    val left: Int
    val right: Int
    if (idx < n) {
        left = transform(idx, 0).second
        right = transform(0, idx).second
    } else {
        left = transform(n - 1, idx - (n - 1)).second
        right = transform(idx - (n - 1), n - 1).second
    }
    return left..right step 2
}