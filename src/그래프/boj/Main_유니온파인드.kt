val br_1707 = System.`in`.bufferedReader()
fun readInt() = br_1707.readLine().toInt()
fun readIntArr() = br_1707.readLine().split(" ").map { it.toInt() }.toIntArray()

lateinit var parent: IntArray

/**
2
3 2
1 3
2 3
4 4
1 2
2 3
3 4
4 2
 */

fun main() {
    repeat(readInt()) {
        val (V, E) = readIntArr()
        parent = IntArray(V + 1) {it}
        println("parent = ${parent.contentToString()}")
        repeat(E) {
            val (u, v) = readIntArr()
            union(u, v)
        }
        println("parent = ${parent.contentToString()}")
        val answer = if (parent.drop(1).distinct().size == 2) "YES" else "NO"
        println(answer)

        for (i in 1..V) println(find(i))
    }
}

fun union(a: Int, b: Int) {
    val pa = find(a)
    val pb = find(b)

    if (pa != pb) {
        parent[pb] = pa
    }
}

fun find(a: Int): Int {

    return if (parent[a] == a) {
        a
    } else {
        parent[a] = find(parent[a])
        parent[a]
    }
}
