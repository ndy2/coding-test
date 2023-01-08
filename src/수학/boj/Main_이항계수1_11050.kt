private val br = System.`in`.bufferedReader()

fun main() {
    var (n, k) = br.readLine().split(" ").map { it.toInt() }

    var bunmo = 1
    var bunza = 1

    for (i in 1..k) {
        bunmo *= (n--)
        bunza *= i
    }

    println(bunmo / bunza)
}
