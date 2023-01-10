package dp.boj

import kotlin.math.min

private var line = ""
private var n = 0

//A BCAABBAA -> check
//AB CAABBAA -> skip
//ABC AABBAA -> skip
//ABCA ABBAA -> skip
//ABCAAB BAA -> skip
//ABCAABB AA -> skip
//ABCAABBA A -> skip

// XXXXX 1 차원으로 충분함
// dp.boj.dp[i][j] : i~j 까지 SubString 의 팰린드롬 분할의 개수 중 최소
private lateinit var dp: Array<IntArray>

fun main() {
    line = System.`in`.bufferedReader().readLine()
    n = line.length
    dp = Array(n) { IntArray(n) { -1 } }
    for (i in 0..n - 1) dp[i][i] = 1

    print(getAndSetDp(0, n - 1))
}

private fun getAndSetDp(i: Int, j: Int): Int {
    if (dp[i][j] != -1) {
        return dp[i][j]
    }

    val pair = isPalindrome(i, j)
    if (pair.first) {
        if (!pair.second) {
            val len = j - i
            for (dist in 0..len / 2) {
                dp[i + dist][j - dist] = 1
            }
        }
        return 1
    } else {
        var value = Int.MAX_VALUE
        for (mid in i..j - 1) {
            if (isPalindrome(i, mid).first){
                value = min(value, 1 + getAndSetDp(mid + 1, j))
            }
        }
        dp[i][j] = value
        return value
    }
}

// ret.first : 팰린드롬, ret.second : dp.boj.dp 사용 여부
private fun isPalindrome(from: Int, to: Int): Pair<Boolean, Boolean> {
    if (dp[from][to] == 1) return Pair(true, true)

    val len = to - from
    for (dist in 0..len / 2) {
        if (line[from + dist] != line[to - dist]) return Pair(false, false)
    }
    return Pair(true, false)
}

