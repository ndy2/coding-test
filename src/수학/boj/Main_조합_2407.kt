import kotlin.math.abs

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val pascalTriangle = Array(n + 1) { Array(it + 1) { "1" } }

    for (r in 2..n) {
        for (c in 1..r / 2) {
            pascalTriangle[r][c] = numStringAdd(pascalTriangle[r - 1][c - 1], pascalTriangle[r - 1][c])
        }
        for (c in (r / 2) + 1..r - 1) {
            pascalTriangle[r][c] = pascalTriangle[r][r - c]
        }
    }

    println(pascalTriangle[n][m].reversed())
}

fun numStringAdd(a: String, b: String): String {

    val l = if (a.length > b.length) a else b
    var r = if (a.length > b.length) b else a
    val gap = abs(a.length - b.length)
    r = buildString { append(r); repeat(gap) { append("0") } }

    var carry = 0
    val answer = StringBuilder()
    for (i in 0..l.length - 1) {

        var result = l[i].digitToInt() + r[i].digitToInt() + carry
        if (result >= 10) {
            result -= 10
            carry = 1
        } else {
            carry = 0
        }

        answer.append(result)
    }
    if (carry == 1) answer.append(1)
    return answer.toString()
}
