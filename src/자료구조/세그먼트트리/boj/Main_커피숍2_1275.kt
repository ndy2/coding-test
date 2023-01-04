package 자료구조.세그먼트트리.boj

import kotlin.math.max
import kotlin.math.min

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, q) = br.readLine().split(" ").map { it.toInt() }
    val arr = br.readLine().split(" ").map { it.toInt() }.toIntArray()

    val sol = Solution_커피숍2(n, arr)
    repeat(q) {
        sol.proc(br.readLine().split(" ").map { it.toInt() }.toIntArray())
    }
}

class Solution_커피숍2(
    val n: Int,
    val arr: IntArray
) {
    var tree: LongArray = LongArray(4 * n);

    init {
        initTree(0, n - 1, 1)
    }

    fun proc(op: IntArray) {
        println(getRangeSum(0, n - 1, 1, min(op[0], op[1]) -1, max(op[0], op[1]) -1))
        update(0, n - 1, 1, op[2] - 1, op[3].toLong() - arr[op[2] - 1] )
        arr[op[2] - 1] = op[3]
    }

    private fun initTree(start: Int, end: Int, idx: Int) {
        if (start == end) {
            tree[idx] = arr[start].toLong()
        } else {
            val mid = (start + end) / 2
            initTree(start, mid, idx * 2)
            initTree(mid+1, end, idx * 2 + 1)
            tree[idx] = tree[idx * 2] + tree[idx * 2 + 1]
        }
    }

    private fun getRangeSum(start: Int, end: Int, idx: Int, l: Int, r: Int): Long {
        if (l > end || start > r) return 0
        else if (l <= start && end <= r) return tree[idx]
        else {
            val mid = (start + end) / 2
            return getRangeSum(start, mid, idx * 2, l, r) +
                    getRangeSum(mid+1, end, idx * 2 + 1, l, r)
        }
    }

    private fun update(start: Int, end: Int, idx: Int, target: Int, amount: Long) {
        if (target < start || target > end) return
        if (start == end) {
            tree[idx] += amount
        } else {
            val mid = (start + end) / 2
            update(start, mid, idx * 2, target, amount)
            update(mid+1, end, idx * 2 + 1, target, amount)
            tree[idx] = tree[idx*2] + tree[idx*2+1]
        }

    }
}