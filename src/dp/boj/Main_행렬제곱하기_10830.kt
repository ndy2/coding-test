package dp.boj

typealias Row = IntArray
typealias Matrix = Array<Row>

private var dp = mutableMapOf<Long, Matrix>()

fun main() {
    val br = System.`in`.bufferedReader()
    val (nn, b) = br.readLine().split(" ").map { it.toLong() }
    val n = nn.toInt()
    val a = Matrix(n) { Row(n) }
    for (r in 0..n - 1) {
        a[r] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    dp[1] = a
    val result = pow(a, b)
    for (r in 0..n-1){
        for (c in 0..n-1){
            print("${result[r][c]%1000} ")
        }
        println()
    }
}

private fun pow(a: Matrix, b: Long): Matrix {
    if (dp.containsKey(b)) return dp[b]!!
    else {
        val l = b / 2
        val r = b - l

        val result = mul(pow(a, l), pow(a, r))
        dp[b] = result
        return result
    }
}

private fun mul(a: Matrix, b: Matrix): Matrix {
    val s = a.size
    val result = Matrix(s) { Row(s) }
    for (r in 0..s - 1) {
        for (c in 0..s - 1) {
            for (t in 0..s - 1) {
                result[r][c] = (result[r][c] + a[r][t] * b[t][c]).mod(1000)
            }
        }
    }
    return result
}