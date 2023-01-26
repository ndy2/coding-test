import kotlin.math.abs

fun main() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val arr = br.readLine().split(" ").map { it.toInt() }.toIntArray()

    var i = 0
    var j = n - 1
    var minI = 0
    var minJ = 0

    var minAbsSum = Int.MAX_VALUE
    while (i  < j) {
        val absSum = abs(arr[i] + arr[j])
        if (absSum < minAbsSum) {
            minAbsSum = absSum
            minI = i
            minJ = j
        }
        if (arr[i] + arr[j] < 0) {
            i++
        } else {
            j--
        }
    }

    println("${arr[minI]} ${arr[minJ]}")
}