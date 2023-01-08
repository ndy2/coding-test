package 이분탐색.boj

import java.lang.Integer.max
import java.lang.Integer.min

private val br = System.`in`.bufferedReader()
private fun readIntArr(): IntArray {
    return br.readLine()
            .split(" ")
            .map { it.toInt() }
            .toIntArray()
}

private lateinit var arr: IntArray
private lateinit var maxTree: IntArray
private lateinit var minTree: IntArray
private lateinit var nums: IntArray
private var answer = Int.MAX_VALUE

fun main() {
    val (n, m) = readIntArr()
    arr = readIntArr()

    initTrees(n, arr)
    if (m == 1) {
        print(maxTree[1] - minTree[1])
        return
    }

    nums = IntArray(n - 1) { it + 1 }
    combination(0, m-1, 0, n)

    print(answer)
}

val indices = mutableListOf(0)
fun combination(len: Int, m: Int, idx: Int, n: Int) {
    if (len == m) {
        indices.add(n)
        search(n,m)
        indices.removeLast()
    }
    for (i in idx until nums.size) {
        indices.add(nums[i])
        combination(len + 1, m, i + 1, n)
        indices.removeLast()
    }
}

fun search(n: Int, m: Int) {
    var maxScore = 0
    for (i in 0.. m) {
        val from = indices[i]
        val to = indices[i + 1] - 1
        val max = getMax(0, n - 1, 1, from, to)
        val min = getMin(0, n - 1, 1, from, to)

        val score = max - min
        maxScore = max(score, maxScore)
    }

    answer = min(maxScore, answer)
}

fun getMax(start: Int, end: Int, idx: Int, left: Int, right: Int): Int {
    if (right < start || end < left) return Int.MIN_VALUE
    if (left <= start && end <= right) return maxTree[idx]

    val mid = (start + end) / 2
    return max(
            getMax(start, mid, idx * 2, left, right),
            getMax(mid + 1, end, idx * 2 + 1, left, right)
    )
}


fun getMin(start: Int, end: Int, idx: Int, left: Int, right: Int): Int {
    if (right < start || end < left) return Int.MAX_VALUE
    if (left <= start && end <= right) return minTree[idx]

    val mid = (start + end) / 2
    return min(
            getMin(start, mid, idx * 2, left, right),
            getMin(mid + 1, end, idx * 2 + 1, left, right)
    )
}

fun initTrees(n: Int, arr: IntArray) {
    maxTree = IntArray(4 * n)
    minTree = IntArray(4 * n)

    setMaxTree(0, n - 1, 1)
    setMinTree(0, n - 1, 1)
}

fun setMaxTree(start: Int, end: Int, idx: Int) {
    if (start == end) {
        maxTree[idx] = arr[start]
        return
    }

    val mid = (start + end) / 2
    setMaxTree(start, mid, idx * 2)
    setMaxTree(mid + 1, end, idx * 2 + 1)
    maxTree[idx] = max(maxTree[idx * 2], maxTree[idx * 2 + 1])
}


fun setMinTree(start: Int, end: Int, idx: Int) {
    if (start == end) {
        minTree[idx] = arr[start]
        return
    }

    val mid = (start + end) / 2
    setMinTree(start, mid, idx * 2)
    setMinTree(mid + 1, end, idx * 2 + 1)
    minTree[idx] = min(minTree[idx * 2], minTree[idx * 2 + 1])
}
