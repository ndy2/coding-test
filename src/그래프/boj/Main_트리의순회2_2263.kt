private lateinit var infix: IntArray
private lateinit var postfix: IntArray
private lateinit var prefix: IntArray
private var prefixRootIdx = 0

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    infix = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    postfix = br.readLine().split(" ").map { it.toInt() }.toIntArray()

    prefix = IntArray(n)

    find(0, 0, n)
    println(prefix.joinToString(" "))
}

private fun find(infixIdx: Int, postfixIdx: Int, len: Int) {

    val rootData = postfix[postfixIdx + len - 1]
    prefix[prefixRootIdx++] = rootData

    if (len != 1) {
        val mid = infix.indexOf(rootData)
        val lenLeft = mid - infixIdx
        val lenRight = len - lenLeft - 1
        if (lenLeft > 0) find(infixIdx, postfixIdx, lenLeft)
        if (lenRight > 0) find(mid + 1, postfixIdx + lenLeft, lenRight)
    }
}