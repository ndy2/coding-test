lateinit var check: Array<IntArray>
lateinit var arr: IntArray

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.`out`.bufferedWriter()
    val n = br.readLine().toInt()

    arr = ("0 " + br.readLine()).split(" ").map { it.toInt() }.toIntArray()
    check = Array(n + 2) { IntArray(n + 2) { -1 } }

    repeat(br.readLine().toInt()) {

        val (a, b) = br.readLine().split(" ").map { it.toInt() }
        bw.append(dfs(a,b).toString())
        bw.append('\n')
    }

    bw.flush()
}

private fun dfs(a: Int, b: Int): Int {

    return if (b < a) {
        1
    } else if (check[a][b] != -1) {
        check[a][b]
    } else if (a == b) {
        check[a][b] = 1
        1
    } else if (arr[a] != arr[b]) {
        check[a][b] = 0
        0
    } else {
        check[a][b] = dfs(a + 1, b - 1)
        check[a][b]
    }
}