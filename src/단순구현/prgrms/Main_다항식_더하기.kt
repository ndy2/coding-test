package 단순구현.prgrms

class Solution {
    fun solution(polynomial: String): String {
        var a = 0
        var b = 0

        polynomial.split(" ").forEach {
            if (it.endsWith("x")) a += it.dropLast(1).toIntOrNull() ?: 1
            else b += it.toIntOrNull() ?: 0
        }

        return when {
            a == 1 && b != 0 -> "x + ${b}"
            a == 1 && b == 0 -> "x"
            a != 0 && b != 0 -> "${a}x + ${b}"
            a != 0 && b == 0 -> "${a}x"
            a == 0 && b != 0 -> "${b}"
            else -> "0"
        }
    }
}