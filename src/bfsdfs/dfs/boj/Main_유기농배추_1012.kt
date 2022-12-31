package bfsdfs.dfs.boj

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val t = br.readLine().toInt()
    for (i in 1..t) {
        val mnk = br.readLine().split(" ")
        val m = mnk[0].toInt()
        val n = mnk[1].toInt()
        val k = mnk[2].toInt()

        val map = Array(n) { BooleanArray(m) }
        for (j in 1..k) {
            val xy = br.readLine().split(" ")
            val x = xy[0].toInt()
            val y = xy[1].toInt()
            map[y][x] = true
        }

        Solution(n, m, map).solve()
    }
}

class Solution(
        val n: Int,
        val m: Int,
        val map: Array<BooleanArray>
) {
    val visited: Array<BooleanArray> = Array(n) { BooleanArray(m) }

    fun solve() {
        var answer = 0

        for (r in 0 until n) {
            for (c in 0 until m) {
                if (!visited[r][c] && map[r][c]) {
                    dfs(r, c, 0)
                    answer++
                }
            }
        }
        println(answer)
    }

    val dy = arrayOf(0, 0, 1, -1)
    val dx = arrayOf(1, -1, 0, 0)

    fun dfs(r: Int, c: Int, cnt: Int) {
        visited[r][c] = true
        for (d in 0 until 4) {
            val ty = r + dy[d]
            val tx = c + dx[d]

            if (isValid(ty, tx) && !visited[ty][tx] && map[ty][tx]) {
                dfs(ty, tx, cnt + 1);
            }
        }
    }

    fun isValid(r: Int, c: Int): Boolean {
        return r >= 0 && r < n && c >= 0 && c < m
    }
}