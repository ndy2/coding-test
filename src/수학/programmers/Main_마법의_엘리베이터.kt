package 수학.programmers

fun main() {
    println(Solution().solution(16))
    println(Solution().solution(2554))
    println(Solution().solution(9999))
    println(Solution().solution(9900))
    println(Solution().solution(8900))
    println(Solution().solution(5555))
    println(Solution().solution(55))
    println(Solution().solution(45))
    println(Solution().solution(75)) //8
    println(Solution().solution(555)) //14
    println(Solution().solution(155)) //11
    println(Solution().solution(646)) //13
    println(Solution().solution(7655)) //15
    println(Solution().solution(57595358)) //26


}

class Solution {

    fun solution(s: Int): Int {
        var storey = s
        var answer = 0
        while (storey != 0) {
            if (storey % 10 == 5) {
                val temp = storey / 10
                if (temp % 10 >= 5) {
                    answer += 5
                    storey += 5
                } else {
                    answer += 5
                }
            }
            else if (storey % 10 > 5) {
                answer += 10 - (storey % 10)
                storey += 10
            }
            else if (storey % 10 < 5) {
                answer += storey % 10
            }
            storey /= 10
        }
        return answer
    }
}