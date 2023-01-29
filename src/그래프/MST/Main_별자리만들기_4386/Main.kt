package 그래프.MST.Main_별자리만들기_4386

import java.util.PriorityQueue
import kotlin.math.pow
import kotlin.math.sqrt

private lateinit var parent: IntArray

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    if (n == 1) {
        println(0)
        return
    }

    parent = IntArray(n) { it }
    val stars = mutableListOf<Star>()
    for (i in 0..n - 1) {
        val (x, y) = br.readLine().split(" ").map { it.toDouble() }
        stars.add(Star(x, y))
    }

    val comp = compareBy<Edge> { it.w }
    val edgesQueue = PriorityQueue(comp)
    for (i in 0..n - 2) {
        for (j in i + 1..n - 1) {
            val dist = dist(stars[i], stars[j])
            edgesQueue.add(Edge(i, j, dist))
        }
    }

    var count = 0
    var answer = 0.0
    while (count < n - 1) {
        val (a, b, w) = edgesQueue.poll()
        if (hasSameParent(a, b)) continue
        union(a, b)
        answer += w
        count++
    }
    println(answer)
}

private fun hasSameParent(a: Int, b: Int): Boolean {
    return find(a) == find(b)
}

private fun union(a: Int, b: Int) {
    val pa = find(a)
    val pb = find(b)

    if (pa != pb) {
        parent[pa] = pb
    }
}

private fun find(a: Int): Int {
    if (parent[a] == a) {
        return a
    } else {
        parent[a] = find(parent[a])
        return parent[a]
    }
}


private data class Star(
    val x: Double,
    val y: Double,
)

private data class Edge(
    val a: Int,
    val b: Int,
    val w: Double,
)

private fun dist(a: Star, b: Star): Double {
    return sqrt((a.x - b.x).pow(2) + (a.y - b.y).pow(2))
}