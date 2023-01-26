import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {

    val br = System.`in`.bufferedReader()
    val (x1, y1, x2, y2) = br.readLine().split(" ").map { it.toInt() }
    val (x3, y3, x4, y4) = br.readLine().split(" ").map { it.toInt() }

    // l1, l2 모두 y 축에 평행인 경우
    if (x2 == x1 && x4 == x3) {
        println(if (x1 == x3 && determine(y1, y2, y3, y4)) 1 else 0)
    }
    // l1 이 y 축에 평행인 경우
    else if (x2 == x1) {
        val m2 = (y4 - y3) / (x4 - x3).toDouble()
        val c2 = -m2 * x3 + y3

        val yi = m2 * x2 + c2
        println(if (inBetween(x1.toDouble(), x3, x4) && inBetween(yi, y1, y2)) 1 else 0)
    }
    // l2 이 y 축에 평행인 경우
    else if (x4 == x3) {
        val m1 = (y2 - y1) / (x2 - x1).toDouble()
        val c1 = -m1 * x1 + y1

        val yi = m1 * x4 + c1
        println(if (inBetween(x3.toDouble(), x1, x2) && inBetween(yi, y3, y4)) 1 else 0)
    }
    // l1, l2 모두 y 축에 평행하지 않은 경우
    else {
        val m1 = (y2 - y1) / (x2 - x1).toDouble()
        val m2 = (y4 - y3) / (x4 - x3).toDouble()

        val c1 = -m1 * x1 + y1
        val c2 = -m2 * x3 + y3

        // 두 직선이 평행한 경우
        if (doubleEqual(m1, m2)) {
            println(if (doubleEqual(c1, c2) && determine(x1, x2, x3, x4) && determine(y1, y2, y3, y4)) 1 else 0)
        }
        // 두 직선이 평행하지 않은 경우
        else {
            val xi = -(c1 - c2) / (m1 - m2)
            println(if (inBetween(xi, x1, x2) && inBetween(xi, x3, x4)) 1 else 0)
        }
    }
}

private const val e = 0.001

private fun doubleEqual(d1: Double, d2: Double): Boolean {
    return abs(d1 - d2) < e
}

private fun inBetween(xi: Double, x1: Int, x2: Int): Boolean {
    return min(x1, x2) <= xi + e && xi <= max(x1, x2) + e
}

private fun determine(y1: Int, y2: Int, y3: Int, y4: Int): Boolean {
    val l2l1 = max(y1, y2) + e >= min(y3, y4) && min(y1, y2) <= max(y3, y4) + e
    val l1l2 = max(y3, y4) + e >= min(y1, y2) && min(y3, y4) <= max(y1, y2) + e
    return l2l1 || l1l2
}
