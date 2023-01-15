package kakao2023blind

import java.lang.Math.max
import java.util.*

fun main() {

    class Solution {
        fun solution(s: Int, n: Int, a: IntArray, b: IntArray): Long {

            val qa = getQueue(s, n, a)
            val qb = getQueue(s, n, b)

            qa.forEach { elem -> print("$elem ") }; println()
            qb.forEach { elem -> print("$elem ") }; println()

            var answer = 0L
            while (qa.isNotEmpty() || qb.isNotEmpty()) {
                val ai = qa.pollOrMinusOne()
                val bi = qb.pollOrMinusOne()

                answer += 2 * max(ai, bi)
            }

            return answer
        }

        private fun getQueue(s: Int, n: Int, a: IntArray): Queue<Int> {
            val q = LinkedList<Int>()
            var cur = 0
            var i = n - 1
            while (i >= 0) {
                val amount = if (cur + a[i] <= s) a[i] else s - cur
                a[i] -= amount
                if (cur == 0 && amount > 0) {
                    q.add(i + 1)
                }
                cur += amount
                if (cur == s) {
                    cur = 0
                }
                if (a[i] == 0) i--
            }
            return q
        }

        private fun Queue<Int>.pollOrMinusOne(): Int {
            return if (this.isEmpty()) -1 else this.poll()
        }
    }


    val solution = Solution()
//    println(solution.solution(4, 5, intArrayOf(1, 0, 3, 1, 2), intArrayOf(0, 3, 0, 4, 0)))
//    println(solution.solution(2, 7, intArrayOf(1, 0, 2, 0, 1, 0, 2), intArrayOf(0, 2, 0, 1, 0, 2, 0)))
    println(solution.solution(2, 7, intArrayOf(0, 0, 0, 0, 0, 0, 0), intArrayOf(0, 2, 0, 1, 0, 2, 0)))
    println(solution.solution(10, 7, intArrayOf(0, 0, 0, 0, 0, 0, 0), intArrayOf(0, 2, 0, 1, 0, 2, 0)))
    println(solution.solution(10, 7, intArrayOf(0, 0, 0, 0, 0, 11, 0), intArrayOf(0, 2, 0, 1, 0, 2, 0)))
}

