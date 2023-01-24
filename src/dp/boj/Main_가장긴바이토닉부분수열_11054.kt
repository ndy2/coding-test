import kotlin.math.max


private var n = 0
private lateinit var arr: IntArray

fun main() {
    init()

    // i 번째 인덱스를 마지막으로 하는 최장 증가수열의 길이
    val inc = getIncs(arr)
    arr.reverse()
    val dec = getIncs(arr)
    dec.reverse()

    var answer = 0
    for (i in 0..n - 1) {
        answer = max(answer, inc[i + 1] +  dec[i] - 1)
    }
    println(answer)
}

private fun getIncs(arr: IntArray): IntArray {
    val inc = IntArray(n + 1)
    for (i in 1..n) {
        val cur = arr[i]
        for (j in 0..i - 1) {
            if (arr[j] < cur) inc[i] = max(inc[i], inc[j] + 1)
        }
    }
    return inc
}

private fun init() = System.`in`.bufferedReader().use { br ->
    n = br.readLine().toInt()
    arr = ("0 " + br.readLine() + " 0").split(" ").map { it.toInt() }.toIntArray()
}
