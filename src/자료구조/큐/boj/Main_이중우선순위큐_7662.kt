package 자료구조.큐.boj

import java.util.*


fun main() {
    val br = System.`in`.bufferedReader()

    repeat(br.readLine().toInt()) {
        val minHeap = PriorityQueue<Int>()
        val maxHeap = PriorityQueue<Int>(Comparator.reverseOrder())
        val countMap = mutableMapOf<Int, Int>()

        repeat(br.readLine().toInt()) {

            val line = br.readLine().split(" ")
            if (line[0] == "I") {
                val x = line[1].toInt()
                minHeap.add(x)
                maxHeap.add(x)
                countMap[x] = countMap.getOrDefault(x, 0) + 1
            } else if (line[0] == "D") {
                if (line[1] == "1" && !maxHeap.isEmpty()) {
                    while (countMap[maxHeap.peek()] == 0) {
                        maxHeap.poll()
                    }
                    if (!maxHeap.isEmpty()) {
                        val polled = maxHeap.poll()
                        countMap[polled] = countMap[polled]!! - 1
                    }

                } else if (line[1] == "-1" && !minHeap.isEmpty()) {
                    while (countMap[minHeap.peek()] == 0) {
                        minHeap.poll()
                    }
                    if (!minHeap.isEmpty()) {
                        val polled = minHeap.poll()
                        countMap[polled] = countMap[polled]!! - 1
                    }

                }
            }
        }
        while (countMap[maxHeap.peek()] == 0) {
            maxHeap.poll()
        }
        while (countMap[minHeap.peek()] == 0) {
            minHeap.poll()
        }
        if (maxHeap.isEmpty()) {
            println("EMPTY")
        } else {
            println("${maxHeap.peek()} ${minHeap.peek()}")
        }
    }
}