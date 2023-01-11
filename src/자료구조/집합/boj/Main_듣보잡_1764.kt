package 자료구조.집합.boj

import java.lang.StringBuilder

private val br = System.`in`.bufferedReader()
private val sb = StringBuilder()

fun main() {
    val (n, m) = br.readLine().split(" ").map { it.toInt() }

    val dbjob = buildSet(n).intersect(buildSet(m))
    sb.append("${dbjob.size}\n")
    dbjob.sortedWith(naturalOrder())
            .forEach { sb.append("${it}\n") }

    println(sb)
}

private fun buildSet(n: Int): Set<String> {
    return buildSet(n) {
        repeat(n) {
            add(br.readLine())
        }
    }
}