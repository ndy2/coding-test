package kakao2023blind

fun main() {
    println(Solution_표_병합().solution(arrayOf(
            "UPDATE 1 1 a",
            "UPDATE 1 2 b",
            "UPDATE 2 1 c",
            "UPDATE 2 2 d",
            "MERGE 1 1 1 2",
            "MERGE 2 2 2 1",
            "MERGE 2 1 1 1",
            "PRINT 1 1",
            "UNMERGE 2 2",
            "PRINT 1 1"
    )).contentToString())
    println(Solution_표_병합().solution(arrayOf(
             "UPDATE 1 1 A",
             "UPDATE 2 2 B",
             "UPDATE 3 3 C",
             "UPDATE 4 4 D",
             "MERGE 1 1 2 2",
             "MERGE 3 3 4 4",
             "MERGE 1 1 4 4",
             "UNMERGE 3 3",
             "PRINT 1 1",
             "PRINT 2 2",
             "PRINT 3 3",
             "PRINT 4 4"
     )).contentToString())
     println(Solution_표_병합().solution(arrayOf(
             "UPDATE 1 1 menu",
             "UPDATE 1 2 category",
             "UPDATE 2 1 bibimbap",
             "UPDATE 2 2 korean",
             "UPDATE 2 3 rice",
             "UPDATE 3 1 ramyeon",
             "UPDATE 3 2 korean",
             "UPDATE 3 3 noodle",
             "UPDATE 3 4 instant",
             "UPDATE 4 1 pasta",
             "UPDATE 4 2 italian",
             "UPDATE 4 3 noodle",
             "MERGE 1 2 1 3",
             "MERGE 1 3 1 4",
             "UPDATE korean hansik",
             "UPDATE 1 3 group",
             "UNMERGE 1 4",
             "PRINT 1 3",
             "PRINT 1 4"
     )).contentToString())
}

class Solution_표_병합 {

    private var parents = IntArray(2500) { it }
    private var ranks = IntArray(2500) { 1 }
    private var values: Array<String?> = Array(2500) { null }
    private var edgesMap = Array(2500) { mutableListOf<Int>() }

    fun solution(commands: Array<String>): Array<String> {
        val answer = mutableListOf<String>()
        for (command in commands) {
            val tokens = command.split(" ")
            val op = tokens[0]
            if (op == "UPDATE" && tokens.size == 4) {
                val r = tokens[1].toInt() - 1
                val c = tokens[2].toInt() - 1
                val value = tokens[3]
                values[find(idx(r, c))] = value
            } else if (op == "UPDATE" && tokens.size == 3) {
                val value1 = tokens[1]
                val value2 = tokens[2]
                for (idx in 0..2500 - 1) {
                    if (values[find(idx)] == value1) {
                        values[find(idx)] = value2
                    }
                }
            } else if (op == "MERGE") {
                val r1 = tokens[1].toInt() - 1
                val c1 = tokens[2].toInt() - 1
                val r2 = tokens[3].toInt() - 1
                val c2 = tokens[4].toInt() - 1

                merge(idx(r1, c1), idx(r2, c2))
            } else if (op == "UNMERGE") {
                val r = tokens[1].toInt() - 1
                val c = tokens[2].toInt() - 1

                unmerge(idx(r, c))
            } else if (op == "PRINT") {
                val r = tokens[1].toInt() - 1
                val c = tokens[2].toInt() - 1

                answer.add(values[find(idx(r, c))] ?: "EMPTY")
            }
        }

        return answer.toTypedArray()
    }

    private fun unmerge(idx: Int) {
        val root = find(idx)
        val origValue = values[root]
        dfs(root)

        if (origValue != null) values[idx] = origValue
    }

    private fun dfs(a: Int) {
        parents[a] = a
        values[a] = null
        for (c in edgesMap[a]) {
            dfs(c)
        }
        edgesMap[a].clear()
    }

    private fun merge(a: Int, b: Int) {
        val pa = find(a)
        val pb = find(b)
        if (pa != pb) {
            val p = if (ranks[pa] >= ranks[pb]) pa else pb
            val c = if (ranks[pa] >= ranks[pb]) pb else pa

            parents[c] = p
            edgesMap[p].add(c)
            if (ranks[p] == ranks[c]) ranks[pb] += 1

            if (values[p] == null && values[c] != null) {
                values[p] = values[c]
            } else if (values[p] != null && values[c] == null) {
                values[c] = values[p]
            } else if (values[p] != null && values[c] != null) {
                values[p] = values[pa]
            }
        }
    }

    private fun find(a: Int): Int {
        if (a == parents[a]) return a
        else {
            parents[a] = find(parents[a])
            return parents[a]
        }
    }

    fun idx(r: Int, c: Int) = 50 * r + c
}
