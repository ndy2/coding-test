package 수학.boj

fun main() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val arr = br.readLine().split(" ").map { it.toInt() }.toIntArray()

    Solution_고층건물_1027(n, arr).solve()
}

class Solution_고층건물_1027(
    val n: Int,
    val arr: IntArray
) {

    fun solve() {
        val counts = IntArray(this.n)
        for (cIdx in 0..n - 1) {
            val cur = arr[cIdx]
            for (nIdx in cIdx + 1..n - 1) {
                if(cIdx==4 && nIdx==8){
                    println()
                }
                val next = arr[nIdx]
                var flag = true
                inner@ for (tIdx in cIdx + 1..nIdx - 1) {
                    // (cIdx, cur), (nIdx, next) 를 잊는 line (tIdx - cIdx : nIdx - tIdx) 내분점
                    val ty = ((cur * (nIdx - tIdx).toLong()) + (next * (tIdx - cIdx).toLong())) / (nIdx - cIdx).toDouble()

                    if (arr[tIdx].toDouble() >= ty) {
                        flag = false
                        break@inner
                    }
                }
                if (flag) {
                    counts[cIdx]++
                    counts[nIdx]++
                }
            }
        }

        print(counts.max())
    }

}