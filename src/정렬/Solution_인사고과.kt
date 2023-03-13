fun main() {
    val solution인사고과 = Solution_인사고과()
    println(
        solution인사고과.solution(
            arrayOf(
                intArrayOf(0, 0),
                intArrayOf(4, 1),

            )
        )
    )

//    println(
//        solution인사고과.solution(
//            arrayOf(
//                intArrayOf(0, 1), // -1
//
//                intArrayOf(1, 1), // -1
//                intArrayOf(1, 2), // -1
//
//                intArrayOf(2, 2), // -1
//                intArrayOf(2, 3), // -1
//                intArrayOf(2, 4), // -1
//
//                intArrayOf(3, 1), // -1
//                intArrayOf(3, 2), // -1
//                intArrayOf(3, 3), // -1
//
//                intArrayOf(4, 1), // -1
//                intArrayOf(4, 3), // -1
//                intArrayOf(4, 4), // -1
//                intArrayOf(4, 6), // 1
//
//                intArrayOf(5, 0), // -1
//                intArrayOf(5, 1), // -1
//                intArrayOf(5, 2), // 4
//                intArrayOf(5, 5), // 1
//
//                intArrayOf(6, 0), // 6
//                intArrayOf(6, 1), // 4
//                intArrayOf(6, 2), // 3
//            )
//        )
//    )
}

class Solution_인사고과 {
    fun solution(scores: Array<IntArray>): Int {
        val baseA = scores[0][0]
        val baseB = scores[0][1]
        val base = baseA + baseB

        val groups = scores
            .groupBy({ it[0] }, { it[1] })
            .map { Group(it.key, it.value) }
            .sortedBy { it.a }

        var first = true
        var prev = 0
        while (true) {
            val dropAmount = if (first) {
                first = false
                0
            } else {
                prev + 1
            }
            val subGroup = groups.drop(dropAmount)
            val found = subGroup.sortedWith(compareByDescending<Group> { it.bTop }.thenByDescending { it.a }).first()
            val foundIndex = groups.indexOf(found)

            for (idx in prev..foundIndex - 1) {
                groups[idx].criteria = found.bTop
            }
            prev = foundIndex
            if (foundIndex == groups.lastIndex) {
                break
            }
        }

        // 완호가 성공했는지 확인
        val wanhoGroup = groups.first { it.a == baseA }
        if (baseB < wanhoGroup.criteria) {
            return -1
        }

        val greaterThanWanho = groups.sumOf { it.countAPlusBGreaterThan(base) }
        return greaterThanWanho + 1
    }
}

class Group(
    val a: Int,
    val bs: List<Int>,
) {

    var criteria = 0 // b 가 criteria 이상인 녀석만 취급함
    val bTop = bs.max()

    fun countAPlusBGreaterThan(base: Int) = bs.count { it >= criteria && a + it > base }
}