package 문자열처리.trie

class Main_개미굴_14725 {

    private var n = 0
    private lateinit var stringMap: Array<Array<String>>

    fun read() = System.`in`.bufferedReader().use { br ->
        n = br.readLine().toInt()
        stringMap = Array(n) { Array(0) { "" } }
        repeat(n) { stringMap[it] = br.readLine().split(" ").drop(1).toTypedArray() }
    }

    fun solve() {
        val root = Node("") //dummy data for root
        stringMap.forEach { root.addStrings(it, 0) }

        val answer = StringBuilder()
        root.dfs(0, answer)
        println(answer)
    }

    private class Node(
        val data: String
    ) {
        val children = mutableMapOf<String, Node>()
        var isCompleted = false

        fun addStrings(strings: Array<String>, idx: Int) {
            val curString = strings[idx]
            val child =
                if (children.containsKey(curString)) children[curString]!!
                else {
                    children[curString] = Node(curString)
                    children[curString]!!
                }

            if (idx == strings.lastIndex) child.isCompleted = true
            else child.addStrings(strings, idx + 1)
        }

        fun dfs(pad: Int, result: StringBuilder) {
            children.entries
                .sortedBy { it.key }
                .map { it.value }
                .forEach {
                    result.append("-".repeat(pad)).appendLine(it.data)
                    it.dfs(pad +2, result)
                }
        }

    }
}

fun main() {
    val solution = Main_개미굴_14725()
    solution.read()
    solution.solve()
}