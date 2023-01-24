import java.util.TreeSet

private lateinit var a: IntArray
private lateinit var arr: IntArray
private var n = 0
private var m = 0
private val answer = TreeSet<String>()

fun main() {

    val br = System.`in`.bufferedReader()
    val (_n, _m) = br.readLine().split(" ").map { it.toInt() }
    a = br.readLine().split(" ").map { it.toInt() }.toSortedSet().toIntArray()
    n = a.size
    m = _m
    arr = IntArray(m)

    for (ai in 0..n - 1) {
        dfs(0, ai)
    }
    answer.forEach(::println)
}

private fun dfs(idx: Int, ai: Int) {
    arr[idx] = a[ai]
    if (idx == m - 1) {
        println(arr.joinToString(" "))
    } else {
        arr[idx] = a[ai]
        for (nai in ai..n - 1) {
            dfs(idx + 1, nai)
        }
    }
}