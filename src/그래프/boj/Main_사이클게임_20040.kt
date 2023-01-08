package 그래프.boj

private val br = System.`in`.bufferedReader()
private lateinit var parents: IntArray

fun main() {
    val (n, m) = readIntList()
    parents = IntArray(n) { it }

    var answer = 0
    for (round in 1..m){
        val (a, b) = readIntList()
        if(hasSameParent(a,b)){
            answer = round
            break
        }
        union(a, b)
    }
    println(answer)
}

private fun readIntList(): List<Int> {
    return br.readLine().split(" ").map { it.toInt() }
}

private fun hasSameParent(a: Int, b: Int): Boolean {
    return find(a) == find(b)
}

private fun union(a: Int, b: Int) {
    val pa = find(a)
    val pb = find(b)

    if (pa != pb) {
        parents[pa] = pb
    }
}

private fun find(a: Int): Int {
    if (parents[a] == a) {
        return a
    } else {
        parents[a] = find(parents[a])
        return parents[a]
    }
}
