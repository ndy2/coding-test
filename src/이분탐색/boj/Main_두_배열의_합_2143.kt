fun main() {

    val br = System.`in`.bufferedReader()
    val t = br.readLine().toInt()

    val n = br.readLine().toInt()
    val a = br.readLine().split(" ").map { it.toInt() }
    val m = br.readLine().toInt()
    val b = br.readLine().split(" ").map { it.toInt() }

    val sumA = buildSortedSubSumList(n, a)
    val sumB = buildSortedSubSumList(m, b)

    var answer = 0L

    var i = 0
    var j = sumB.lastIndex
    while (i <= sumA.lastIndex && j >= 0) {

        val cur = sumA[i] + sumB[j]
        val lenA = sumA.countEqAfterBinary(i)
        val lenB = sumB.countEqBeforeBinary(j)

        if (cur > t) j -= lenB
        else if (cur < t) i += lenA
        else {
            answer += lenA.toLong() * lenB.toLong()
            i += lenA
            j -= lenB
        }
    }
    println(answer)
}


private fun List<Int>.countEqAfterBinary(i: Int): Int {
    if (last() == this[i]) return lastIndex - i + 1

    var l = i
    var r = lastIndex
    while (l + 1 < r) {

        val mid = (l + r) / 2
        if (this[mid] == this[i]) {
            l = mid
        } else {
            r = mid
        }
    }

    return l - i + 1
}

private fun List<Int>.countEqBeforeBinary(i: Int): Int {
    if (first() == this[i]) return i + 1

    var l = 0
    var r = i
    while (l + 1 < r) {

        val mid = (l + r) / 2
        if (this[mid] == this[i]) {
            r = mid
        } else {
            l = mid
        }
    }

    return i - r + 1
}


private fun buildSortedSubSumList(n: Int, a: List<Int>): List<Int> {
    return buildList(n * (n + 1) / 2) {
        var prev = 0
        for (i in 0..n - 1) {
            add(prev + a[i])
            prev = this[i]
        }

        for (j in 0..n - 1) {
            for (k in j + 1..n - 1) {
                add(this[k] - this[j])
            }
        }
        sort()
    }
}