private const val MOD = 1_000_000_007

private var n = 8
private val map = HashMap<Int, Array<LongArray>>()
private val edges = arrayOf(
    0 to 1, 1 to 0,
    0 to 2, 2 to 0,
    1 to 2, 2 to 1,
    1 to 3, 3 to 1,
    2 to 3, 3 to 2,
    2 to 4, 4 to 2,
    3 to 4, 4 to 3,
    3 to 5, 5 to 3,
    4 to 5, 5 to 4,
    5 to 6, 6 to 5,
    6 to 7, 7 to 6,
    4 to 7, 7 to 4
)

fun main() {

    val t = System.`in`.bufferedReader().readLine().toInt()

    val init = Array(n) { LongArray(n) }
    edges.forEach { (a, b) -> init[a][b] = 1 }
    map[1] = init

    dfs(t)
    println(map[t]!![0][0])
}

private fun dfs(t: Int): Array<LongArray> {

    if (map.contains(t)) return map[t]!!

    val l = t / 2
    val r = t - l
    val left = dfs(l)
    val right = dfs(r)

    val result = Array(n) { LongArray(n) }
    for (i in 0..n - 1) {
        for (j in 0..n - 1) {
            for (k in 0..n - 1) {
                result[i][j] += (left[i][k] * right[k][j]) % MOD
                result[i][j] = result[i][j] % MOD
            }
        }
    }

    map[t] = result
    return result
}
