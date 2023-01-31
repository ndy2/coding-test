package 문자열처리.trie

fun main() {

    val br = System.`in`.bufferedReader()
    val t = br.readLine().toInt()

    repeat(t) {
        val n = br.readLine().toInt()
        val root = Node('r') // 'r' of root!
        repeat(n) { root.add(br.readLine(), 0) }

        val answer = if (root.dfs()) "NO" else "YES"
        println(answer)
    }
}

private class Node(
    val char: Char
) {
    val children = Array<Node?>(10) { null }
    var isCompleted = false

    fun add(str: String, idx: Int) {
        val c = str[idx]
        val cIdx = c - '0'

        val childNode = if (children[cIdx] == null) {
            children[cIdx] = Node(c)
            children[cIdx]!!
        } else {
            children[cIdx]!!
        }

        if (idx == str.lastIndex) {
            childNode.isCompleted = true
        } else {
            childNode.add(str, idx + 1)
        }
    }

    fun dfs(): Boolean {
        val notNullChildren = children.filterNotNull()
        if (notNullChildren.isNotEmpty() && isCompleted) {
            return true
        } else if (notNullChildren.isNotEmpty()) {
            for (child in notNullChildren) {
                if (child.dfs()) return true
            }
            return false
        } else {
            return false
        }
    }
}