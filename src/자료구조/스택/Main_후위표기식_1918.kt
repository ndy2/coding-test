package 자료구조.스택

import java.util.*

fun main() {
    val s = System.`in`.bufferedReader().readLine()
    val op = Stack<Char>()

    for (e in s) {
        if (e in 'A'..'Z') {
            print(e)
        } else {
            if (e == '(') op.push(e)
            else if (e == '*' || e == '/') {
                while (!op.empty() && (op.peek() == '*' || op.peek() == '/')) {
                    print(op.peek())
                    op.pop()
                }
                op.push(e)
            } else if (e == '+' || e == '-') {
                while (!op.empty() && op.peek() != '(') {
                    print(op.peek())
                    op.pop()
                }
                op.push(e)
            } else if (e == ')') {
                while (!op.empty() && op.peek() != '(') {
                    print(op.peek())
                    op.pop()
                }
                op.pop()
            }
        }
    }
    while (!op.empty()) {
        print(op.peek())
        op.pop()
    }
}

