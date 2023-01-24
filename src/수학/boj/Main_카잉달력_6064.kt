fun main() {

    val br = System.`in`.bufferedReader()
    repeat(br.readLine().toInt()) {

        var check = false
        var (m, n, x, y) = br.readLine().split(" ").map { it.toInt() }
        x--
        y--
        for (i in x..n * m step m) {
            if (i % n == y) {
                println(i + 1)
                check = true
                break
            }
        }
        if (!check) {
            println(-1)
        }
    }


}
