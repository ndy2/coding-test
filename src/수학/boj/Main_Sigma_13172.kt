import kotlin.math.max
import kotlin.math.min

fun main() {
    val br = System.`in`.bufferedReader()
    val m = br.readLine().toInt()

    var a = 1L
    var b = 0L

    repeat(m) {
        val (c, d) = br.readLine().split(" ").map { it.toInt() }

        val ac = (a * c).mod(1_000_000_007).toLong()
        val bcad = (b * c + a * d).mod(1_000_000_007).toLong()
        val gcd = gcd(max(ac, bcad), min(ac, bcad))

        a = ac / gcd
        b = bcad / gcd
    }
    println((b * a.invMod()).mod(1_000_000_007))
}

private fun gcd(l: Long, r: Long): Long {
    var a = l
    var b = r
    var c: Long
    while (b > 0) {
        c = a % b
        a = b
        b = c
    }
    return a
}

private val map = mutableMapOf<Long, Long>()

private fun Long.invMod(): Long {
    map[1] = this
    return mod(this, 1_000_000_007 - 2)
}

private fun mod(a: Long, exp: Long): Long {
    if (map.containsKey(exp)) return map[exp]!!

    val lExp = exp / 2
    val rExp = exp - lExp

    val lMod = mod(a, lExp)
    val rMod = mod(a, rExp)
    val result = (lMod * rMod).mod(1_000_000_007).toLong()
    map[exp] = result
    return result
}

