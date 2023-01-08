package 비트연산.boj

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    var bit = 0
    val n = br.readLine().toInt()
    repeat(n) {
        val line = br.readLine().split(" ")
        val x = if (line.size == 2) line[1].toInt() else -1
        when (line[0]) {
            "add" -> bit = bit or (0b1 shl x)
            "remove" -> bit = bit and ((0b1 shl x).inv())
            "check" -> bw.write("${(bit shr x) and 0b1}\n")
            "toggle" -> bit = bit xor (0b1 shl x)
            "all" -> bit = 0b1_1111_1111_1111_1111_1111
            "empty" -> bit = 0
        }
    }
    bw.flush()
    bw.close()
}