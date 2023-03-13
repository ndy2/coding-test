package 문자열처리.trie

fun main() {

    val br = System.`in`.bufferedReader()
    val t = br.readLine().toInt()

    repeat(t) {
        val n = br.readLine().toInt()
        val root = Node_trie('r') // 'r' of root!
        repeat(n) { root.add(br.readLine(), 0) }

        val answer = if (root.dfs()) "NO" else "YES"
        println(answer)
    }
}

class Node_trie(
    val char: Char
) {
    val children = Array<Node_trie?>(10) { null }
    var isCompleted = false

    fun add(str: String, idx: Int) {
        val c = str[idx]
        val cIdx = c - '0'

        val childNodeTrie = if (children[cIdx] == null) {
            children[cIdx] = Node_trie(c)
            children[cIdx]!!
        } else {
            children[cIdx]!!
        }

        if (idx == str.lastIndex) {
            childNodeTrie.isCompleted = true
        } else {
            childNodeTrie.add(str, idx + 1)
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