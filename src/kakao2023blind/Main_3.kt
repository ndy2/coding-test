package kakao2023blind

import kotlin.math.max

fun main() {

    class Solution {
        var u = 0
        var e = 0
        var answer = intArrayOf(0, 0)
        lateinit var percent: IntArray //discount percent for each emoticons
        lateinit var users: Array<IntArray>
        lateinit var emoticons: IntArray
        lateinit var bounds: Map<Int, Int>

        fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
            this.users = users
            this.emoticons = emoticons
            this.e = emoticons.size
            this.u = users.size
            this.percent = IntArray(e)
            users.sortBy { it[0] }
            initBound()

            for (p in 10..40 step 10) {
                dfs(0, p)
            }

            return answer
        }

        private fun dfs(idx: Int, t: Int) {
            percent[idx] = t
            if (idx == e - 1) {
                letsGo()
            } else {
                for (p in 10..40 step 10) {
                    dfs(idx + 1, p)
                }
            }
        }

        private fun initBound() {
            bounds = buildMap {
                this[10] = users.indexOfLast { it[0] <= 10 }
                this[20] = users.indexOfLast { it[0] <= 20 }
                this[30] = users.indexOfLast { it[0] <= 30 }
                this[40] = users.indexOfLast { it[0] <= 40 }
            }
        }

        private fun letsGo() {
            val payments = IntArray(u)
            for (i in 0..e - 1) {
                val p = percent[i]
                val bound = bounds[p]!!

                for (ui in 0..bound) {
                    payments[ui] += emoticons[i].discount(p)
                }
            }

            var paymentSum = 0
            var emoticonPlus = 0
            for (ui in 0..u - 1) {
                if (payments[ui] >= users[ui][1]) {
                    emoticonPlus++
                } else {
                    paymentSum += payments[ui]
                }
            }
            if (emoticonPlus > answer[0]) {
                answer[0] = emoticonPlus
                answer[1] = paymentSum
            } else if (emoticonPlus == answer[0]) {
                answer[1] = max(paymentSum, answer[1])
            }
        }

        private fun Int.discount(p: Int): Int {
            return this - this * p / 100
        }

    }

    val solution = Solution()
    println(
        solution.solution(
            arrayOf(
                intArrayOf(40, 2900),
                intArrayOf(23, 10000),
                intArrayOf(11, 5200),
                intArrayOf(5, 5900),
                intArrayOf(40, 3100),
                intArrayOf(27, 9200),
                intArrayOf(32, 6900)
            ), intArrayOf(1300, 1500, 1600, 4900)
        ).contentToString()
    )
}

