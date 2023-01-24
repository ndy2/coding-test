package kakao2023blind

fun main() {
    println(
        Solution().solution(
            arrayOf(
                "UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"
                )
        ).contentToString()
    )
}

class Solution {

    fun solution(commands: Array<String>): Array<String> {

        val table = mutableMapOf<Int, Cell>()
        for (r in 1..50) {
            for (c in 1..50) {
                table[idx(r, c)] = Cell(r, c)
            }
        }

        val answer = mutableListOf<String>()
        val invTable = mutableMapOf<Value, MutableSet<Cell>>()
        for (command in commands) {
            val split = command.split(" ")
            if (split[0] == "UPDATE" && split.size == 4) {
                invTable[Value(split[3])] = mutableSetOf()
            }else if (split[0] == "UPDATE" && split.size ==3){
                invTable[Value(split[1])] = mutableSetOf()
                invTable[Value(split[2])] = mutableSetOf()
            }
        }

        for (command in commands) {
            val line = command.split(" ")
            if (line[0] == "UPDATE" && line.size == 4) {
                val r = line[1].toInt()
                val c = line[2].toInt()
                val updateValue = Value(line[3])

                val cell = table[idx(r, c)]!!
                val groups = cell.groups(mutableSetOf())
                val orig = cell.value
                for (gc in groups) {
                    gc.update(updateValue)
                    updateInvTable(orig, updateValue, table, gc.r, gc.c, invTable)
                }

            } else if (line[0] == "UPDATE" && line.size == 3) {
                val value1 = Value(line[1])
                val updateValue = Value(line[2])

                for (cell in invTable[value1]!!) {
                    cell.update(updateValue)
                }
                invTable[updateValue]!!.addAll(invTable[value1]!!)
                invTable.remove(value1)
            } else if (line[0] == "MERGE") {
                val (r1, c1, r2, c2) = line.drop(1).map { it.toInt() }
                val cell1 = table[idx(r1, c1)]!!
                val cell2 = table[idx(r2, c2)]!!

                if (cell1.groups(mutableSetOf()).contains(cell2)) {
                    continue
                } else if (cell1.hasValue && !cell2.hasValue) {
                    cell2.update(cell1.value!!)
                    cell1.merge(cell2)
                    invTable[cell1.value]!!.addAll(cell2.groups(mutableSetOf()))
                } else if (cell2.hasValue && !cell1.hasValue) {
                    cell1.update(cell2.value!!)
                    cell2.merge(cell1)
                    invTable[cell2.value]!!.addAll(cell1.groups(mutableSetOf()))
                } else if (!cell1.hasValue && !cell2.hasValue) {
                    cell2.merge(cell1)
                } else if (cell1.hasValue && cell2.hasValue) {
                    val groupCell2 = cell2.groups(mutableSetOf())
                    invTable[cell1.value]!!.addAll(groupCell2)
                    invTable[cell2.value]!!.removeAll(groupCell2)
                    cell2.update(cell1.value!!)
                    cell1.merge(cell2)
                }
            } else if (line[0] == "UNMERGE") {
                val (r, c) = line.drop(1).map { it.toInt() }
                val cell = table[idx(r, c)]!!
                val orig = cell.value
                val groups = cell.groups(mutableSetOf())
                for (gc in groups) {
                    if (gc.value != null) invTable[gc.value]!!.remove(gc)
                    gc.parent = null
                    gc.child = null
                    gc.value = null
                }
                if(orig!=null) invTable[orig]!!.add(cell)
                cell.value = orig
            } else if (line[0] == "PRINT") {
                val (r, c) = line.drop(1).map { it.toInt() }
                answer.add(table[idx(r, c)]!!.value?.value ?: "EMPTY")
            }
        }

        return answer.toTypedArray()
    }

    // orig -> value
    // 1. invTable 의 orig 집합 에서 idx 원소 제거
    // 2. invTable 의 value 집합 에서 idx 원소 추가
    private fun updateInvTable(
        orig: Value?,
        value: Value,
        table: MutableMap<Int, Cell>,
        r: Int,
        c: Int,
        invTable: MutableMap<Value, MutableSet<Cell>>
    ) {
        if (orig != null) {
            invTable[orig]!!.remove(Cell(r, c))
        }
        if (invTable[value] == null) invTable[value] = mutableSetOf()
        invTable[value]!!.add(table[idx(r, c)]!!)
    }

    private fun idx(r: Int, c: Int): Int = (r - 1) * 50 + (c - 1)
}

private class Cell(
    val r: Int,
    val c: Int,
    var value: Value? = null
) {
    val hasValue: Boolean
        get() = value != null

    var parent: Cell? = null
    var child: Cell? = null

    fun update(updateValue: Value) {
        updateParents(updateValue)
        updateChildren(updateValue)
    }

    private fun updateParents(updateValue: Value) {
        value = updateValue
        parent?.updateParents(updateValue)
    }

    private fun updateChildren(updateValue: Value) {
        value = updateValue
        child?.updateChildren(updateValue)
    }


    fun merge(other: Cell) {
        val oTail = other.tail()
        val tHead = this.head()
        oTail.child = tHead
        tHead.parent = oTail
    }

    fun groups(cells: MutableSet<Cell>): MutableSet<Cell> {
        var c = this
        cells.add(c)
        while (c.parent != null) {
            cells.add(c.parent!!)
            c = c.parent!!
        }
        c = this
        while (c.child != null) {
            cells.add(c.child!!)
            c = c.child!!
        }
        return cells
    }

    private fun head(): Cell {
        var h = this
        while (h.parent != null) {
            h = h.parent!!
        }
        return h
    }

    private fun tail(): Cell {
        var c = this
        while (c.child != null) {
            c = c.child!!
        }
        return c
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cell

        if (r != other.r) return false
        if (c != other.c) return false

        return true
    }

    override fun hashCode(): Int {
        var result = r
        result = 31 * result + c
        return result
    }

    override fun toString(): String {
        val parent = if (parent == null) "NULL" else "Cell(${parent!!.r}, ${parent!!.c})"
        val child = if (child == null) "NULL" else "Cell(${child!!.r}, ${child!!.c})"

        return "Cell(r=$r, c=$c, value=$value, parent : $parent, child : $child)"
    }
}


private data class Value(
    val value: String
) {
    override fun toString(): String {
        return "Value(value='$value')"
    }
}

