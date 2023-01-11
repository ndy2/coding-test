fun main() {

    val ans = intArrayOf(0, 1, 2, 4, 7, 13, 24, 44, 81, 149, 274, 504, 927)
    val br = System.`in`.bufferedReader()
    repeat(br.readLine().toInt()) {
        println(ans[br.readLine().toInt()])
    }
}