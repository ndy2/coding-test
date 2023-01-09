fun main() {
    val br = System.`in`.bufferedReader()
    val line = br.readLine()
            .trimStart { it == ' ' }
            .trimEnd { it == ' ' }
    if (line.isEmpty()) {
        print(0)
        return
    }

    print(line.split(" ").size)
}