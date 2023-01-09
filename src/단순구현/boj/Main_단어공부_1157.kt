package 단순구현.boj

fun main() {

    val br = System.`in`.bufferedReader()
    val s = br.readLine()

    val count = IntArray(26)

    s.forEach {
        val idx = if (it.isUpperCase()) it - 'A' else it - 'a'
        count[idx]++
    }

    val max = count.max()
    val i1 = count.indexOfFirst { it == max }
    if (i1 == count.indexOfLast { it == max }) {
        println('A' + i1)
    } else {
        println('?')
    }

}