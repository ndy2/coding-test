package 수학.boj

private lateinit var dp: MutableMap<Pair<Long, Long>, Long>

fun main() {
    val br = System.`in`.bufferedReader()
    val (a, b) = br.readLine().split(" ").map { it.toLong() }

    dp = mutableMapOf(
        Pair(0L, 0L) to 0L,
        Pair(0L, 1L) to 1L
    )
    println(dfs(a, b))
}

private fun dfs(a: Long, b: Long): Long {
    if (dp.containsKey(Pair(a, b))) return dp[Pair(a, b)]!!

    val aBase = a.takeHighestOneBit()
    val bBase = b.takeHighestOneBit()

    if (aBase == bBase) {
        val numHighestOneBits = b - a + 1
        val numRemainingBits = dfs(a - aBase, b - bBase)
        val result = numHighestOneBits + numRemainingBits
        dp[Pair(a, b)] = result
        return result
    } else {
        val numHighestOneBits = b - bBase + 1
        val numRemainingBits = dfs(a, bBase - 1) + dfs(0, b - bBase)
        val result = numHighestOneBits + numRemainingBits
        dp[Pair(a, b)] = result
        return result
    }
}