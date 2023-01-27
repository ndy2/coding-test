import kotlin.math.pow

private const val INF = 1_000_000_000

private var n = 0
private lateinit var map: List<List<Int>>
private lateinit var dp: List<MutableList<Int>>

fun main() {

    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()

    map = buildList(n) {
        for (r in 0..n - 1) {
            this.add(br.readLine().split(" ").map {
                val cost = it.toInt()
                if (cost == 0) INF else cost
            })
        }
    }

    //dp[bit][cur]
    dp = List(1.shl(n)) { MutableList(n) { 0 } }

    val bit = 2.0.pow(n).toInt() - 2
    println(getAndSetDp(0, bit))
}

private fun getAndSetDp(cur: Int, bit: Int): Int {
    if (dp[bit][cur] != 0) return dp[bit][cur]
    if (bit == 0) return map[cur][0]

    val buildList = buildList {
        for (b in getBitIndices(bit)) {
            val nb = bit.and((1.shl(b)).inv())
            add(map[cur][b] + getAndSetDp(b, nb))
        }
    }
    val result = buildList.min()
    dp[bit][cur] = result
    return result
}

private fun getBitIndices(bit: Int): List<Int> {
    return buildList {
        for (b in 0..n) {
            if (bit.and(1.shl(b)).shr(b) == 1) add(b)
        }
    }
}
