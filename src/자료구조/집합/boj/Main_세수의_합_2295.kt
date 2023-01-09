package 자료구조.집합.boj

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val arr = buildList(n) { repeat(n) { add(br.readLine().toInt()) } }.sorted()
    val set = HashSet(arr)

    val sumArrSet = buildSet {
        for (i in 0..n - 1) {
            for (j in 0..n - 1) {
                add(arr[i] + arr[j])
            }
        }
    }

    for (k in n - 1 downTo 0) {
        set.forEach {
            val target = arr[k] - it
            if (sumArrSet.contains(target)) {
                println(arr[k])
                return
            }
        }

    }
}