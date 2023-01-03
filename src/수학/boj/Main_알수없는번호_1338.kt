package 수학.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val (l, r) = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    val (y, x) = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    Solution_알수없는번호_1338(min(l, r), max(l, r), y, x).solve()
    br.close()
}

class Solution_알수없는번호_1338(
    val l: Int,
    val r: Int,
    val x: Int,
    val y: Int
) {

    fun solve() {

        var answer = "Unknwon Number"
        if (x == 0 || y < 0 || y >= abs(x)) {
            print(answer)
            return
        }
        // 같은 덩어리
        val floorL = floor(l / x.toDouble()).toLong()
        val floorR = floor(r / x.toDouble()).toLong()
        if (floorL == floorR) {
            if (rem(l, x) <= y && y <= rem(r, x)) {
                answer = ((x * floorL) + y).toString()
            }
        } else if (floorL + 1 == floorR) {
            if (rem(l, x) <= y && rem(r, x) < y) {
                answer = ((x * floorL) + y).toString()
            } else if (y <= rem(r, x) && y < rem(l, x)) {
                answer = ((x * floorR) + y).toString()
            }
        }
        println(answer)
    }

    fun rem(t: Int, x: Int) = ((t % x + x) % x).toLong()
}