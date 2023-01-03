package 이분탐색.programmers

fun main() {
    val solution = Solution()
    println(solution.solution(7, 3, intArrayOf(4, 2, 4, 5, 3, 3, 1))) //5
    println(solution.solution(2, 4, intArrayOf(3, 3, 3, 3))) //4
    println(solution.solution(2, 3, intArrayOf(3, 3, 3, 3))) //3
    println(solution.solution(2, 2, intArrayOf(3, 3, 3, 3))) //2
    println(solution.solution(2, 1, intArrayOf(3, 3, 3, 3))) //1
}

class Solution {

    private var n: Int = 0
    private var k: Int = 0
    private var len: Int = 0
    private lateinit var arr: IntArray

    fun solution(n: Int, k: Int, enemy: IntArray): Int {
        this.n = n
        this.k = k
        this.arr = enemy
        this.len = enemy.size

        var lo = 0
        var hi = enemy.size

        while (lo + 1 < hi) {
            val mid = (lo + hi) / 2
            if (check(mid)) {
                lo = mid
            } else {
                hi = mid
            }
        }
        return lo + 1
    }

    // 적을 t번 라운드까지 무적권을 k 번 이하로 사용해서 막아낼 수 있는지 여부
    private fun check(t: Int): Boolean {
        if (t == len) return false // 마지막 경계의 check 여부는 항상 false 로 함

        val sub = arr.copyOfRange(0, t + 1)
        sub.sortDescending()
        return sub.drop(k).sumOf { it.toLong() } <= n.toLong()
    }
}