package 누적합.programmers

import kotlin.math.max

fun main() {

    println(Solution_연속_펄스_부분_수열의_합().solution(intArrayOf(2, 3, -6, 1, 3, -1, 2, 4)))
    println(Solution_연속_펄스_부분_수열의_합().solution(intArrayOf(1, -2, 3, -4, 5, -6, 7, -8, 9, -10)))
}

class Solution_연속_펄스_부분_수열의_합 {
    fun solution(sequence: IntArray): Long {
        val seq1 = sequence.timesNegOne { it % 2 == 0 }
        val seq2 = sequence.timesNegOne { it % 2 == 1 }

        val subSum1 = seq1.subSum()
        val subSum2 = seq2.subSum()

        val max1 = subSum1.maxOrNull()!!
        val min1 = subSum1.maxOrNull()!!

        val max2 = subSum2.maxOrNull()!!
        val min2 = subSum2.maxOrNull()!!

        val max1Idx = subSum1.indexOfLast { it == max1 }
        val min1Idx = subSum1.indexOfFirst { it == min1 }

        val max2Idx = subSum2.indexOfLast { it == max2 }
        val min2Idx = subSum2.indexOfFirst { it == min2 }

        val maxDiff1 = resolve(min1Idx, max1Idx, max1, min1, subSum1)
        val maxDiff2 = resolve(min2Idx, max2Idx, max2, min2, subSum2)

        return max(maxDiff1, maxDiff2)
    }

    private fun resolve(min1Idx: Int, max1Idx: Int, max1: Long, min1: Long, subSum1: LongArray) =
            if (min1Idx < max1Idx) max1 - min1
            else {
                val subSum1WithIndex = subSum1.withIndex()
                val targetA =
                        if (max1Idx == 0) max1
                        else max1 - subSum1WithIndex.minByOrNull { if (it.index >= max1Idx) Long.MAX_VALUE else it.value }!!.value
                val targetB =
                        if (min1Idx == subSum1.lastIndex) min1
                        else subSum1WithIndex.maxByOrNull { if (it.index <= min1Idx) Long.MIN_VALUE else it.value }!!.value - min1

                max(targetA, targetB)
            }

    private fun IntArray.timesNegOne(predicate: (Int) -> Boolean): IntArray {
        val result = IntArray(this.size)
        for ((i, value) in this.withIndex()) {
            result[i] = if (predicate(i)) -value else value
        }
        return result
    }

    private fun IntArray.subSum(): LongArray {
        val result = LongArray(this.size + 1)
        for ((i, value) in this.withIndex()) {
            result[i + 1] = result[i] + value
        }
        return result
    }
}