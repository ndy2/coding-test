package 재귀.boj

private val br = System.`in`.bufferedReader()
private val map = mutableMapOf<Long,Long>()

fun main() {
    val (a, b, c) = readLongArray()
    println(dfs(a,b,c))
}

private fun dfs(a: Long, b: Long, c: Long): Long {
    if(map.containsKey(b)){
        return map[b]!!
    }

    val result : Long
    when (b) {
        1L -> result = a % c
        2L -> result = (a * a) % c
        else -> {
            val b1 = b / 2
            val b2 = b - b1

            result = (dfs(a, b1, c) * dfs(a, b2, c)) % c
        }
    }
    map[b] = result
    return result
}


private fun readLongArray(): List<Long> {
    return br.readLine().split(" ").map { it.toLong() }
}