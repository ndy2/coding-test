package 브루트포스.boj

import java.util.stream.Collectors
import kotlin.math.abs

/**
N = 5457, BROKEN =  6 7 8
100 -> 5455 로 이동 (4번누름)
5455 -> 5456 -> 5457 (6번)

N = 999, BROKEN = 9
100 -> 1000로 이동 (4번) ->  999 (5번)
 */

lateinit var available: Set<Int>
lateinit var broken: Set<Int>

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()
    val brokenNumbersIn =
            if (m != 0) br.readLine().split(" ") else emptyList()
    broken = brokenNumbersIn.map { it.toInt() }
            .stream().collect(Collectors.toSet())
    available = setOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0).minus(broken)

    // n 을 더하고 빼면서 available 로만 만들 수 있는 숫자가 나올때 까지 이동
    // 1. n 을 더해서 등장한 수
    var cntMinus = 0
    var n1 = n
    while (!check(n1++) && n1 <= (n+1) * 10) {
        cntMinus++
    }
    val t1 = if (check(n1 - 1)) cntMinus + digitCnt(n1 - 1) else Int.MAX_VALUE

    // 2. n 을 빼서 등장한 수
    var cntPlus = 0
    var n2 = n
    while (!check(n2--) && n2 >= 0) {
        cntPlus++
    }
    val t2 = if (check(n2 + 1)) cntPlus + digitCnt(n2 + 1) else Int.MAX_VALUE

    // 3. 100 에서 바로 이동 한 수
    val t3 = abs(n - 100)

    println(minOf(t1, t2, t3))
}

// 각 digit 이 available 한 숫자로만 구성되어 있는지 판별
fun check(number: Int): Boolean {
    var cur = number
    while (true) {
        val digit = cur % 10
        cur /= 10

        if (!available.contains(digit)) return false
        if (cur == 0) break
    }
    return true
}

fun digitCnt(number: Int) = number.toString().length