package 단순구현.boj

import java.lang.StringBuilder

fun main() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val sb = StringBuilder()
    for (r in 1..n) {
        for (c in n - r - 1 downTo 0) {
            sb.append(' ')
        }
        for (c in 0..r - 1) {
            sb.append('*')
        }
        sb.append('\n')
    }
    print(sb)
}