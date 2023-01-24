package kakao2023blind

import java.time.LocalDate

fun main() {

    class Solution {
        fun solution(today: String, terms: Array<String>, privacies: Array<String>): IntArray {

            val (ty, tm, td) = today.split(".").map { it.toInt() }
            val todayDateTime = LocalDate.of(ty, tm, td)
            val termMap = terms.associateBy({ it[0] }, { it.substring(2).toLong() })

            val answer = mutableListOf<Int>()
            for (i in 0..privacies.size - 1) {
                val (ymd, t) = privacies[i].split(" ")
                val (y, m, d) = ymd.split(".").map { it.toInt() }
                val termMonth = termMap[t[0]]!!
                val maxPrivacyStoreDateTime = LocalDate.of(y, m, d).plusMonths(termMonth).minusDays(1L)
                if (todayDateTime.isAfter(maxPrivacyStoreDateTime)) {
                    answer.add(i + 1)
                }
            }

            return answer.toIntArray()
        }
    }

    val solution = Solution()

    println(
        solution.solution(
            "2022.05.19",
            arrayOf("A 6", "B 12", "C 3"),
            arrayOf("2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C")
        ).contentToString()
    )

}

