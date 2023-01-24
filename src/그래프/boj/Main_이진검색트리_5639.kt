private val br = System.`in`.bufferedReader()
private lateinit var prefix: IntArray
private lateinit var postfix: IntArray
private var count = 1

fun main() {
    prefix = buildList {
        while (true) {
            val line = br.readLine()
            if (line.isEmpty()) break
            add(line.toInt())
        }
    }.toIntArray()
    val n = prefix.size
    postfix = IntArray(n)

    dfs(0, n, 0, n)
    println(postfix.joinToString("\n"))
}

private fun dfs(from: Int, to: Int, l: Int, r: Int) {
    if (from >= to) {
        return
    } else {
        val rootValue = prefix[from]
        postfix[r - 1] = rootValue
        val rFrom = prefix.lowerBoundBetween(rootValue, from, to)
        if (from + 1 == to) return
        val lLen = rFrom - from - 1
        dfs(from + 1, rFrom, l, l + lLen)
        dfs(rFrom, to, l + lLen , r - 1)
    }
}

//idx 이후의 값 중에서 value 보다 큰 첫번째 인덱스를 반환
//처음 부터 value 보다 크다면 from 반환
//모든 원소가 value 보다 작다면 to 반환
//from - inclusive, to - exclusive
private fun IntArray.lowerBoundBetween(value: Int, from: Int, to: Int): Int {
    if (this[from] > value) return from
    else if (this[to - 1] < value) return to

    var l = from
    var r = to - 1
    while (l + 1 < r) {
        val mid = (l + r) / 2
        if (this[mid] > value) {
            r = mid
        } else {
            l = mid
        }
    }
    return r
}