package 백트래킹.boj

private val br = System.`in`.bufferedReader()
private lateinit var board: Array<IntArray>
private lateinit var coors: MutableList<Coor>
private lateinit var boxIdx2Coors: MutableMap<Pair<Int, Int>, List<Coor>>

private var n = 0

private data class Coor(
        val r: Int,
        val c: Int
)

private fun initBoardAndCoors() {
    board = Array(9) { IntArray(9) }
    coors = mutableListOf()
    for (r in 0..8) {
        val line = br.readLine()
        for (c in 0..8) {
            val v = line[c].digitToInt()
            board[r][c] = v
            if (v == 0) {
                coors.add(Coor(r, c))
            }
        }
    }
    n = coors.size
}

private fun initBoxIdx2Coors() {
    boxIdx2Coors = mutableMapOf()
    for (rr in 0..2) {
        for (cc in 0..2) {
            boxIdx2Coors[Pair(rr, cc)] = buildList {
                for (r in 0..2) {
                    for (c in 0..2) {
                        add(Coor(rr * 3 + r, cc * 3 + c))
                    }
                }
            }
        }
    }
}

private fun getBoxCoors(r: Int, c: Int): List<Coor> {
    return boxIdx2Coors[Pair(r / 3, c / 3)]!!
}

fun main() {
    initBoardAndCoors()
    initBoxIdx2Coors()

    for (t in 1..9) {
        dfs(0, t)
    }

    printBoard()
}


/**
 * idx 번의 coor 에 대해서 번호 t 로 확인해본다.
 * -> 문제없다  -> 다음 idx 의 1 번 부터 호출
 * -> 문제있다  -> return
 */
private var done = false
private fun dfs(idx: Int, t: Int) {
    if (done) return

    val (r, c) = coors[idx]
    board[r][c] = t
    if (!check(idx)) {
        board[r][c] = 0
        return
    } else {
        if (idx == coors.size - 1) {
            // done!
            done = true
            return
        }
        for (nt in 1..9) {
            dfs(idx + 1, nt)
        }
    }
    if(!done){
        board[r][c] = 0
    }
}

/**
 * idx 번째 좌표와 관련된 3요소 (가로, 세로, 박스) 테스트
 * 0을 제외한 중복 원소가 발견되면 fail
 */

private fun check(idx: Int): Boolean {
    val counts = IntArray(10)

    val (r, c) = coors[idx]
    // check 가로
    for (col in 0..8) {
        val v = board[r][col]
        counts[v]++
        if (v != 0 && counts[v] == 2) return false
    }
    counts.fill(0)

    // check 세로
    for (row in 0..8) {
        val v = board[row][c]
        counts[v]++
        if (v != 0 && counts[v] == 2) return false
    }
    counts.fill(0)

    // check 네모
    for ((tr, tc) in getBoxCoors(r, c)) {
        val v = board[tr][tc]
        counts[v]++
        if (v != 0 && counts[v] == 2) return false
    }
    counts.fill(0)

    return true
}

private fun printBoard() {
    val sb = StringBuilder()
    for (r in 0..8) {
        for (c in 0..8) {
            sb.append(board[r][c])
        }
        sb.append('\n')
    }
    print(sb)
}
