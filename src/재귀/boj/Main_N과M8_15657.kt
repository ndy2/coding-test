package 재귀.boj

private lateinit var arr: IntArray
private lateinit var store: IntArray

private var n = 0
private var m = 0

fun main() {
    val br = System.`in`.bufferedReader()
    val nm = br.readLine().split(" ").map { it.toInt() }
    n = nm[0]; m = nm[1]
    arr = br.readLine().split(" ").map { it.toInt() }.sorted().toIntArray()
    store = IntArray(m)

    for (i in 0..n - 1) {
        dfs(i, 0)
    }
}


private fun dfs(idx: Int, len: Int) {
    store[len] = arr[idx]
    if (len == m - 1) {
        println(store.joinToString(" "))
    } else {
        for (i in idx..n - 1) {
            dfs(i, len + 1)
        }
    }
}