package bfsdfs.dfs.boj

private var arr = intArrayOf()
private var visited = booleanArrayOf()
private var count = 0
fun main() {

    val br = System.`in`.bufferedReader()
    repeat(br.readLine().toInt()) {
        val n = br.readLine().toInt()
        arr = ("0 " + br.readLine()).split(" ").map { it.toInt() }.toIntArray()
        visited = BooleanArray(n + 1)

        count = 0
        for (i in 1..n) if (!visited[i]) {
            list = mutableListOf()
            dfs(i)
        }
        println(count)
    }
}

private lateinit var list: MutableList<Int>

private fun dfs(idx: Int) {
    if (visited[idx]) {
        // 이번 탐색 출신인가 -> cycle 이 있다.
        // 아니면 코 박은거임
        val indexOf = list.indexOf(idx)
        if (indexOf == -1) {
            count += list.size
        } else {
            count += indexOf
        }
    } else {
        visited[idx] = true
        list.add(idx)
        dfs(arr[idx])
    }
}