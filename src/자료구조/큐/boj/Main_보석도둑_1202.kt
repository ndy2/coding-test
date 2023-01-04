package 자료구조.큐.boj

import java.io.BufferedReader
import java.util.*
import java.util.stream.Collectors
import java.util.stream.IntStream


fun main() {

    val br = System.`in`.bufferedReader()
    val (n, k) = readIntList(br)
    val gemList = readGemList(br, n)
    val bagQ = readBagQueue(br, k)
    var answer = 0L

    var gemIdx = 0
    val pq = PriorityQueue(Collections.reverseOrder<Long>())
    while (!bagQ.isEmpty()) {
        val c = bagQ.poll()

        while(gemIdx < n && c >= gemList[gemIdx].m) {
            pq.add(gemList[gemIdx++].v)
        }
        if(!pq.isEmpty()) answer += pq.poll();
    }

    println(answer)
}

private fun readIntList(br: BufferedReader) = br.readLine().split(" ").map { it.toInt() }
private fun readLongList(br: BufferedReader) = br.readLine().split(" ").map { it.toLong() }
private fun readGemList(br: BufferedReader, n: Int): List<Gem> {
    return IntRange(1, n)
        .map { readLongList(br) }
        .map { Gem(it[0], it[1]) }
        .sortedWith(compareBy<Gem> { it.m }.thenByDescending { it.v })
}

private fun readBagQueue(br: BufferedReader, k: Int): Queue<Int> {
    return IntStream.range(0, k).map { br.readLine().toInt() }
        .boxed()
        .collect(Collectors.toCollection { PriorityQueue() })
}

class Gem(val m: Long, val v: Long)