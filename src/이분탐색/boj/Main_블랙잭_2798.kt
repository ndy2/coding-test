package 이분탐색.boj

import kotlin.math.max

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(" ").map { it.toInt() }

    val arr = br.readLine().split(" ").map { it.toInt() }.sorted()
    var answer = 0

    for (i in 0..n - 3) {
        for (j in i + 1..n - 2) {
            val from = j + 1
            val target = m - (arr[i] + arr[j])

            if (target < arr[from]) {
                continue
            }

            val idx = lowerBound(arr, from, target)
            answer = max(answer, arr[i] + arr[j] + arr[idx])
        }
    }

    println(answer)
}

fun lowerBound(list: List<Int>, from: Int, target: Int): Int {
    var l = from
    var r = list.size - 1

    while (l + 1 < r) {
        val mid = (l + r) / 2
        if (list[mid] <= target) {
            l = mid
        } else {
            r = mid
        }
    }
    return l
}
