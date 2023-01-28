import kotlin.math.min

private const val INF = 10_000_000

fun main() {

    val br = System.`in`.bufferedReader()
    val arr = br.readLine().split(" ").map { it.toInt() }.dropLast(1).toIntArray()

    // prev[b] - 이전 단계에서 두 발을 (a,b) 에 위차 하는 cost
    var prev = IntArray(5) { INF }

    // cur[d] - 현재 두 발을 (c,d) 에 위차 하는 cost
    prev[0] = 0

    val cost = arrayOf(
        intArrayOf(-1, 2, 2, 2, 2),
        intArrayOf(-1, 1, 3, 4, 3),
        intArrayOf(-1, 3, 1, 3, 4),
        intArrayOf(-1, 4, 3, 1, 3),
        intArrayOf(-1, 3, 4, 3, 1),
    )

    var a = 0
    for (c in arr) {
        var cur = IntArray(5) { INF }

        if (a == c) {
            cur = prev.map { it + 1 }.toIntArray()
        } else {
            for (b in 0..4) {
                if (a == b && a != 0) continue

                // (a,b) -> (c,d)
                for (d in 0..4) {
                    if (c == d) continue
                    else if (a == d)
                        cur[d] = min(cur[d], prev[b] + cost[b][c])
                    else if (b == d)
                        cur[d] = min(cur[d], prev[b] + cost[a][c])

                }
            }
        }
        a = c
        prev = cur
    }
    println(prev.min())
}