fun main() {
    val br = System.`in`.bufferedReader()
    val (n, s) = br.readLine().split(" ").map { it.toInt() }
    val arr = br.readLine().split(" ").map { it.toInt() }

    println(getCountFromAB(arr, s))
}

private fun getCountFromAB(arr: List<Int>, s: Int): Long {
    if (arr.size == 1) return if (arr[0] == s) 1 else 0
    if (arr.size == 2) {
        val countFromA = if (arr[0] == s) 1 else 0
        val countFromB = if (arr[1] == s) 1 else 0
        val countFromAB = if (arr[0] + arr[1] == s) 1 else 0
        return (countFromA + countFromB + countFromAB).toLong()
    }

    val mid = arr.size / 2
    val aa = arr.take(mid)
    val bb = arr.drop(mid)

    val a = buildSubSumList(aa)
    val b = buildSubSumList(bb)

    var i = 0
    var j = b.size - 1

    var countFromAB = 0L
    while (i < a.size && j >= 0) {
        val cur = a[i] + b[j]
        if (cur < s) {
            i++
        } else if (cur > s) {
            j--
        } else {
            val na = getNumberOfEqualValFrom(a, i)
            val nb = getNumberOfEqualValBefore(b, j)
            countFromAB += na.toLong() * nb.toLong()
            i += na
            j -= nb
        }
    }

    val countFromA = getCountFromAB(aa, s)
    val countFromB = getCountFromAB(bb, s)

    return countFromA + countFromB + countFromAB
}

private fun getNumberOfEqualValFrom(a: List<Int>, from: Int): Int {
    val cur = a[from]
    var idx = from
    while (idx < a.size && a[idx] == cur) {
        idx++
    }
    return idx - from
}

private fun getNumberOfEqualValBefore(a: List<Int>, before: Int): Int {
    val cur = a[before]
    var idx = before
    while (idx >= 0 && a[idx] == cur) {
        idx--
    }
    return before - idx
}

private fun buildSubSumList(arr: List<Int>): List<Int> {
    val n = arr.size
    return buildList {

        for (i in 0..((1 shl n) - 1)) {
            var sum = 0
            var flag = false
            for (j in 0..n - 1) {
                if (i and (1 shl j) != 0) {
                    sum += arr[j]
                    flag = true
                }
            }
            if (flag) add(sum)
        }
    }.sorted()
}
