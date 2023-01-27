fun main() {

    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(" ").map { it.toInt() }

    val edge = Array(n + 1) { mutableListOf<Int>() }
    val invEdge = Array(n + 1) { mutableSetOf<Int>() }
    val processed = BooleanArray(n + 1)

    repeat(m) {
        val (a, b) = br.readLine().split(" ").map { it.toInt() }
        edge[a].add(b)
        invEdge[b].add(a)
    }

    val answer = mutableListOf<Int>()
    while (answer.size < n) {

        // select target
        var target = 0
        for (i in 1..n) {
            if (!processed[i] && invEdge[i].isEmpty()) {
                target = i
                break
            }
        }

        // process it!
        for (t in edge[target]) {
            invEdge[t].remove(target)
        }
        processed[target] = true

        answer.add(target)
    }
    println(answer.joinToString(" "))
}