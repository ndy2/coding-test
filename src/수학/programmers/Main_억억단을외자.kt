package 수학.programmers
fun main() {
    println("hello")
    println(Solution_억억단().solution(20, intArrayOf(1, 2, 3, 4, 5, 6, 7, 8)).contentToString())
}

class Solution_억억단 {

    var e = 0
    lateinit var arr: IntArray
    lateinit var store: IntArray

    fun solution(e: Int, starts: IntArray): IntArray {
        this.e = e
        initArr()
        initStore()

        return starts.map { store[it-1] }
            .toIntArray()
    }

    private fun initStore() {
        store = IntArray(e)
        var max = 0
        var maxIdx = 0
        for (i in e - 1 downTo 0) {
            val cur = arr[i]
            if (cur >= max) {
                max = cur
                maxIdx = i
            }
            store[i] = maxIdx + 1
        }
    }

    private fun initArr() {
        arr = IntArray(e)
        for (i in 1..e) {
            for (j in 1..e / i) {
                arr[i * j - 1]++
            }
        }
    }
}