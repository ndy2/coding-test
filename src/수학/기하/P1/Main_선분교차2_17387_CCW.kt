package 수학.기하.P1

import kotlin.math.max
import kotlin.math.min
import kotlin.math.sign

fun main() {

    val br = System.`in`.bufferedReader()
    val (x1, y1, x2, y2) = br.readLine().split(" ").map { it.toLong() }
    val (x3, y3, x4, y4) = br.readLine().split(" ").map { it.toLong() }

    val a = Point(x1, y1)
    val b = Point(x2, y2)
    val c = Point(x3, y3)
    val d = Point(x4, y4)

    val abc = ccw(a, b, c)
    val abd = ccw(a, b, d)
    val cda = ccw(c, d, a)
    val cdb = ccw(c, d, b)

    var result = 0
    var check = false
    if (abc * abd == 0 && cda * cdb == 0) {
        check = true
        result = if (determine(a, b, c, d)) 1 else 0
    }
    if (abc * abd <= 0 && cda * cdb <= 0) {
        if (!check) result = 1
    }
    println(result)
}

private fun determine(a: Point, b: Point, c: Point, d: Point): Boolean {
    return min(a.x, b.x) <= max(c.x, d.x)
            && min(c.x, d.x) <= max(a.x, b.x)
            && min(a.y, b.y) <= max(c.y, d.y)
            && min(c.y, d.y) <= max(a.y, b.y)
}

private fun ccw(a: Point, b: Point, c: Point): Int {
    val ccw = a.x * b.y + b.x * c.y + c.x * a.y - a.y * b.x - b.y * c.x - c.y * a.x;
    return ccw.sign
}

private data class Point(val x: Long, val y: Long)
