private lateinit var map: Array<BooleanArray>
private var n = 0
private var white = 0
private var blue = 0


fun main() {
    readMap()
    dfs(0, 0, n)
    println("$white\n$blue")
}

fun dfs(r: Int, c: Int, len: Int) {
    if (len == 1) {
        if (map[r][c]) blue++ else white++
    } else {
        var isBlue = true
        var isWhite = true

        for (row in r..r + len - 1) {
            for (col in c..c + len - 1) {
                if (map[row][col]) isWhite = false
                else isBlue = false
            }
        }

        if (isBlue) {
            blue++
        } else if (isWhite) {
            white++
        } else {
            val nLen = len / 2
            dfs(r, c, nLen)
            dfs(r + nLen, c, nLen)
            dfs(r, c + nLen, nLen)
            dfs(r + nLen, c + nLen, nLen)
        }
    }


}

private fun readMap() {
    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()
    map = Array(n) { BooleanArray(n) }
    for (r in 0..n - 1) {
        map[r] = br.readLine().split(" ").map { it == "1" }.toBooleanArray()
    }
}