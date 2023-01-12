package 자료구조.스택.boj

import java.lang.StringBuilder
import java.util.*

fun main() {

    val br = System.`in`.bufferedReader()
    val s = br.readLine()
    val bomb = br.readLine()

    var bombIdx = -1
    val bombLen = bomb.length

    val stack = Stack<Info>()
    for (c in s) {
        val checkIdx = bombIdx + 1
        if (c == bomb[checkIdx] && checkIdx == bombLen - 1) {
            repeat(bombLen - 1) { stack.pop() }
            bombIdx = if (stack.isNotEmpty()) stack.peek().bombIdx else -1
        } else if (c == bomb[checkIdx]) {
            stack.push(Info(c, checkIdx))
            bombIdx = checkIdx
        } else if (c == bomb[0]) {
            stack.push(Info(c, 0))
            bombIdx = 0
        } else {
            stack.push(Info(c, -1)) // dummy info
            bombIdx = -1
        }
    }

    if (stack.isEmpty()) println("FRULA")
    else {
        val answer = StringBuilder()
        while (!stack.isEmpty()) answer.append(stack.pop().char)
        println(answer.reverse())
    }
}

private data class Info(
        val char: Char,
        val bombIdx: Int,
)