import kotlin.math.pow

private var n = 0
private lateinit var map: List<List<Int>>
private lateinit var dp: List<MutableList<Int>>

fun main() {

    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()

    map = buildList(n) {
        for (r in 0..n - 1) {
            this.add(br.readLine().split(" ").map { it.toInt() })
        }
    }

    //dp[bit][to]
    dp = List(1.shl(n)) { MutableList(n) { 0 } }

    val bit = 2.0.pow(n).toInt() - 2
    println(getAndSetDp(0, bit, 0))
}

private fun getAndSetDp(from: Int, bit: Int, to: Int): Int {
    if (bit == 0) {
        return map[from][to]
    }

    val buildList = buildList {
        for (b in getBitIndices(bit)) {
            val nb = bit.and((1.shl(b)).inv())
            add(map[from][b] + getAndSetDp(b, nb, to))
        }
    }

    val result = buildList.min()
    dp[bit][to] = result
    return result
}

private fun getBitIndices(bit: Int): List<Int> {
    return buildList {
        for (b in 0..n) {
            if (bit.and(1.shl(b)).shr(b) == 1) add(b)
        }
    }
}
