package bfsdfs.bfs.programmers

import java.util.LinkedList

class Solution_미로탈출 {

    private lateinit var board: Array<BooleanArray>
    var n = 0
    var m = 0
    fun solution(maps: Array<String>): Int {
        this.n = maps.size
        this.m = maps[0].length
        this.board = Array(n) { BooleanArray(m) }

        var start = Coor_미로탈출(0, 0)
        var lever = Coor_미로탈출(0, 0)
        var end = Coor_미로탈출(0, 0)

        for (r in 0..n - 1) {
            for (c in 0..m - 1) {
                if (maps[r][c] == 'S') start = Coor_미로탈출(r, c)
                if (maps[r][c] == 'L') lever = Coor_미로탈출(r, c)
                if (maps[r][c] == 'E') end = Coor_미로탈출(r, c)

                board[r][c] = maps[r][c] != 'X'
            }
        }

        val a = bfs(start, lever, Array(n) { BooleanArray(m) })
        if (a == -1) return -1

        val b = bfs(lever, end, Array(n) { BooleanArray(m) })
        if (b == -1) return -1

        return a + b
    }

    val dy = intArrayOf(0, 0, 1, -1)
    val dx = intArrayOf(1, -1, 0, 0)

    private fun isValid(r: Int, c: Int) = r in 0..n - 1 && c in 0..m - 1

    private fun bfs(from: Coor_미로탈출, to: Coor_미로탈출, visited: Array<BooleanArray>): Int {
        val q = LinkedList<Info_미로탈출>()
        visited[from.r][from.c] = true
        q.add(Info_미로탈출(from.r, from.c, 0))

        while (!q.isEmpty()) {
            val cur = q.poll()
            if (cur.r == to.r && cur.c == to.c) {
                return cur.len
            }

            for (d in 0..3) {
                val ty = cur.r + dy[d]
                val tx = cur.c + dx[d]
                if (isValid(ty, tx) && !visited[ty][tx] && board[ty][tx]) {
                    visited[ty][tx] = true
                    q.add(Info_미로탈출(ty, tx, cur.len + 1))
                }
            }
        }
        return -1
    }

    data class Coor_미로탈출(val r: Int, val c: Int, )
    data class Info_미로탈출(val r: Int, val c: Int, val len: Int, )
}