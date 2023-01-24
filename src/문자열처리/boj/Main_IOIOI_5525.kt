fun main() {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()
    val s = br.readLine()


    var cur = 0
    var toCheck = 'I'
    var answer = 0
    for (i in 0..s.length - 1) {
        if(s[i] == 'I' && toCheck=='O'){
            cur = 0
            toCheck = 'I'
        }

        if (s[i] == toCheck) {
            cur++
            toCheck = toCheck.flip()
        } else {
            cur = 0
            toCheck = 'I'
        }

        if (cur == n * 2 + 1) {
            answer++
            cur-=2
        }
    }
    println(answer)
}

private fun Char.flip(): Char {
    return if (this == 'I') 'O' else 'I'
}
