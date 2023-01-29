package 수학.기하

import kotlin.math.max
import kotlin.math.min
import kotlin.math.sign

private lateinit var parents: IntArray
private lateinit var ranks: IntArray
private lateinit var size: IntArray

fun main() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    parents = IntArray(n) { it }
    ranks = IntArray(n) { 1 }
    size = IntArray(n) { 1 }

    val segments = buildList(n) {
        repeat(n) {
            val (x1, y1, x2, y2) = br.readLine().split(" ").map { it.toInt() }
            add(Segment(Point(x1, y1), Point(x2, y2)))
        }
    }

    for (i in 0..n - 2) {
        for (j in i + 1..n - 1) {
            val s1 = segments[i]
            val s2 = segments[j]
            if (intersect(s1, s2)) union(i, j)
        }
    }

    val numGroup = parents.filterIndexed { idx, it -> idx == it }.count()
    val maxGroupSize = size.max()

    println("$numGroup\n$maxGroupSize")
}

// method ~ group disjoint set
private fun union(a: Int, b: Int) {
    val pa = find(a)
    val pb = find(b)

    if (pa != pb) {
        if (ranks[pa] > ranks[pb]) {
            // pb -> pa
            parents[pb] = pa
            size[pa] += size[pb]
        } else if (ranks[pa] < ranks[pb]) {
            // pa -> pb
            parents[pa] = pb
            size[pb] += size[pa]
        } else {
            // pa -> pb
            parents[pa] = pb
            size[pb] += size[pa]
            // add rank
            ranks[pb]++
        }
    }
}

private fun find(a: Int): Int {
    return if (a == parents[a]) a
    else {
        parents[a] = find(parents[a]) // path compression
        parents[a]
    }
}


// method ~ segment intersect determination

private fun intersect(s1: Segment, s2: Segment): Boolean {
    val abc = ccw(s1.a, s1.b, s2.a)
    val abd = ccw(s1.a, s1.b, s2.b)
    val cda = ccw(s2.a, s2.b, s1.a)
    val cdb = ccw(s2.a, s2.b, s1.b)

    var result = false
    var check = false
    if (abc * abd == 0 && cda * cdb == 0) {
        check = true
        if (determine(s1, s2)) result = true
    }
    if (abc * abd <= 0 && cda * cdb <= 0) {
        if (!check) result = true
    }
    return result
}

private fun determine(s1: Segment, s2: Segment): Boolean {
    return min(s1.a.x, s1.b.x) <= max(s2.a.x, s2.b.x)
            && min(s2.a.x, s2.b.x) <= max(s1.a.x, s1.b.x)
            && min(s1.a.y, s1.b.y) <= max(s2.a.y, s2.b.y)
            && min(s2.a.y, s2.b.y) <= max(s1.a.y, s1.b.y)
}

private fun ccw(a: Point, b: Point, c: Point): Int {
    return (a.x * b.y + b.x * c.y + c.x * a.y - a.y * b.x - b.y * c.x - c.y * a.x).sign
}


private data class Segment(val a: Point, val b: Point)
private data class Point(val x: Int, val y: Int)

