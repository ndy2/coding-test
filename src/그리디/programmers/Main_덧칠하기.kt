package 그리디.programmers

fun main() {

    val solution = Solution_덧칠하기()
    println(solution.solution(8, 4, intArrayOf(2, 3, 6)))
    println(solution.solution(5, 4, intArrayOf(1, 3)))
    println(solution.solution(4, 1, intArrayOf(1, 2, 3, 4)))
}

class Solution_덧칠하기 {
    fun solution(n: Int, m: Int, section: IntArray): Int {
        var answer = 0
        var base = -1
        for (s in section) {
            if (s > base) {
                answer++
                base = s + m - 1
            }
        }

        return answer
    }
}