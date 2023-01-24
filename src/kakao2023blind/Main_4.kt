package kakao2023blind

fun main() {
    class Solution {
        fun solution(numbers: LongArray): IntArray {
            return numbers.map { subSolution(it) }.toIntArray()
        }

        private fun getHeight(number: Long): Int {

            return when (number) {
                1L -> 1
                in 2..7 -> 2
                in 8..127 -> 3
                in 128..32767 -> 4
                in 32768..2147483647 -> 5
                else -> 6
            }
        }

        private val len = intArrayOf(-1, 1, 3, 7, 15, 31, 63)
        private val mid = intArrayOf(-1, 0, 1, 3, 7, 15, 31)
        private val width = intArrayOf(-1, 0, 1, 2, 4, 8, 16)

        private var flag = false

        private fun subSolution(number: Long): Int {
            val h = getHeight(number)
            val l = len[h]
            val m = mid[h]
            val s = number.toString(2).padStart(l, '0')

            flag = false
            if (s[m] == '0') return 0

            dfs1(s, m, h)
            return if (flag) 0 else 1
        }

        private fun dfs1(s: String, i: Int, ch: Int) {
            val c = s[i]
            if (ch == 1) return

            if (c == '1') {
                dfs1(s, i - width[ch], ch - 1)
                dfs1(s, i + width[ch], ch - 1)
            } else if (c == '0') {
                dfs0(s, i - width[ch], ch - 1)
                dfs0(s, i + width[ch], ch - 1)
            }
        }

        private fun dfs0(s: String, i: Int, ch: Int) {
            val c = s[i]

            if (c == '0') {
                if (ch == 1) return
                dfs0(s, i - width[ch], ch - 1)
                dfs0(s, i + width[ch], ch - 1)
            } else if (c == '1') {
                flag = true
            }
        }
    }

    val solution = Solution()
//    println(solution.solution(longArrayOf(7, 42, 5)).contentToString())
//    println(solution.solution(longArrayOf(63, 111, 95)).contentToString())
//    println(solution.solution(longArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)).contentToString())
    println(solution.solution(longArrayOf(
        4294967296
    )).contentToString())

}