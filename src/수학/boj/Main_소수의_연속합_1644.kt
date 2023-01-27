package 수학.boj

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    if(n==1) {
        println(0)
        return
    }

    val isPrimes = BooleanArray(n + 1) { true }
    isPrimes[1] = false
    for (d in 2..n / 2) {
        if (isPrimes[d]) {
            for (i in 2..n / d) {
                isPrimes[d * i] = false
            }
        }
    }

    val primes = buildList { for (i in 1..n) if (isPrimes[i]) add(i) }

    val size = primes.size
    var answer = 0
    var curSum = primes[0]
    var l = 0
    var r = 0
    while (l < size) {
        if (curSum == n) {
            answer++
            curSum -= primes[l++]
        } else if (curSum < n) {
            if(++r==size) break
            curSum += primes[r]
        } else {
            curSum -= primes[l++]
        }
    }
    println(answer)
}
