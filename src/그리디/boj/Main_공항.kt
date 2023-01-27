fun main() {

    val br = System.`in`.bufferedReader()
    val g = br.readLine().toInt()
    val p = br.readLine().toInt()

    val gates = MutableList(g) { it + 1 }

    var answer = 0
    for (i in 1..p) {
        val t = br.readLine().toInt()
        val bound = gates.lowerBound(t)
        if (bound == -1) break
        gates.removeAt(bound)
        answer++
        if(gates.isEmpty()) break
    }
    println(answer)
}

private fun List<Int>.lowerBound(t: Int): Int {

    return if (first() > t) -1
    else if (last() <= t) lastIndex
    else {
        var l = 0
        var r = lastIndex
        while (l + 1 < r) {
            val mid = (l + r) / 2
            if (get(mid) <= t) {
                l = mid
            } else {
                r = mid
            }
        }
        l
    }
}