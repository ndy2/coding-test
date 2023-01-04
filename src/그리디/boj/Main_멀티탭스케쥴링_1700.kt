package 단순구현.boj

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, k) = br.readLine().split(" ").map { it.toInt() }
    val schedule = br.readLine().split(" ").map { it.toInt() }.toIntArray()

    // arr[i] : i 번째 플러그의 상태 (0: 비어있음)
    val arr = IntArray(n)

    var answer = 0
    for ((i, s) in schedule.withIndex()) {
        if (arr.contains(s)) {
            continue
        } else if (arr.contains(0)) {
            arr[arr.indexOfFirst { it == 0 }] = s
        } else {
            // i 이후의 스케쥴을 통해 가장 늦게 (안) 사용되는 인덱스를 선택
            arr[victim(i, schedule, arr)] = s
            answer++
        }
    }
    println(answer)
}

private fun victim(i: Int, schedule: IntArray, arr: IntArray): Int {
    var maxValue = -1
    var maxIndex = -1
    for ((index, a) in arr.withIndex()) {
        val firstIdxInScheduleAfterI = schedule.drop(i).indexOfFirst { it == a }
        if (firstIdxInScheduleAfterI == -1) return index

        if (firstIdxInScheduleAfterI > maxValue) {
            maxValue = firstIdxInScheduleAfterI
            maxIndex = index
        }
    }
    return maxIndex
}