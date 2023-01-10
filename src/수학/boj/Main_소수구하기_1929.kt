package 수학.boj

import kotlin.math.sqrt

fun main() {
    val (m,n) = System.`in`.bufferedReader().readLine().split(" ").map { it.toInt() }

    for (a in m..n){
        if (isPrime(a)){
            println(a)
        }
    }
}

private fun isPrime(a: Int): Boolean {

    if (a == 1) return false
    if (a == 2 || a == 3) return true

    val b = sqrt(a.toDouble()).toInt()
    for (i in 2..b) {
        if (a % i == 0) return false
    }
    return true
}