package 단순구현.boj

fun main() {

    val br = System.`in`.bufferedReader()
    val s = br.readLine()

    val answer = IntArray(26) { idx -> s.indexOfFirst { it - 'a' == idx } }
    println(answer.joinToString(" "))
}