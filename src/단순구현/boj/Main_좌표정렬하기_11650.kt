package 단순구현.boj

typealias Coor = Pair<Int, Int>

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    buildList(n) {
        repeat(n) {
            val (x, y) = br.readLine().split(" ").map { it.toInt() }
            add(Coor(x, y))
        }
    }
            .sortedWith(compareBy<Coor> { it.first }.thenBy { it.second })
            .forEach { println("${it.first} ${it.second}") }
}
