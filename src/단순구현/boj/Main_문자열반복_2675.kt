private val br = System.`in`.bufferedReader()

fun main() {
    repeat(br.readLine().toInt()) {
        val line = br.readLine().split(" ")
        val r = line[0].toInt()
        val s = line[1]

        println(buildString(s.length * r) {
            s.forEach { c -> repeat(r) { append(c) } }
        })
    }
}