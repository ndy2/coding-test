fun main() {
    val br = System.`in`.bufferedReader()
    val (n, m, b) = br.readLine().split(" ").map { it.toInt() }

    val blocks = mutableListOf<Int>()
    for (r in 0..n - 1) {
        br.readLine().split(" ").map { it.toInt() }.toCollection(blocks)
    }
    blocks.sort()
    val blocksSumLower = IntArray(n * m + 1)
    val blocksSumUpper = IntArray(n * m + 1)
    for (i in 1..n * m) {
        blocksSumLower[i] = blocksSumLower[i - 1] + blocks[i - 1]
        blocksSumUpper[n * m - i] = blocksSumUpper[n * m - i + 1] + blocks[n * m - i]
    }

    var minTimeHeight = 0
    var minTime = Int.MAX_VALUE

    for (t in blocks.first()..blocks.last()) {
        var time = 0
        var inventory = b

        val l = blocks.lowerBound(t)
        val u = blocks.upperBound(t)

        val requiredBlockAmount = t * (l + 1) - blocksSumLower[l + 1]
        val breakBlockAmount = blocksSumUpper[u] - t * (n * m - u)

        time += 2 * breakBlockAmount
        time += requiredBlockAmount

        inventory += breakBlockAmount
        inventory -= requiredBlockAmount

        if (inventory >= 0 && time <= minTime) {
            minTime = time
            minTimeHeight = t
        }
    }
    println("$minTime $minTimeHeight")
}


// 1 1 2 2 3 3 4 4 4 4  -> getLowerBound(3) return : 3
// t 보다 작은 마지막 인덱스, 모두 t 보다 크거나 같은 경우 -1
private fun List<Int>.lowerBound(t: Int): Int {
    return if (this.first() >= t) {
        -1
    } else if (this.last() < t) {
        this.size - 1
    } else {
        var l = 0
        var r = this.size - 1
        while (l + 1 < r) {
            val mid = (l + r) / 2
            if (this[mid] < t) {
                l = mid
            } else {
                r = mid
            }
        }
        l
    }
}

// 1 1 2 2 3 3 4 4 4 4  -> getUpperBound(3) return : 6
// t 보다 큰 첫 인덱스, 모두 t 보다 작거나 같으면 this.size
private fun List<Int>.upperBound(t: Int): Int {
    return if (this.first() > t) {
        0
    } else if (this.last() <= t) {
        this.size
    } else {
        var l = 0
        var r = this.size - 1
        while (l + 1 < r) {
            val mid = (l + r) / 2
            if (this[mid] > t) {
                r = mid
            } else {
                l = mid
            }
        }
        r
    }
}
