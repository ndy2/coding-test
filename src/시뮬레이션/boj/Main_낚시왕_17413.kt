package 시뮬레이션.boj

private var n = 0
private var m = 0
private var numShark = 0

private lateinit var map: Array<IntArray>
private lateinit var sharks: List<Shark> // null if dead or caught

private var answer = 0

fun main() {
    readAndInit()
    var fisher = 0
    var t = 0
    while (t < m) {
        // 1. 낚시왕이 오른쪽으로 한칸 이동한다.
        fisher++

        // 2. 낚시왕이 있는 열의 상어 중 가장 땅과 가까운 상어를 잡는다.
        doFishing(fisher)

        // 3. 상어가 이동한다.
        sharkMoves()

        // 4. 1초가 흐른다.
        t++
    }

    println(answer)
}

private fun doFishing(fisher: Int) {
    for (r in 0..n - 1) {
        val shark = map[r][fisher - 1]
        if (shark != -1) {
            answer += sharks[shark].z
            map[r][fisher - 1] = -1
            break
        }
    }
}


private val dy = arrayOf(0, -1, 1, 0, 0)
private val dx = arrayOf(0, 0, 0, 1, -1)
private val flipD = arrayOf(0, 2, 1, 4, 3)

fun getEdgeCoor(r: Int, c: Int, d: Int, dist: Int) = Pair(r + dy[d] * dist, c + dx[d] * dist)
private fun getDistToEdge(r: Int, c: Int, d: Int) =
    when (d) {
        1 -> r - 0
        2 -> n - r - 1
        3 -> m - c - 1
        4 -> c - 0
        else -> throw IllegalStateException()
    }

fun getDistOfOneWayTrip(d: Int) =
    when (d) {
        1, 2 -> n - 1
        3, 4 -> m - 1
        else -> throw IllegalStateException()
    }

fun sharkMoves() {
    val nextMap = Array(n) { Array(m) { mutableSetOf<Int>() } }
    for (r in 0..n - 1) {
        for (c in 0..m - 1) {
            val shark = map[r][c]
            if (shark != -1) {
                var (s, d, _) = sharks[shark]
                val distToEdge = getDistToEdge(r, c, d)
                var ty: Int
                var tx: Int

                if (s <= distToEdge) {
                    // naive approach
                    ty = r + s * dy[d]
                    tx = c + s * dx[d]
                    nextMap[ty][tx].add(shark)
                } else {
                    //flip occurs
                    // 1. move to the edge and do first flip
                    val edgeCoor = getEdgeCoor(r, c, d, distToEdge)
                    ty = edgeCoor.first; tx = edgeCoor.second
                    d = flipD[d]

                    // 2. proc remain
                    val distOfOneWayTrip = getDistOfOneWayTrip(d)
                    val remain = (s - distToEdge) % (2 * distOfOneWayTrip)

                    if (remain <= distOfOneWayTrip) {
                        // even time flip occurs
                        ty += remain * dy[d]
                        tx += remain * dx[d]
                    } else {
                        // odd time flip occurs
                        val invRemain = 2 * distOfOneWayTrip - remain
                        ty += invRemain * dy[d]
                        tx += invRemain * dx[d]
                        d = flipD[d]
                    }
                }

                sharks[shark].d = d
                nextMap[ty][tx].add(shark)
            }
        }
    }

    // survival of the largest
    for (r in 0..n - 1) {
        for (c in 0..m - 1) {
            map[r][c] = -1
            if (nextMap[r][c].isNotEmpty()) {
                map[r][c] = nextMap[r][c].maxBy { sharks[it].z }
            }
        }
    }
}


private val br = System.`in`.bufferedReader()
private fun readIntList() = br.readLine().split(" ").map { it.toInt() }
private fun readAndInit() {
    val (_r, _c, _m) = readIntList()
    n = _r
    m = _c
    numShark = _m

    map = Array(n) { IntArray(m) { -1 } }

    sharks = buildList(m) {
        for (i in 0..numShark - 1) {
            val (r, c, s, d, z) = readIntList()
            map[r - 1][c - 1] = i
            this.add(Shark(s, d, z))
        }
    }
}

data class Shark(val s: Int, var d: Int, val z: Int)
