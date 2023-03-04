package 단순구현.prgrms

import kotlin.math.max
import kotlin.math.min

fun main() {

    println(Solution_바탕화면_정리().solution(arrayOf(
            ".##...##.",
            "#..#.#..#",
            "#...#...#",
            ".#.....#.",
            "..#...#..",
            "...#.#...",
            "....#....",
    )).contentToString())
}

class Solution_바탕화면_정리{
    fun solution(wallpaper: Array<String>): IntArray {
        var lux = Int.MAX_VALUE
        var luy = Int.MAX_VALUE
        var rdx = Int.MIN_VALUE
        var rdy = Int.MIN_VALUE

        for (r in wallpaper.indices) {
            val line = wallpaper[r]
            for (c in line.indices) {
                if (line[c] == '#') {
                    lux = min(lux, r)
                    luy = min(luy, c)
                    rdx = max(rdx, r+1)
                    rdy = max(rdy, c+1)
                }
            }
        }

        return intArrayOf(lux, luy, rdx, rdy)
    }
}