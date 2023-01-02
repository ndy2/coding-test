package 시뮬레이션.programmers

fun main() {

    println(
        Solution_테이블().solution(
            arrayOf(
                intArrayOf(2, 2, 6),
                intArrayOf(1, 5, 10),
                intArrayOf(4, 2, 9),
                intArrayOf(3, 8, 3)
            ), 2, 2, 3
        )
    )
}

class Solution_테이블 {
    fun solution(data: Array<IntArray>, col: Int, row_begin: Int, row_end: Int): Int {
        val colIdx = col - 1
        data
            .sortWith { a, b -> if (a[colIdx] == b[colIdx]) b[0] - a[0] else a[colIdx] - b[colIdx] }

        var answer = 0;
        data
            .drop(row_begin - 1)
            .dropLast(data.size - row_end)
            .forEachIndexed { i, arr ->
                run {
                    val si = arr.sumOf { it.mod(i + row_begin) }
                    answer = answer.xor(si)
                }
            }

        return answer
    }
}