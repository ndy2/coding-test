package 그래프.MST.Main_행성터널_2887

private lateinit var parents: IntArray
private lateinit var edges: MutableList<Edge>

private data class Edge(val a: Int, val b: Int, val cost: Int) : Comparable<Edge> {
    override fun compareTo(other: Edge) = cost - other.cost
}

private data class Point(val idx: Int, val value: Int) : Comparable<Point> {
    override fun compareTo(other: Point) = value - other.value
}

fun main() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val x = mutableListOf<Point>()
    val y = mutableListOf<Point>()
    val z = mutableListOf<Point>()

    for (i in 0..n - 1) {
        val (_x, _y, _z) = br.readLine().split(" ").map { it.toInt() }
        x.add(Point(i, _x))
        y.add(Point(i, _y))
        z.add(Point(i, _z))
    }
    edges = mutableListOf()

    x.sort()
    y.sort()
    z.sort()

    for (i in 0..n - 2) {
        edges.add(Edge(x[i + 1].idx, x[i].idx, x[i + 1].value - x[i].value))
        edges.add(Edge(y[i + 1].idx, y[i].idx, y[i + 1].value - y[i].value))
        edges.add(Edge(z[i + 1].idx, z[i].idx, z[i + 1].value - z[i].value))
    }

    edges.sort()
    parents = IntArray(n) { it }

    var answer = 0
    for (i in 0..edges.size - 1) {
        val (a, b, cost) = edges[i]
        val pa = find(a)
        val pb = find(b)
        if (pa != pb) {
            parents[pb] = pa
            answer += cost
        }
    }
    println(answer)

    br.close()
}

private fun find(a: Int): Int {
    return if (parents[a] == a) a
    else {
        parents[a] = find(parents[a])
        parents[a]
    }
}


