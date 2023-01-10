package 수학.boj

import kotlin.math.ceil

fun main() {
    val (a, b, v) = System.`in`.bufferedReader().readLine().split(" ").map { it.toInt() }

    // 2, 1, 5 -> 하루 1
    // 3 을 naive 하게 돌파하는 시간 = ceil(3/1) + 1 = 4

    // 5 1 6 -> 하루 4
    // 1 을 naive 하게 돌파하는 시간 = ceil(1/4) + 1 = 1

    // 100 99 1000000000 -> 하루 1
    //999999900 을 naive 하게 돌파하는 시간 = ceil(999999900/1) + 1 = 999999901

    val perDay = a - b
    val target = v - a
    println(ceil(target / perDay.toDouble()).toInt() + 1)
}