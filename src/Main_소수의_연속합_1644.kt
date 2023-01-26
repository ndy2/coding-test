import kotlin.math.sqrt

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val isPrimes = BooleanArray(n + 1) { true }
    isPrimes[1] = false
    for (d in 2..n / 2) {
        if (isPrimes[d]){
            for (i in 2..n / d) {
                isPrimes[d * i] = false
            }
        }
    }

    val primes = buildList { for (i in 1..n) if (isPrimes[i]) add(i) }
    val size = primes.size

    val sumSum = buildList<Int>(size + 1) {
        add(0)
        for (i in 1..size) {
            add(this[i - 1] + primes[i - 1])
        }
    }

    var answer = 0
    for (i in 0..size - 1) {
        for (j in i + 1..size) {
            if (sumSum[j] - sumSum[i] == n) answer++
        }
    }

    println(answer)
}
