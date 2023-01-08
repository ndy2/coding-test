package 누적합.boj

private var br = System.`in`.bufferedReader()
private var bw = System.out.bufferedWriter()
private fun getInt() = br.readLine().toInt()
private fun getIntList() = br.readLine().split(' ').map { it.toInt() }

fun main() {
    var map: Array<ShortArray>

    repeat(getInt()) {
        map = Array(10020) { ShortArray(10020) }

        repeat(getInt()) {
            val (x, y) = getIntList()
            map[y + 1][x + 1] = (map[y + 1][x + 1] + 1).toShort()
            map[y + 1][x + 12] = (map[y + 1][x + 12] - 1).toShort()
            map[y + 12][x + 1] = (map[y + 12][x + 1] - 1).toShort()
            map[y + 12][x + 12] = (map[y + 12][x + 12] + 1).toShort()
        }

        for (r in 1..10000) {
            for (c in 1..10000) {
                map[r][c] = (map[r][c] + map[r][c - 1] + map[r - 1][c] - map[r - 1][c - 1]).toShort()
            }
        }

        var answer = 0
        map.forEach { it.forEach { i -> answer = Integer.max(answer, i.toInt()) } }
        bw.write("$answer\n")
    }
    bw.flush()
}