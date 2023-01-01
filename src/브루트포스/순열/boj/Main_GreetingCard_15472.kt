package 브루트포스.순열.boj

import kotlin.math.pow
import kotlin.math.sqrt

typealias Coordinate = Pair<Double, Double>

val br = System.`in`.bufferedReader()
fun readInt() = br.readLine().toInt()
fun readCoordinate(): Coordinate {
    val (y, x) = br.readLine().split(' ').map { it.toDouble() }
    return Coordinate(y, x)
}

fun dist(p1: Coordinate, p2: Coordinate): Int {
    val (y1, x1) = p1
    val (y2, x2) = p2

    return sqrt((y1 - y2).pow(2.0) + (x1 - x2).pow(2.0)).toInt()
}

fun main() {
    val n = readInt()
    val coordinates = mutableSetOf<Coordinate>()
    repeat(n) { coordinates.add(readCoordinate()) }

    val dy = arrayOf(0, 0, 2018, -2018, 1680, 1118, 1680, 1118, -1680, -1118, -1680, -1118)
    val dx = arrayOf(2018, -2018, 0, 0, 1118, 1680, -1118, -1680, 1118, 1680, -1118, -1680)

    var answer = 0L
    coordinates.forEach {
        for (d in 0 until 12) {
            val ty = it.first + dy[d]
            val tx = it.second + dx[d]
            if (coordinates.contains(Coordinate(ty,tx))) answer ++
        }
    }
    println(answer/2)
}