package 단순구현.prgrms

fun main() {

    println(
        Solution_카드뭉치().solution(
            arrayOf("a", "b", "c"),
            arrayOf("d"),
            arrayOf("a", "c"),
        )
    )
}

class Solution_카드뭉치 {
    private lateinit var card1: Array<String>
    private lateinit var card2: Array<String>
    private lateinit var goal: Array<String>

    fun solution(cards1: Array<String>, cards2: Array<String>, goal: Array<String>): String {
        this.card1 = cards1
        this.card2 = cards2
        this.goal = goal

        return if (search(0, 0, 0)) "Yes" else "No"
    }

    private fun search(i1: Int, i2: Int, gi: Int): Boolean {
        if (gi == goal.size) {
            return true
        }

        val match1 = if (i1 == card1.size) false else goal[gi] == card1[i1]
        val match2 = if (i2 == card2.size) false else goal[gi] == card2[i2]

        if (!match1 && !match2) { //ff
            return false
        } else if (match1 && match2) { //tt
            return search(i1 + 1, i2, gi + 1) || search(i1, i2 + 1, gi + 1)
        } else if (match1 && !match2) { //tf
            return search(i1 + 1, i2, gi + 1)
        } else if (!match1 && match2) { //ft
            return search(i1, i2 + 1, gi + 1)
        }
        throw IllegalStateException()
    }
}