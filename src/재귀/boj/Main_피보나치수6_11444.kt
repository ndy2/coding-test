private lateinit var dp: MutableMap<Long, Long>
private const val MOD = 1_000_000_007

fun main() {

    val n = System.`in`.bufferedReader().readLine().toLong()

    dp = mutableMapOf()
    dp[0L] = 0L
    dp[1L] = 1L
    dp[2L] = 1L
    dp[3L] = 2L
    dp[4L] = 3L
    dp[5L] = 5L

    fib(n)
    println(dp[n])
}

private fun fib(n: Long): Long {
    if (dp.containsKey(n)) {
        return dp[n]!!
    }

    val result: Long = if (n % 2 == 0L) {
        val a = fib(n / 2)
        val b = fib(n / 2 - 1)
        val c = fib(n / 2 + 1)

        (a * (b + c).mod(MOD)).mod(MOD).toLong()
    } else {
        val a = fib((n + 1) / 2)
        val b = fib((n - 1) / 2)

        ((a * a).mod(MOD) + (b * b).mod(MOD)).mod(MOD).toLong()
    }
    dp[n] = result
    return result
}